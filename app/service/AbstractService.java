package service;

import io.ebean.PagedList;
import models.common.BaseModel;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Using for create new service for model
 */
public abstract class AbstractService<T extends BaseModel> extends BaseService {

  private Class<T> entityClass;

  @Inject
  public AbstractService(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext, Class<T> entityClass) {
    super(ebeanConfig, executionContext);
    setEntityClass(entityClass);
  }

  public void setEntityClass(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public PagedList<T> page(int page, int pageSize, String sortBy, String order, String fieldName, String filter) {
    return page(page, pageSize, sortBy, order, fieldName, filter, entityClass);
  }

  public List<T> findAll() {
    return findAll(entityClass);
  }

  public Optional<T> findByField(String field, String value) {
    return findByField(field, value, entityClass);
  }

  public Optional<T> findById(Long id) {
    return findById(id, entityClass);
  }

  public Optional<Long> delete(Long id) {
    return delete(id, entityClass);
  }

  public abstract Optional<Long> update(T entityClass);

}
