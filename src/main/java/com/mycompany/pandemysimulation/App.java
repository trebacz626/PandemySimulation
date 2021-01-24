package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.MainLoopAgent;
import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.map.Map;
import com.mycompany.pandemysimulation.map.MapBuilder;
import com.mycompany.pandemysimulation.map.MapCreator;
import com.mycompany.pandemysimulation.map.MapManager;
import com.mycompany.pandemysimulation.person.client.ClientFactory;
import com.mycompany.pandemysimulation.person.supplier.SupplierFactory;
import com.mycompany.pandemysimulation.ui.UIManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javafx.application.Platform;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Simulation simulation;
    @Override
    public void start(Stage stage) throws IOException {
          UIManager uIManager = new UIManager(stage);
          MapBuilder builder= new ComplexMap().getMapBuilder();
          Map map = builder.build();
          MapManager mapManager = new MapManager(map);
          simulation = new Simulation(uIManager, mapManager);
          Thread mainThread = new Thread(){
            @Override
            public void run(){
                Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            try {
                                simulation.start();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                Platform.exit();
                            }
                            addAgents(builder.getSimulationObjects());
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
    
    private void addAgents(List<SimulationObject> objectsToAdd) {
        for (SimulationObject so : objectsToAdd) {
            if (so instanceof MainLoopAgent) {
                simulation.addMainLoopAgent((MainLoopAgent) so);
            } else {
                simulation.addSimulationObject(so);
            }
        }
        for (int i = 0; i < 100; i++) {
            simulation.addThreadAgent(ClientFactory.createRandomClient(simulation.getMapManager().getMap().getSpawnPointPedestrian(), simulation.getMapManager().getPedestrianPathFinder()));
        }

        for (int i = 0; i <100; i++) {
            simulation.addThreadAgent(SupplierFactory.createRandomSupplier(simulation.getMapManager().getMap().getSpawnPointRoad(), simulation.getMapManager().getRoadPathFinder()));
        }
    }

}