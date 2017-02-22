package boilerhungry.backend.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import boilerhungry.webapp.WebApp;


public class Bootstrap extends Application {

    private WebApp server;
    private static final int PORT = 8080;
    private static final String LANDING_PAGE = String.format("http://localhost:%d/home", PORT);
    private static final boolean USING_GUI = false;

    @Override
    public void start(Stage stage) throws Exception {
        server = new WebApp(PORT);
        server.start();

        stage.setTitle("BoilerHungry");
        Scene scene = new Scene(new Browser(LANDING_PAGE), 1000, 667, Color.web("#666970"));
        stage.setScene(scene);
        //scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (server != null) {
            server.stop();
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setProperty("prism.order", "sw");
        //System.setProperty("prism.verbose", "true");
        System.setProperty("org.apache.jasper.compiler.disablejsr199","false");
        if (USING_GUI) {
            launch(args);
        } else {
            Bootstrap launch = new Bootstrap();
            launch.server = new WebApp(PORT);
            launch.server.start();
        }
    }
}