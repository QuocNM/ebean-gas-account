package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ebean.Expr;
import io.ebean.PagedList;
import io.ebean.Query;
import models.GasAccount;
import models.group.GroupType;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import service.GasAccountService;
import service.response.ServiceResponse;
import service.response.StatusCode;
import service.serializer.builder.GasAccountJSONBuilder;

import javax.inject.Inject;
import java.util.*;

import static play.libs.Json.toJson;
import static service.utils.Utils.validateAccountOwner;


public class GasAccountController extends Controller {

  private ObjectMapper mapper = new ObjectMapper();

  public Result index() {
    return ok("It's work!!!");
  }

  private boolean validateAccountOnwer(GasAccount accountOwner) {
    if (accountOwner == null
        || !accountOwner.getGroupType().equals(GroupType.OWNER)
        || accountOwner.getOwnerId() != -1) return false;
    return true;
  }

  public Result registerOwner() {
    JsonNode body = request().body().asJson();
    if (body == null) return badRequest();
    String username = body.get("username").toString();
    String password = body.get("password").toString();
    GasAccount gasAccount = new GasAccount(username, password, GroupType.OWNER);
    gasAccount.save();
    return ok(toJson("OK!!!"));
  }

  public Result login() {
    JsonNode body = request().body().asJson();
    if (body == null) return badRequest();
    String username = body.get("username").asText();
    String password = body.get("userPassword").asText();
    if (username == null || password == null) return badRequest();

    GasAccount gasAccount = GasAccount.find
        .query().where()
        .and()
          .ilike("username", username)
          .endAnd()
        .and()
          .ilike("userPassword", password)
          .endAnd()
        .findOne();

    if (gasAccount != null){
      return ok(toJson(gasAccount));
    }
    return ok(toJson("{NOT FOUND!!!}"));
  }
}
