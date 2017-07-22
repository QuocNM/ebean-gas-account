package service.serializer.serialize;

import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public interface JsonDeserializer {

    /**
     * Generic Class Type
     *
     * @param json
     * @param type
     * @return
     */
    <T> T toObject(String json, Type type) throws JsonSyntaxException;

    /**
     * @param json
     * @param clazz
     * @return
     * @throws JsonSyntaxException
     */
    <T> T toObject(String json, Class<T> clazz) throws JsonSyntaxException;

}
