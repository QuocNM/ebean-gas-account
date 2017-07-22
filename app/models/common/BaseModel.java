package models.common;

import io.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel extends Model {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    return hashCode() == o.hashCode();
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  public abstract void prepareToUpdate(BaseModel other);

}
