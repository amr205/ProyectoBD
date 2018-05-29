package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.MySQL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        MySQL.Connect();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        primaryStage.setTitle("Proyecto base de datos");
        Scene scene = new Scene(root, 640, 400);
        //scene.getStylesheets().add(getClass().getResource("css/style.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
