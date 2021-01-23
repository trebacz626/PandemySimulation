package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.person.ClientFactory;
import com.mycompany.pandemysimulation.person.SupplierFactory;
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

    public static Simulation simulation;
    @Override
    public void start(Stage stage) throws IOException {
          simulation = new Simulation(stage, new ComplexMap());
          Thread mainThread = new Thread(){
            @Override
            public void run(){
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            simulation.start();
                            addAgents();
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

    public static void main(String[] args) {
        launch();
    }
    
    private void addAgents() {
        for (int i = 0; i < 100; i++) {
            simulation.addThreadAgent(ClientFactory.createRandomClient(simulation.getMapManager().getMap().getSpawnPointPedestrian(), simulation.getMapManager().getPedestrianPathFinder()));
        }

        for (int i = 0; i <100; i++) {
            simulation.addThreadAgent(SupplierFactory.createRandomSupplier(simulation.getMapManager().getMap().getSpawnPointRoad(), simulation.getMapManager().getRoadPathFinder()));
        }
    }

}