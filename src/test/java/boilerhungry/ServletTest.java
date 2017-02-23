package boilerhungry;

import boilerhungry.webapp.WebApp;
import org.junit.After;
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
    public void testTabTitle() {
        beginAt("/home");
        assertTitleEquals("BoilerHungry");
    }

    @Test
    public void testWebPageTitle() {
        beginAt("/home");
        assertTextPresent("BoilerHungry");
    }

    @Test
    public void testDifferentDiningCourtTitles() {
        beginAt("/home");
        /* Test for Earhart */
        assertElementPresentByXPath("//*[@id='carousel']/div/div[1]/div/hgroup/h2");
        /* Test for Ford */
        assertElementPresentByXPath("//*[@id='carousel']/div/div[2]/div/hgroup/h2");
    }

    @Test
    public void testDiningCourtOnPage() throws InterruptedException {
        beginAt("/home");
        /* Test for Earhart */
        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[1][@class='item active']");
        // Testing for the little ball in the bottom
        assertElementPresentByXPath("//*[@id=\"carousel\"]/ol/li[1][@class='active']");
        /* Test for Ford */
        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[2][@class='item']");
    }

    @After
    public void refreshOrTearDown() throws Exception {
        server.stop();
    }

}