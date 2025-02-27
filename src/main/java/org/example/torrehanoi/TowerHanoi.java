package org.example.torrehanoi;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class TowerHanoi {
    private int numTowers;
    private int numDisc;
    private List<Tower> towers = new ArrayList<>();
    private HomeController homeController;

    public TowerHanoi(int numTowers, int numDisc, HomeController homeController) {
        if (numTowers < 3) {
            throw new IllegalArgumentException("El número de torres debe ser al menos 3.");
        }
        if (numDisc < 3) {
            throw new IllegalArgumentException("El número de discos debe ser al menos 3.");
        }
        this.numTowers = numTowers;
        this.numDisc = numDisc;
        this.homeController = homeController;
        initializeTowers();
        insertInitialValues();
    }

    public TowerHanoi(HomeController homeController) {
        this(3, 3, homeController);
    }

    private void initializeTowers() {
        for (int i = 0; i < numTowers; i++) {
            towers.add(new Tower());
        }
    }

    private void insertInitialValues() {
        for (int i = numDisc; i > 0; i--) {
            towers.get(0).push(new Disc(i));
        }
    }

    public void autoResolve() {
        if (numTowers >= 3) {
            solveHanoiGeneralized(numDisc, 0, numTowers - 1, 1);
        } else {
            System.out.println("La resolución automática requiere al menos 3 torres.");
        }
    }

    private void solveHanoiGeneralized(int n, int from, int to, int aux) {
        if (n == 1) {
            moveDisc(from, to);
            return;
        }

        if (numTowers > 3) {
            int k = n / 2;
            solveHanoiGeneralized(k, from, aux, to);
            solveHanoiGeneralized(n - k, from, to, aux);
            solveHanoiGeneralized(k, aux, to, from);
        } else {
            solveHanoiGeneralized(n - 1, from, aux, to);
            moveDisc(from, to);
            solveHanoiGeneralized(n - 1, aux, to, from);
        }
    }

    private void moveDisc(int from, int to) {
        if (!towers.get(from).isEmpty()) {
            Disc disc = towers.get(from).pop();
            towers.get(to).push(disc);
            System.out.println("Moviendo disco " + disc.getValue() + " de Torre " + from + " a Torre " + to);
            Platform.runLater(() -> homeController.updateGame(getTowers()));
        }
    }

    public int getNumDisc() {
        return numDisc;
    }

    public int getNumTowers() {
        return numTowers;
    }

    public List<Tower> getTowers() {
        return new ArrayList<>(towers);
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }
}