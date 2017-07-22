package service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.GasAccount;
import models.group.GroupType;

/**
 * Created by quoc.nm on 7/19/2017.
 */
public class Utils {

  private static ObjectMapper mapper = new ObjectMapper();

  public static JsonNode convertToJsonNode(Object object) {
    return mapper.convertValue(object, JsonNode.class);
  }

  public static boolean validateAccountOwner(GasAccount gasAccount) {
    if (gasAccount != null) return true;
    return false;
  }

  public static boolean validateSubAccountOwner(GasAccount subAccount){
    if (subAccount.getOwnerId() < 1) return false;
    return true;
  }

  public static boolean validateSubAccountType(GasAccount subAccount){
    if (subAccount.getGroupType().equals(GroupType.SUB_CLI)) return true;
    return false;
  }

}
