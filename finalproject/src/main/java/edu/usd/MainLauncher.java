package edu.usd;

import edu.usd.ConversionService.MainApp;
import edu.usd.GradeService.GradeUIApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btnGradeService = new Button("Launch Grade Service");
        btnGradeService.setOnAction(e -> launchGradeService());

        Button btnPhysicsService = new Button("Launch Physics Service");
        btnPhysicsService.setOnAction(e -> launchPhysicsService());

        Button btnConversionService = new Button("Launch Conversion Service");
        btnConversionService.setOnAction(e -> launchConversionService());

        VBox root = new VBox(10, btnGradeService, btnPhysicsService, btnConversionService);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Main Launcher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void launchGradeService() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GradeCalculator.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Grade Calculator");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void launchPhysicsService() {
        // Logic to launch PhysicsService
    }

    private void launchConversionService() {
        MainApp conversionApp = new MainApp();
        Stage stage = new Stage();
//        stage.setScene(new Scene(conversionApp.getRootNode(), 300, 250));
        conversionApp.start(stage);
        stage.setTitle("Conversion Service");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
