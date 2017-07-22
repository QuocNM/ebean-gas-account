package models;

import io.ebean.Finder;
import models.group.PermissionType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * Created by quoc.nm on 7/20/2017.
 */
public class ApplicationFeaturePermissionTest extends BaseTest {

  public static final Finder<Long, GasAccount> find = new Finder<>(GasAccount.class);
//
//  @Before
//  public void init() {
//
//    GasAccount gasAccount = new GasAccount();
//    gasAccount.save();
//
//    ApplicationFeaturePermission applicationFeaturePermission = new ApplicationFeaturePermission(
//        gasAccount, 0L, PermissionType.DELETE
//    );
//    applicationFeaturePermission.save();
//
//  }
//
//  @Test
//  public void testAdd() {
//    List<GasAccount> gasAccounts = find.all();
//    System.out.println(gasAccounts.get(0).getAppFeaturePermissions().size());
//  }
}