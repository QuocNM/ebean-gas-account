package service;

import io.ebean.Expr;
import models.GasAccount;
import models.group.GroupType;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class GasAccountService extends AbstractService<GasAccount> {

  @Inject
  public GasAccountService(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
    super(ebeanConfig, executionContext, GasAccount.class);
  }

  public Optional<List<GasAccount>> getSubAccounts(Long id) {
    return Optional.ofNullable(ebeanServer.find(GasAccount.class)
        .where(
            Expr.and(
                Expr.like("groupType", String.valueOf(GroupType.SUB_CLI)),
                Expr.like("ownerId", id.toString())
            )
        )
        .findList()
    );
  }

  public Optional<List<GasAccount>> getOneSubAccount(Long ownerId, Long subAccountId) {
    return Optional.ofNullable(ebeanServer.find(GasAccount.class)
        .where(
            Expr.and(
                Expr.like("groupType", String.valueOf(GroupType.SUB_CLI)),
                Expr.and(
                    Expr.like("ownerId", ownerId.toString()),
                    Expr.like("id", subAccountId.toString())
                )
            )
        )
        .findList()
    );

  }

  public Optional<Long> update(GasAccount object) {
    Optional<Long> value = Optional.empty();

    try {

      Long id = object.getId();
      Optional<GasAccount> gasAccountOptional = findById(id);
      if (gasAccountOptional.isPresent()) {
        GasAccount gasAccount = gasAccountOptional.get();
        gasAccount.prepareToUpdate(object);
        gasAccount.update();
        value = Optional.of(id);
      }

    } catch (Exception ignored) {

    }
    return value;
  }

}
