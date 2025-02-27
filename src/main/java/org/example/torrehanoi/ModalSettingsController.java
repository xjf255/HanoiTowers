package org.example.torrehanoi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ModalSettingsController {
    private HomeController homeController;

    @FXML
    private TextField towerField, diskField;

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    protected void onSaveButtonClick(ActionEvent event) {
        List<Integer> values = getValues();
        if (values != null) {
            // send new values to canvas
            if (homeController != null) {
                homeController.updateGame(values.get(0), values.get(1));
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private List<Integer> getValues() {
        List<Integer> values = new ArrayList<>();

        String towerText = towerField.getText();
        String diskText = diskField.getText();

        if (towerText == null || towerText.trim().isEmpty() || diskText == null || diskText.trim().isEmpty()) {
            showError("Por favor, complete todos los campos.");
            return null;
        }

        try {
            int numTowers = Integer.parseInt(towerText.trim());
            int numDisks = Integer.parseInt(diskText.trim());

//            if ((numTowers <= 2 || numDisks <= 2)) {
//                showError("Los números deben ser mayores que 3.");
            if ((numTowers < 3 || numDisks < 3) || (numTowers > 10 || numDisks > 10)) {
                showError("Los números deben ser entre 3 y 10.");
                return null;
            }

            values.add(numTowers);
            values.add(numDisks);
            return values;
        } catch (NumberFormatException e) {
            showError("Por favor, ingrese números válidos.");
            return null;
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Validación");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
