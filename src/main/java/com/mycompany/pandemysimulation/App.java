package com.mycompany.pandemysimulation;

import com.mycompany.pandemysimulation.core.MainLoopAgent;
import com.mycompany.pandemysimulation.core.Simulation;
import com.mycompany.pandemysimulation.core.SimulationObject;
import com.mycompany.pandemysimulation.core.map.Map;
import com.mycompany.pandemysimulation.data.DataManager;
import com.mycompany.pandemysimulation.map.ComplexMap;
import com.mycompany.pandemysimulation.map.MapBuilder;
import com.mycompany.pandemysimulation.map.MapManager;
import com.mycompany.pandemysimulation.person.client.ClientFactory;
import com.mycompany.pandemysimulation.person.supplier.SupplierFactory;
import com.mycompany.pandemysimulation.ui.UIManager;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private Simulation simulation;

    @Override
    public void start(Stage stage) throws IOException {
        MapBuilder builder = new ComplexMap().getMapBuilder();
        Map map = builder.build();
        MapManager mapManager = new MapManager(map);
        simulation = new Simulation(new UIManager(stage), mapManager, new DataManager());
        Thread mainThread = new Thread() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
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
        for (int i = 0; i < 1000; i++) {
            simulation.addThreadAgent(ClientFactory.createRandomClient(simulation));
        }

        for (int i = 0; i < 1000; i++) {
            simulation.addThreadAgent(SupplierFactory.createRandomSupplier(simulation));
        }
    }

}
