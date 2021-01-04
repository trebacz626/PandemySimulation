package com.mycompany.pandemysimulation;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
    private ControlPanel controlPanel;
    private MapPanel mapPanel;
    private InformationPanel informationPanel;
    
    public void start(){
    
    }
    
    public void finish(){
    
    }
    
    @FXML
    private void btnClicked() throws IOException {
        System.out.println("ButtonClicked");
    }
}

