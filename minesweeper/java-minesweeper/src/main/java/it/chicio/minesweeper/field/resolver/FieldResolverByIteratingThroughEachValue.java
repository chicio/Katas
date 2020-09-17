package it.chicio.minesweeper.field.resolver;

import it.chicio.minesweeper.field.Field;

public class FieldResolverByIteratingThroughEachValue implements FieldResolver {
    public Field resolve(Field field) {
        Field resolvedField = new Field(field.numberOfRows(), field.numberOfColumn());
        for (int row = 0; row < field.numberOfRows(); row++) {
            for (int column = 0; column < field.numberOfColumn(); column++) {
                if (isABomb(field.get(row, column))) {
                    resolvedField.set(row, column, "*");
                } else {
                    resolvedField.set(row, column, String.valueOf(getNumberOfBombsInNeighbours(field, row, column)));
                }
            }
        }
        return resolvedField;
    }

    private boolean isABomb(String value) {
        return value.equals("*");
    }

    private int getNumberOfBombsInNeighbours(Field field, int row, int column) {
        int numberOfBombsNearCell = 0;
        for (String value : field.neighboursOf(row, column)) {
            if (isABomb(value)) {
                numberOfBombsNearCell++;
            }
        }
        return numberOfBombsNearCell;
    }
}
