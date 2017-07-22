package service.serializer.javaserialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;
import service.serializer.SerializerManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class JavaSerializerManager implements SerializerManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaSerializerManager.class);
    private static byte STREAM_MAGIC[] = {(byte) 0xac, (byte) 0xed};

    public JavaSerializerManager() {
        super();
    }

    @Override
    public <T> byte[] serializeToBytes(T obj) {
        return SerializationUtils.serialize(obj);
    }

    @Override
    public <T> String serializeToString(T obj) {
        if (obj == null) {
            return null;
        }
        if (isPrimitive(obj.getClass())) {
            return primitiveSerialize(obj);
        } else {
            byte[] bytes = this.serializeToBytes(obj);
            return serializeBytesToString(bytes);
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(String binSafeStr, Class<T> clazz) {
        if (binSafeStr == null) {
            return null;
        }
        if (isPrimitive(clazz)) {
            return (T) primitiveDeserialize(binSafeStr, clazz);
        } else {
            byte[] bytes = deserializeStringToBytes(binSafeStr);
            return this.deserialize(bytes);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize_v2(String binSafeStr, Class<T> clazz) {
        if (binSafeStr == null) {
            return null;
        }
        if (isPrimitive(clazz)) {
            return (T) primitiveDeserialize_v2(binSafeStr, clazz);
        } else {
            byte[] bytes = deserializeStringToBytes(binSafeStr);
            return this.deserialize(bytes);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(String binSafeStr) {
        if (binSafeStr == null) {
            return null;
        }
        byte[] bytes = deserializeStringToBytes(binSafeStr);
        if (bytes.length >= 2 && bytes[0] == STREAM_MAGIC[0] && bytes[1] == STREAM_MAGIC[1]) { // object write
            return this.deserialize(bytes);
        } else { // primitive write
            return (T) primitiveDeserialize(binSafeStr);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bytes, Class<T> primitiveClazz) {
        if (isPrimitive(primitiveClazz)) {
            return (T) primitiveDeserialize(bytes, primitiveClazz);
        } else {
            return (T) SerializationUtils.deserialize(bytes);

        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ObjectInputStream ois = null;
        try {
            ois = new ClassLoaderObjectInputStream(new ByteArrayInputStream(bytes));
            return (T) ois.readObject();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Failed to deserialize object", ex);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Failed to deserialize object type", ex);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    @Override
    public String serializeBytesToString(byte[] bytes) {
        try {
            return new String(bytes, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public byte[] deserializeStringToBytes(String binSafeStr) {
        try {
            return binSafeStr.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    protected String primitiveSerialize(Object obj) {
        return obj.getClass().getName() + ":" + String.valueOf(obj);
    }


    protected Object primitiveDeserialize_v2(String binSafeStr, Class<?> c) {
        if (c == String.class) {
            return binSafeStr;
        } else if (c == Integer.class) {
            return binSafeStr == null ? null : Integer.parseInt(binSafeStr);
        } else if (c == Long.class) {
            return binSafeStr == null ? null : Long.parseLong(binSafeStr);
        } else if (c == Float.class) {
            return binSafeStr == null ? null : Float.parseFloat(binSafeStr);
        } else if (c == Double.class) {
            return binSafeStr == null ? null : Double.parseDouble(binSafeStr);
        } else if (c == Boolean.class) {
            return binSafeStr == null ? null : Boolean.parseBoolean(binSafeStr);
        }
        if (c == Byte.class) {
            return binSafeStr == null ? null : Byte.parseByte(binSafeStr);
        } else if (c == Character.class) {
            if (binSafeStr.length() > 1) {
                return null;
            } else {
                return binSafeStr.charAt(0);
            }
        } else if (c == Short.class) {
            return binSafeStr == null ? null : Short.parseShort(binSafeStr);
        }
        return null;
    }

    protected Object primitiveDeserialize(String binSafeStr, Class<?> c) {
        if (binSafeStr == null) {
            return null;
        }
        String[] tokens = binSafeStr.split(":");
        if (tokens.length < 2) {
            return null;
        }
        String type = tokens[0];
        String value = binSafeStr.substring(type.length() + 1);
        if (c == String.class) {
            return value;
        } else if (c == Integer.class) {
            return Integer.parseInt(value);
        } else if (c == Long.class) {
            return Long.parseLong(value);
        } else if (c == Float.class) {
            return Float.parseFloat(value);
        } else if (c == Double.class) {
            return Double.parseDouble(value);
        } else if (c == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (c == Byte.class) {
            return Byte.parseByte(value);
        } else if (c == Character.class) {
            if (value.length() > 1) {
                return null;
            } else {
                return value.charAt(0);
            }
        } else if (c == Short.class) {
            return Short.parseShort(value);
        }
        return null;
    }

    protected Object primitiveDeserialize(String binSafeStr) {
        if (binSafeStr == null) {
            return null;
        }
        String[] tokens = binSafeStr.split(":");
        if (tokens.length < 2) {
            return null;
        }
        String type = tokens[0];
        String value = binSafeStr.substring(type.length() + 1);
        if ("java.lang.String".equals(type)) {
            return value;
        } else if ("java.lang.Integer".equals(type)) {
            return Integer.parseInt(value);
        } else if ("java.lang.Long".equals(type)) {
            return Long.parseLong(value);
        } else if ("java.lang.Float".equals(type)) {
            return Float.parseFloat(value);
        } else if ("java.lang.Double".equals(type)) {
            return Double.parseDouble(value);
        } else if ("java.lang.Boolean".equals(type)) {
            return Boolean.parseBoolean(value);
        }
        if ("java.lang.Byte".equals(type)) {
            return Byte.parseByte(value);
        } else if ("java.lang.Character".equals(type)) {
            if (binSafeStr.length() > 1) {
                return null;
            } else {
                return value.charAt(0);
            }
        } else if ("java.lang.Short".equals(type)) {
            return Short.parseShort(value);
        }
        return null;
    }

    protected Object primitiveDeserialize(byte[] bytes, Class<?> c) {
        if (c == String.class) {
            return new String(bytes);
        } else if (c == Integer.class) {
            return ByteBuffer.wrap(bytes).getInt();
        } else if (c == Long.class) {
            return ByteBuffer.wrap(bytes).getLong();
        } else if (c == Float.class) {
            return ByteBuffer.wrap(bytes).getFloat();
        } else if (c == Double.class) {
            return ByteBuffer.wrap(bytes).getDouble();
        } else if (c == Boolean.class) {
            return (bytes[0] == (byte) 1);
        }
        if (c == Byte.class) {
            return bytes[0];
        } else if (c == Character.class) {
            return ByteBuffer.wrap(bytes).getChar();
        } else if (c == Short.class) {
            return ByteBuffer.wrap(bytes).getShort();
        }
        return null;
    }

    protected byte[] primitiveSerializeToBytes(Object obj) {
        Class<?> c = obj.getClass();
        if (c == String.class) {
            return ((String) obj).getBytes();
        } else if (c == Integer.class || c == int.class) {
            return ByteBuffer.allocate(Integer.SIZE / Byte.SIZE).putInt((int) obj).array();
        } else if (c == Long.class) {
            return ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong((long) obj).array();
        } else if (c == Float.class) {
            return ByteBuffer.allocate(Float.SIZE / Byte.SIZE).putFloat((float) obj).array();
        } else if (c == Double.class) {
            return ByteBuffer.allocate(Double.SIZE / Byte.SIZE).putDouble((double) obj).array();
        } else if (c == Boolean.class) {
            boolean b = (boolean) obj;
            return new byte[]{(byte) (b ? 1 : 0)};
        }
        if (c == Byte.class) {
            return new byte[]{(byte) obj};
        } else if (c == Character.class) {
            return ByteBuffer.allocate(Character.SIZE / Byte.SIZE).putChar((char) obj).array();
        } else if (c == Short.class) {
            return ByteBuffer.allocate(Short.SIZE / Byte.SIZE).putShort((short) obj).array();
        }
        return null;
    }

    public boolean isPrimitive(Class<?> c) {
        final boolean isWrapper = (c == Byte.class || c == Short.class || c == Integer.class || c == Long.class || c == Float.class
                || c == Double.class || c == Boolean.class || c == Character.class || c == String.class);

        return c != null && (c.isPrimitive() || isWrapper);
    }

}
