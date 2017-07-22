package service.serializer.serialize;

public interface JsonSerializer {

    /**
     * @param obj
     * @return
     */
    String toJson(Object obj);

}
