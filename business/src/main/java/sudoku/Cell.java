package sudoku;

import java.util.ArrayList;

public class Cell {
    private int row;
    private int column;
    private ArrayList<SumGroup> groups = new ArrayList<>();

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", row, column);
    }

    public void addGroup(CellGroup cellGroup) {
        groups.add((SumGroup)cellGroup);
    }
}
