module com.mycompany.pandemysimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.pandemysimulation to javafx.fxml;
    exports com.mycompany.pandemysimulation;
}
