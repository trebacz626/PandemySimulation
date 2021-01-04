module com.mycompany.pandemysimulation {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.pandemysimulation to javafx.fxml;
    exports com.mycompany.pandemysimulation;
}
