package service.serializer.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import service.serializer.serialize.JsonIgnore;

public class JsonExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        if (f.getAnnotation(JsonIgnore.class) != null) {
            return true;
        }
        JsonIgnore clsAnnotation = f.getDeclaringClass().getAnnotation(JsonIgnore.class);
        if (clsAnnotation != null) {
            for (String field : clsAnnotation.value()) {
                if (field.equals(f.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

}
