package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.ui.UIManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Platform;

/**
 * JavaFX App
 */
public class App extends Application {

    public static UIManager uiManager;
    public static Simulation simulation;

    @Override
    public void start(Stage stage) throws IOException {
          uiManager = new UIManager(stage);
          simulation = new Simulation(uiManager);
          Thread mainThread = new Thread(){
            @Override
            public void run(){
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            simulation.start();
                        }
                    });
                while (true) {
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            simulation.update();
                        }
                    });
                }
            }
        };
        mainThread.setDaemon(true);
        mainThread.start();
    }



    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}


/*
Plan:
Add Supplier to Map
Information Panel for every type of Object
Add Wholesale and Shops
Make World Graph
Add just travel between
Add Shoping
Add Intersections

*/