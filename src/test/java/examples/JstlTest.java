package examples;

import boilerhungry.webapp.WebApp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class JstlTest {

    protected org.eclipse.jetty.server.Server server;
    private WebApp webApp;

    @Before
    public void aJettyServer() throws Exception {
        webApp = new WebApp(8080);
        webApp.start();
    }

    @After
    public void stopServer() throws Exception {
        webApp.stop();
    }

    @Test
    public void canServeJspWithTaglib() throws Exception {
        assertThat(resourceWithUrl("http://localhost:8080/test/jstl.jsp"), containsString("10"));
    }

    public String resourceWithUrl(String uri) throws Exception {
        URL url = new URL( uri );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        byte[] response = new byte[ inputStream.available() ];
        inputStream.read(response);

        return new String(response);
    }
}
