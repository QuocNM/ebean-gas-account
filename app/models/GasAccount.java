package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import models.common.BaseModel;
import models.group.GroupType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by quoc.nm on 7/17/2017.
 */
@Entity
public class GasAccount extends BaseModel {

  private boolean active = false;
  private String username;
  private String userPassword;
  private long reference;
  private long ownerId;

  @NotNull
  private GroupType groupType;

  public GasAccount() {
    username = "default";
    userPassword = "Abc123!";
    groupType = GroupType.SUB_CLI;
    reference = 0;
    ownerId = 0;
    active = false;
  }

  public GasAccount(boolean isActive, String username, String userPassword, long reference, GroupType groupType, long ownerId) {
    this.active = isActive;
    this.username = username;
    this.userPassword = userPassword;
    this.reference = reference;
    this.groupType = groupType;
    this.ownerId = ownerId;
  }

  public GasAccount(String username, String userPassword, GroupType groupType) {
    this.username = username;
    this.userPassword = userPassword;
    this.groupType = groupType;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public long getReference() {
    return reference;
  }

  public void setReference(long reference) {
    this.reference = reference;
  }

  public GroupType getGroupType() {
    return groupType;
  }

  public void setGroupType(GroupType groupType) {
    this.groupType = groupType;
  }

  @Override
  public String toString() {
    return "GasAccount{" +
        "  isActive=" + active +
        ", id=" + id +
        ", username='" + username + '\'' +
        ", userPassword='" + userPassword + '\'' +
        ", reference=" + reference +
        ", ownerId=" + ownerId +
        ", groupType=" + groupType +
        '}';
  }

  public long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }

  @Override
  public void prepareToUpdate(BaseModel other) {
    if (!(other instanceof GasAccount)) throw new IllegalArgumentException();

    final GasAccount _other = (GasAccount) other;

    this.setUsername(_other.username);
    this.setUserPassword(_other.userPassword);
    this.setActive(_other.active);
    this.setReference(_other.reference);
    this.setOwnerId(_other.ownerId);
    this.setGroupType(_other.groupType);
  }

  public static final Finder<Long, GasAccount> find = new Finder<>(GasAccount.class);

}
