package models;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.Application;
import play.test.Helpers;

/**
 * Created by quoc.nm on 7/18/2017.
 */
public class BaseTest {

  public static Application app;

  @BeforeClass
  public static void startApp() {
    app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
    Helpers.start(app);
  }

  @AfterClass
  public static void stopApp() {
    Helpers.stop(app);
  }

}
