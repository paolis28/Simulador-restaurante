package com.simulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RestauranteControllerVista {
    @FXML
    private Label label;
    @FXML
    private ImageView backgroundImage;

    @FXML
    public void initialize() {
        // Este método se ejecuta automáticamente después de cargar el archivo FXML.
        System.out.println("Vista Restaurante inicializada.");

        // Cargar la imagen desde el classpath
        Image image = new Image(getClass().getResourceAsStream("/img/fondo-p.png"));
        backgroundImage.setImage(image);

        // Ajustar el tamaño de la imagen para que cubra toda la vista
        backgroundImage.setFitWidth(1000);
        backgroundImage.setFitHeight(600);
    }
}
