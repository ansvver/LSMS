package library.seat.manage.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import play.modules.guice.GuiceSupport;

public class GuicyDummy extends GuiceSupport {
    protected Injector configure() {
        return Guice.createInjector(new GuicyDummyModel());
    }
}
