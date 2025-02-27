package org.example.torrehanoi;

public class Disc {
    private Integer value;

    public Disc() {}

    public Disc(Integer value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("El valor del disco debe ser un número positivo.");
        }
        this.value = value;
    }

    public int getValue() {
        if (value == null) {
            throw new IllegalStateException("El valor del disco no ha sido inicializado.");
        }
        return value;
    }

    public void setValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("El valor del disco debe ser un número positivo.");
        }
        this.value = value;
    }
}
