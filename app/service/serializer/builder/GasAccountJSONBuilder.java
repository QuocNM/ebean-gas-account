package service.serializer.builder;

import com.google.inject.Singleton;
import models.GasAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.serializer.json.AbstractJsonBuilderService;
import service.serializer.json.gson.RuntimeTypeAdapterFactory;

import java.util.HashMap;

@Singleton
public class GasAccountJSONBuilder extends AbstractJsonBuilderService {
    private RuntimeTypeAdapterFactory<GasAccount> runtimeTypeAdapterFactory;

    private static final Logger logger = LoggerFactory.getLogger(GasAccountJSONBuilder.class);

    public GasAccountJSONBuilder() {
//        this.gsonBuilder.setExclusionStrategies(new UserDataJsonExclusionStrategy(), new JsonExclusionStrategy());
//        this.gsonBuilder.setFieldNamingStrategy(new UserDataJsonNamingStrategy());

        runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(GasAccount.class, "user_model_type");
//        Arrays.stream(UserModelMapper.values()).forEach(m -> runtimeTypeAdapterFactory.registerSubtype(m.getEntityClass(), m.name()));

        this.gsonBuilder.registerTypeAdapterFactory(runtimeTypeAdapterFactory);
    }

    @Override
    public String toJson(Object obj) {
        if (obj instanceof HashMap) {
            if (logger.isDebugEnabled()) {
                logger.debug("serializing model {}", obj.getClass().getSimpleName());
            }
            return this.gsonBuilder.create().toJson(obj);
        } else {
            return super.toJson(obj);
        }
    }
}
