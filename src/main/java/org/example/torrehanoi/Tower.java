package org.example.torrehanoi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tower implements Iterable<Disc> {
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

    public boolean push(Disc newDisc) {
        if (this.isEmpty() || newDisc.getValue() < this.getValue()) {
            discs.add(newDisc);
            return true;
        } else {
            return false;
        }
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
