package models.group;

import io.ebean.annotation.EnumValue;

/**
 * Created by quoc.nm on 7/17/2017.
 */
public enum GroupType {
  @EnumValue("GAS")
  GAS,

  @EnumValue("OWNER")
  OWNER,

  @EnumValue("SUB_CLI")
  SUB_CLI,

  @EnumValue("CLONE")
  CLONE,

  @EnumValue("NULL")
  NULL
}
