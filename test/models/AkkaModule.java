package models;

import akka.actor.ActorSystem;
import com.google.inject.AbstractModule;
import play.db.ebean.DefaultEbeanConfig;
import play.db.ebean.EbeanConfig;

import java.util.HashMap;

public class AkkaModule extends AbstractModule {

    @Override
    public void configure() {
        bind(ActorSystem.class).toInstance(ActorSystem.create("test"));
    }
}
