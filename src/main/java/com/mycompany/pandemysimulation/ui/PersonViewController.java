
package com.mycompany.pandemysimulation.ui;

import com.mycompany.pandemysimulation.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 * A controller for Person View.
 *
 * @author kacper
 * @param <T>
 */
public abstract class PersonViewController<T extends Person> extends SimulationObjectViewController<T> {

    @FXML
    private Text isSick;
    @FXML
    private Text isVaccinated;
    @FXML
    private Button removeButton;

    /**
     * Updates view state
     */
    @Override
    public void update() {
        super.update();
        if (getSimulationObject() != null) {
            isSick.setText(String.valueOf(getSimulationObject().isSick()));
            isVaccinated.setText(String.valueOf(getSimulationObject().isVaccinated()));
            removeButton.setOnMouseClicked(event -> {
                getInfromationPanel().onRemove();
                getSimulation().removeThreadAgent(getSimulationObject());
                removeSimulationObject();
            });
        }
    }

}
