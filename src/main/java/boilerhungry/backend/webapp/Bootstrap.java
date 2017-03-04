package boilerhungry.backend.webapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Bootstrap extends Application {

    private static WebApp server;
    private static final int PORT = 8080;
    private static final String LANDING_PAGE = String.format("http://localhost:%d/", PORT);
    private static final boolean USING_GUI = false;

    @Override
    public void start(Stage stage) throws Exception {
        System.setProperty("org.apache.jasper.compiler.disablejsr199","false");

        stage.setTitle("BoilerHungry");
        Scene scene = new Scene(new Browser(LANDING_PAGE), 1000, 667, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (server != null) {
            server.stop();
        }
    }

    public static void main(String[] args) throws Exception {
        server = new WebApp(PORT);
        server.start();
        if (USING_GUI) {
            launch(args);
        }
    }
}