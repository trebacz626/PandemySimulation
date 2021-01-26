module com.mycompany.pandemysimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.pandemysimulation to javafx.fxml;
    opens com.mycompany.pandemysimulation.ui to javafx.fxml;
    exports com.mycompany.pandemysimulation;
    exports com.mycompany.pandemysimulation.core;
    exports com.mycompany.pandemysimulation.core.data;
    exports com.mycompany.pandemysimulation.core.map;
    exports com.mycompany.pandemysimulation.core.ui;
    exports com.mycompany.pandemysimulation.core.utils;
    exports com.mycompany.pandemysimulation.data;
    exports com.mycompany.pandemysimulation.map;
    exports com.mycompany.pandemysimulation.map.tile;
    exports com.mycompany.pandemysimulation.person;
    exports com.mycompany.pandemysimulation.person.client;
    exports com.mycompany.pandemysimulation.person.supplier;
    exports com.mycompany.pandemysimulation.product;
    exports com.mycompany.pandemysimulation.shop;
    exports com.mycompany.pandemysimulation.shop.retailshop;
    exports com.mycompany.pandemysimulation.shop.wholesaleshop;
    exports com.mycompany.pandemysimulation.sync;
    exports com.mycompany.pandemysimulation.ui;
}
