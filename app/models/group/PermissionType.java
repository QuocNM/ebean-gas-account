package models.group;

import io.ebean.annotation.EnumValue;

/**
 * Created by quoc.nm on 7/17/2017.
 */
public enum PermissionType {

  @EnumValue("CREATE")
  CREATE,

  @EnumValue("READ")
  READ,

  @EnumValue("UPDATE")
  UPDATE,

  @EnumValue("DELETE")
  DELETE,

  @EnumValue("NULL")
  NULL
}
