package org.example.torrehanoi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class HomeController {
    private TowerHanoi game = new TowerHanoi(3, 3, this);

    @FXML
    private Canvas canvas;

    @FXML
    public void initialize() {
        drawProject();
    }

    @FXML
    protected void onShowModalClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modal-settings.fxml"));
            Parent root = fxmlLoader.load();

            ModalSettingsController modalController = fxmlLoader.getController();
            modalController.setHomeController(this);

            Scene scene = new Scene(root, 400, 200);
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Settings");
            modalStage.setScene(scene);
            modalStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawProject() {
        if (canvas == null) return;

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int numTowers = game.getNumTowers();
        int numDisc = game.getNumDisc();
        List<Tower> towers = game.getTowers();

        double towerWidth = 20;
        double towerHeight = 300;
        double towerY = canvas.getHeight() - 390;
        double spacing = canvas.getWidth() / (numTowers + 1);
        double diskHeight = 20;

        for (int i = 0; i < numTowers; i++) {
            double x = spacing * (i + 1) - towerWidth / 2;
            gc.setFill(Color.SANDYBROWN);
            gc.fillRoundRect(x, towerY, towerWidth, towerHeight, 10, 10);

            if (!towers.get(i).isEmpty()) {
                double currentY = towerY + towerHeight - diskHeight;
                for (Disc disc : towers.get(i)) {
                    int currentValue = disc.getValue();
                    gc.setFill(getDiscColor(currentValue, numDisc));

                    double diskWidth = spacing - (20 * (numDisc - currentValue));
                    double diskX = x + (towerWidth / 2) - (diskWidth / 2);

                    gc.fillRoundRect(diskX, currentY, diskWidth, diskHeight, 10, 10);
                    currentY -= diskHeight;
                }
            }
        }

        gc.setFill(Color.SADDLEBROWN);
        double floorY = canvas.getHeight() - 100;
        gc.fillRect(0, floorY, canvas.getWidth(), 30);
    }

    private Color getDiscColor(int discValue, int maxDiscs) {
        int red = (discValue * 20) % 256;
        int green = (discValue * 40) % 256;
        int blue = (discValue * 60) % 256;
        return Color.rgb(red, green, blue);
    }

    public void updateGame(int towers, int discs) {
        game = new TowerHanoi(towers, discs, this);
        drawProject();
    }

    public void updateGame(List<Tower> towers) {
        game.setTowers(towers);
        drawProject();
    }

    @FXML
    protected void onAutoResolveClick() {
        game.autoResolve();
    }
}