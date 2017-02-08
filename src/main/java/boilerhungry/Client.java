package boilerhungry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.eclipse.jetty.server.Server;


public class Client extends Application {

    private Server server;
    private static final String LANDING_PAGE = "http://localhost:8080/boilerhungry";

    @Override
    public void start(Stage stage) throws Exception {
        WebApp webApp = new WebApp();
        server = webApp.createServer();
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

    public static void main(String[] args) {
        launch(args);
    }
}