package models;

import models.common.BaseModel;
import models.group.PermissionType;
import service.serializer.serialize.JsonIgnore;

import javax.persistence.*;

/**
 * Created by quoc.nm on 7/13/2017.
 */
@Entity
public class ApplicationFeaturePermission extends BaseModel {

  private Long gasAccountId;

  private Long featureAppId;

  @Enumerated(EnumType.STRING)
  private PermissionType permission;

  public ApplicationFeaturePermission(Long gasAccountId, Long featureAppId, PermissionType permission) {
    this.gasAccountId = gasAccountId;
    this.featureAppId = featureAppId;
    this.permission = permission;
  }

  public ApplicationFeaturePermission() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public void prepareToUpdate(BaseModel other) {
    if (!(other instanceof ApplicationFeaturePermission)) throw new IllegalArgumentException();

    final ApplicationFeaturePermission _other = (ApplicationFeaturePermission) other;

    this.setGasAccount(_other.gasAccountId);
    this.setFeatureAppId(_other.featureAppId);
    this.setPermission(_other.permission);
  }

  public Long getGasAccount() {
    return gasAccountId;
  }

  public void setGasAccount(Long gasAccountId) {
    this.gasAccountId = gasAccountId;
  }

  public Long getFeatureAppId() {
    return featureAppId;
  }

  public void setFeatureAppId(Long featureAppId) {
    this.featureAppId = featureAppId;
  }

  public PermissionType getPermission() {
    return permission;
  }

  public void setPermission(PermissionType permission) {
    this.permission = permission;
  }

  @Override
  public String toString() {
    return "ApplicationFeaturePermission{" +
        "gasAccount=" + gasAccountId +
        ", featureAppId=" + featureAppId +
        ", permission=" + permission +
        '}';
  }
}
