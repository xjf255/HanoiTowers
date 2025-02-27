package org.example.torrehanoi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Tower extends Stack<Disc> {
    private List<Disc> discs;

    public Tower() {
        this.discs = new ArrayList<>();
    }

    public int getValue() {
        if (this.isEmpty()) return 0;
        return discs.get(discs.size() - 1).getValue();
    }

    public int size() {
        return discs.size();
    }

    public Disc push(Disc newDisc) {
        if (this.isEmpty() || newDisc.getValue() < this.getValue()) {
            discs.add(newDisc);
        } else {
            throw new IllegalArgumentException("No se puede colocar un disco más grande sobre uno más pequeño.");
        }
        return newDisc;
    }

    public boolean isEmpty() {
        return discs.isEmpty();
    }

    public Disc pop() {
        if (this.isEmpty()) return null;
        return discs.remove(discs.size() - 1);
    }

    @Override
    public Iterator<Disc> iterator() {
        // Devuelve el iterador de la lista interna
        return discs.iterator();
    }
}
