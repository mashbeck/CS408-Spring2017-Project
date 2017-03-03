package boilerhungry;

import boilerhungry.webapp.WebApp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertElementPresentByXPath;

public class ServletTest {

    private WebApp server;

    @Before
    public void prepare() throws Exception {
        server = new WebApp(8080);
        server.start();
        setBaseUrl("http://localhost:8080");
        setScriptingEnabled(false);
    }

    @Test
    public void testTabTitle() {
        beginAt("/home");
        assertElementPresentByXPath("/html/body/nav/div/div[1]/a");
    }

    @Test
    public void testWebPageTitle() {
        beginAt("/home");
//        assertElementPresentByXPath("/html/body/nav/div/div[1]/a");
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

    @Test
    public void testNavigationToOneDiningCourtMenu() {
        beginAt("/home");
        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[1][@class='item active']");
        /* Clicking on "View Menu" for Earhart */
        clickElementByXPath("//*[@id=\"carousel\"]/div/div[1]/div/form/button");
        /* Verifying the location label and the location for Earhart */
        assertElementPresentByXPath("/html/body/div/div[1]/nav/div/div[2]/h4");
        assertElementPresentByXPath("/html/body/div/div[1]/nav/div/div[2]/label");
    }

    @Test
    public void testNavigationToOneDiningCourtMenuAndBack() {
        beginAt("/home");

        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[1][@class='item active']");
        /* Clicking on "View Menu" for Earhart */
        clickElementByXPath("//*[@id=\"carousel\"]/div/div[1]/div/form/button");
        assertElementNotPresentByXPath("//*[@id=\"carousel\"]/div/div[1][@class='item active']");

        /* Verifying the location label and the location for Earhart */
        assertElementPresentByXPath("/html/body/div/div[1]/nav/div/div[2]/h4");
        assertElementPresentByXPath("/html/body/div/div[1]/nav/div/div[2]/label");
        assertTextPresent("1275 First Street, West Lafayette, IN 47906-4231");
        assertTextNotPresent("Late Lunch");

//        assertDownloadedFileEquals("http://localhost:8080/ViewMenu?diningCourt=Earhart");

        /* Going back to home page */
        clickElementByXPath("/html/body/nav/div/ul/li[1]/a");

        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[1][@class='item active']");

        clickElementByXPath("//*[@id=\"carousel\"]/div/div[2]/div/form/button");

        assertTextPresent("1122 West Stadium Avenue, West Lafayette, IN 47906-2102");
        assertTextPresent("Late Lunch");
    }

    @Test
    public void testNotificationTicker() {
        beginAt("/home");
        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[1][@class='item active']");
        assertElementPresentByXPath("//*[@id=\"ticker\"]");
    }

    @Test
    public void testPictureOnHomePage() {
        beginAt("/home");

        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[1]/img");
        assertElementPresentByXPath("//*[@id=\"carousel\"]/div/div[1][@class='item active']");
    }

    @Test
    public void testSettingsTab() {
        beginAt("/home");

        assertElementPresentByXPath("//*[@id=\"navbar-collapsible\"]/ul/li/a");

        assertTextNotPresent("My Foods");
        assertTextNotPresent("Dietary Preferences");

        clickElementByXPath("//*[@id=\"navbar-collapsible\"]/ul/li/a");

        assertElementPresentByXPath("//*[@id=\"navbar-collapsible\"]/ul/li/ul/li[1]/a");
        assertElementPresentByXPath("//*[@id=\"navbar-collapsible\"]/ul/li/ul/li[2]/a");

    }

    @After
    public void refreshOrTearDown() throws Exception {
        server.stop();
    }

}