package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import service.response.ServiceResponse;

import java.io.IOException;

import static play.test.Helpers.contentAsString;

/**
 * Created by quoc.nm on 7/19/2017.
 */
public class Utils {

  private static ObjectMapper mapper = new ObjectMapper();
  private static Gson gson = new Gson();

  public static JsonNode objectToJsonNode(Object object) {
    return mapper.convertValue(object, JsonNode.class);
  }

  public static Result connApiServer(String url, String method, JsonNode data) {
    Http.RequestBuilder request = new Http.RequestBuilder().method(method)
        .bodyJson(data)
        .uri(url);
    Result result = Helpers.route(request);
    return result;
  }

  public static boolean validateAccount(GasAccount gasAccount) {
    if (gasAccount != null) return true;
    return false;
  }

  public static void checkResult(Result result){
    final String body = contentAsString(result);
    System.out.println("body: " + body);
    ServiceResponse serviceResponse = gson.fromJson(body, ServiceResponse.class);
    System.out.println("Message: " + serviceResponse.getMessage());
  }

}
