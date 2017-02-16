package boilerhungry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import boilerhungry.webapp.WebApp;


public class Client extends Application {

    private WebApp server;
    private static final String LANDING_PAGE = "http://localhost:8080/";

    @Override
    public void start(Stage stage) throws Exception {
        server = new WebApp(8080);
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
        //System.setProperty("prism.order", "sw");
        //System.setProperty("prism.verbose", "true");
        System.setProperty("org.apache.jasper.compiler.disablejsr199","false");

        launch(args);
    }
}