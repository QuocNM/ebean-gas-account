package service.serializer.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import service.serializer.json.gson.GasLogJsonExclusionStrategy;

@Service
public class GasLogJsonBuilder extends AbstractJsonBuilderService {

    private JsonParser jsonParser;

    public GasLogJsonBuilder() {
        this.jsonParser = new JsonParser();
        this.gsonBuilder.addSerializationExclusionStrategy(new GasLogJsonExclusionStrategy());
    }

    @Override
    public JsonElement toJsonObject(Object obj) {
        if (obj instanceof String)
            return jsonParser.parse((String) obj).getAsJsonObject();
        return super.toJsonObject(obj);
    }
}
