package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controllers.GasAccountController;
import controllers.routes;
import models.group.GroupType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Http;
import play.test.Helpers;
import service.GasAccountService;
import service.response.ServiceResponse;
import service.utils.Utils;

import java.io.IOException;
import java.util.List;

import static models.Utils.connApiServer;
import static models.Utils.objectToJsonNode;
import static play.libs.Json.toJson;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;


public class GasAccountTest extends BaseTest {

  private GasAccountController gasAccountController = app.injector().instanceOf(GasAccountController.class);
  private ObjectMapper mapper = new ObjectMapper();
  private Gson gson = new Gson();
  private GasAccount ownerAccount = new GasAccount();

  public GasAccount createZombie(String posfix, GroupType groupType, Long ownerId) {
    GasAccount account = new GasAccount();
    account.setUsername("name" + posfix);
    account.setUserPassword("pass" + posfix);
    account.setActive(true);
    account.setGroupType(groupType);
    account.setOwnerId(ownerId);
    account.save();
    return account;
  }

  @Before
  public void init(){
    ownerAccount.setActive(true);
    ownerAccount.setGroupType(GroupType.OWNER);
    ownerAccount.setOwnerId(-1);
    ownerAccount.setReference(-1);
    ownerAccount.setUsername("ownerName");
    ownerAccount.setUserPassword("ownerPass");

    createZombie("1", GroupType.SUB_CLI, 1L);
    createZombie("2", GroupType.SUB_CLI, 1L);
    createZombie("3", GroupType.SUB_CLI, 1L);
    createZombie("4", GroupType.SUB_CLI, 1L);
  }

  @Test
  public void index() throws IOException {
    JsonNode jsonNode = mapper.readTree("{}");
    Result result = connApiServer(
        controllers.routes.GasAccountController.index().url(),
        "GET",
        jsonNode
    );

    String body = contentAsString(result);
    System.out.println(body);
  }

  @Test
  public void registerOwner(){
    JsonObject data = new JsonObject();
    data.addProperty("username", "zombie1");
    data.addProperty("userPassword", "pass1");
    JsonNode jsonNode = objectToJsonNode(data);
    Result result = connApiServer(
        controllers.routes.GasAccountController.registerOwner().url(),
        "POST",
        jsonNode
    );
    String body = contentAsString(result);
    List<GasAccount> gasAccounts = GasAccount.find.all();
    System.out.println(gasAccounts);
  }

  @Test
  public void login(){
//    JsonObject data = new JsonObject();
//    data.addProperty("username", "name1");
//    data.addProperty("userPassword", "pass1");
    GasAccount data = new GasAccount("name1", "pass1", GroupType.SUB_CLI);

    JsonNode jsonNode = objectToJsonNode(data);
    Result result = connApiServer(
        controllers.routes.GasAccountController.login().url(),
        "POST",
        jsonNode
    );
    String body = contentAsString(result);
    List<GasAccount> gasAccounts = GasAccount.find.all();
    System.out.println(gasAccounts);
    System.out.println(body);
  }

}