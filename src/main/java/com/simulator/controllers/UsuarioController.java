package com.simulator.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.IOException;


public class UsuarioController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField edadField;

    @FXML
    private void guardarUsuario() {
        try {
            // Obtener los datos del usuario
            String nombre = nombreField.getText();
            int edad = Integer.parseInt(edadField.getText());
            System.out.println("Usuario guardado: " + nombre + ", " + edad + " años");

            // Cargar la nueva vista FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RestauranteView.fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual (Stage)
            Stage stage = (Stage) nombreField.getScene().getWindow();

            // Establecer la nueva escena en la ventana
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la nueva vista.");
        } catch (NumberFormatException e) {
            System.err.println("La edad debe ser un número.");
        }
    }

}
