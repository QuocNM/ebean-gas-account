package models.group;

import io.ebean.annotation.EnumValue;

/**
 * Created by quoc.nm on 7/17/2017.
 */
public enum AppType {
  @EnumValue("GAME")
  GAME,

  @EnumValue("MOBILE")
  MOBILE,

  @EnumValue("WEB")
  WEB,

  @EnumValue("IOT")
  IOT,

  @EnumValue("DESKTOP")
  DESKTOP,

  @EnumValue("NULL")
  NULL
}
