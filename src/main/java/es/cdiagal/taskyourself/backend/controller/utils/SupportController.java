package es.cdiagal.taskyourself.backend.controller.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SupportController {
    @FXML
    private TextArea supportTextArea;

    @FXML
    public void initialize() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/readme.txt")))) {
            String contenido = reader.lines().collect(Collectors.joining("\n"));
            supportTextArea.setText(contenido);
        } catch (Exception e) {
            supportTextArea.setText("No se pudo cargar el README.");
            e.printStackTrace();
        }
    }
}
