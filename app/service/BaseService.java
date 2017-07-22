package service;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.PagedList;
import models.common.BaseModel;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Using for handle for any model
 */

public class BaseService {

  protected final EbeanServer ebeanServer;
  protected final DatabaseExecutionContext executionContext;

  @Inject
  public BaseService(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
    this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
    this.executionContext = executionContext;
  }

  public <T extends BaseModel> PagedList<T> page(int page, int pageSize, String sortBy, String order, String fieldName, String filter, Class<T> entityClass) {
    return ebeanServer.find(entityClass).where()
        .ilike(fieldName, "%" + filter + "%")
        .orderBy(sortBy + " " + order)
        .setFirstRow(page * pageSize)
        .setMaxRows(pageSize)
        .findPagedList();
  }

  public <T extends BaseModel> List<T> findAll(Class<T> entityClass) {
    return ebeanServer.find(entityClass)
        .where()
        .findList();
  }

  public <T extends BaseModel> Optional<T> findByField(String field, String value, Class<T> entityClass) {
    return Optional.ofNullable(ebeanServer.find(entityClass)
        .where().eq(field, value)
        .findUnique()
    );
  }

  public <T extends BaseModel> Optional<T> findById(Long id, Class<T> entityClass) {
    return Optional.ofNullable(ebeanServer.find(entityClass).setId(id).findUnique());
  }

//  public <T extends BaseModel> Optional<Long> update(Long id, T object) {
//    Transaction txn = ebeanServer.beginTransaction();
//    Optional<Long> value = Optional.empty();
//    try {
//      T objectSaved = ebeanServer.find((Class<T>) object.getClass()).setId(id).findUnique();
//
//      if (objectSaved != null) {
//        object.update();
//        txn.commit();
//        value = Optional.of(id);
//      }
//
//    } catch (Exception e){
//      System.out.println("!!!!! Exception: " + e);
//      txn.rollback();
//    } finally {
//      txn.end();
//    }
//    return value;
//  }

  public <T extends BaseModel> Optional<Long> delete(Long id, Class<T> entityClass) {
    try {
      final Optional<T> ModelOptional = Optional.ofNullable(ebeanServer.find(entityClass).setId(id).findUnique());
      ModelOptional.ifPresent(c -> c.delete());
      return ModelOptional.map(c -> c.getId());
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public <T extends BaseModel> Long insert(T object) {
    ebeanServer.insert(object);
    return object.getId();
  }

}
