package service.serializer.javaserialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class ClassLoaderObjectInputStream extends ObjectInputStream {
    public ClassLoaderObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        try {
            String name = desc.getName();
            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            Class<?> clazz = ccl.loadClass(name);
            return clazz;
        } catch (ClassNotFoundException e) {
            return super.resolveClass(desc);
        }
    }
}
