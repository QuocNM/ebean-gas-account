package service.serializer.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GasLogJsonExclusionStrategy implements ExclusionStrategy {

    private final String[] skippedAttributeNames = {"_id", "_created", "_modified", "isOk"};

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        for (String field : skippedAttributeNames) {
            if (field.equals(f.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

}
