package boilerhungry;

import boilerhungry.webapp.WebApp;
import org.junit.Before;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 * Created by eric on 2/22/17.
 */
public class ServletTest {

    private WebApp server;

    @Before
    public void prepare() throws Exception {
        server = new WebApp(8080);
        server.start();
        setBaseUrl("http://localhost:8080/home");
        setScriptingEnabled(false);
    }

    @Test
    public void testHome() {
        beginAt("/home");
        assertTitleEquals("Search");
    }

}