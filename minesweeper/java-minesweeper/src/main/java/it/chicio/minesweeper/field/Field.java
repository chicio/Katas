package it.chicio.minesweeper.field;

import java.util.ArrayList;
import java.util.Arrays;

public class Field {
    private String[][] matrixFieldRepresentation;

    public Field(String[][] matrixFieldRepresentation) {
        this.matrixFieldRepresentation = matrixFieldRepresentation;
    }

    public Field(int numberOfRows, int numberOfColumn) {
        matrixFieldRepresentation = new String[numberOfRows][numberOfColumn];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Field field = (Field) o;
        return Arrays.deepEquals(matrixFieldRepresentation, field.matrixFieldRepresentation);
    }

    private boolean has(int row, int column) {
        return row >= 0 && row < numberOfRows() && column >= 0 && column < numberOfColumn();
    }

    public String get(int row, int column) {
        return matrixFieldRepresentation[row][column];
    }

    public void set(int row, int column, String value) {
        matrixFieldRepresentation[row][column] = value;
    }

    public int numberOfRows() {
        return matrixFieldRepresentation.length;
    }

    public int numberOfColumn() {
        return matrixFieldRepresentation[0].length;
    }

    public ArrayList<String> neighboursOf(int row, int column) {
        ArrayList<String> neighbours = new ArrayList<String>();
        if (has(row, column - 1)) {
            neighbours.add(get(row, column - 1));
        }
        if (has(row, column + 1)) {
            neighbours.add(get(row, column + 1));
        }
        if (has(row + 1, column + 1)) {
            neighbours.add(get(row + 1, column + 1));
        }
        if (has(row + 1, column)) {
            neighbours.add(get(row + 1, column));
        }
        if (has(row + 1, column - 1)) {
            neighbours.add(get(row + 1, column - 1));
        }
        if (has(row - 1, column - 1)) {
            neighbours.add(get(row - 1, column - 1));
        }
        if (has(row - 1, column)) {
            neighbours.add(get(row - 1, column));
        }
        if (has(row - 1, column + 1)) {
            neighbours.add(get(row - 1, column + 1));
        }
        return neighbours;
    }
}
