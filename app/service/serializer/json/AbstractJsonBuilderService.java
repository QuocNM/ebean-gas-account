package service.serializer.json;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import service.serializer.json.gson.JsonExclusionStrategy;
import service.serializer.serialize.JsonDeserializer;
import service.serializer.serialize.JsonSerializer;

import java.lang.reflect.Type;

public abstract class AbstractJsonBuilderService implements JsonSerializer, JsonDeserializer {

    protected GsonBuilder gsonBuilder;

    public AbstractJsonBuilderService() {
        this.gsonBuilder = new GsonBuilder();
        this.gsonBuilder.disableHtmlEscaping();
        this.gsonBuilder.addSerializationExclusionStrategy(new JsonExclusionStrategy());
    }

    @Override
    public String toJson(Object obj) {
        return this.gsonBuilder.create().toJson(obj);
    }

    @Override
    public <T> T toObject(String json, Type type) throws JsonSyntaxException {                                   
        return this.gsonBuilder.create().fromJson(json, type);
    }

    @Override
    public <T> T toObject(String json, Class<T> clazz) throws JsonSyntaxException {
        return this.gsonBuilder.create().fromJson(json, clazz);
    }

    public JsonElement toJsonObject(Object obj) {
        return this.gsonBuilder.create().toJsonTree(obj);
    }

}
