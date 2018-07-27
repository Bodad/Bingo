package sudoku;

import java.util.ArrayList;

public class CellGroup {
    private ArrayList<Cell> cells = new ArrayList<>();

    protected void addCell(Cell cell){
        cells.add(cell);
        cell.addGroup(this);
    }

    public int getNumberOfCells(){
        return cells.size();
    }
}
