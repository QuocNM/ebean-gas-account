package service.serializer;

public interface SerializerManager {

    <T> byte[] serializeToBytes(T obj);

    <T> String serializeToString(T obj);

    <T> T deserialize(String binSafeStr, Class<T> clazz);

    <T> T deserialize(String binSafeStr);

    <T> T deserialize(byte[] bytes, Class<T> primitiveClazz);

    <T> T deserialize(byte[] bytes);

    String serializeBytesToString(byte[] bytes);

    byte[] deserializeStringToBytes(String binSafeStr);

    <T> T deserialize_v2(String member, Class<T> clazz);
}
