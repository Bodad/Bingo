package sudoku;

import common.LogManager;

import java.util.ArrayList;

public class SudokuBoard {


    LogManager logManager;

    public SudokuBoard(LogManager logMangager){
        this();
        this.logManager =logMangager;
    }


    private Cell[][] cells = new Cell[9][9];

    private NineGroup[] rows = new NineGroup[9];
    private NineGroup[] columns = new NineGroup[9];
    private NineGroup[] blocks = new NineGroup[9];
    private ArrayList<CageGroup> cageGroups = new ArrayList<>();

    public SudokuBoard(){
        createCells();
        createRowGroups();
        createColumnGroups();
        createBlockGroups();
    }

    public void addCageGroup(int desiredSum, int... cellCoordinates){
        Cell[] cells = getCells(cellCoordinates);
        CageGroup cageGroup = new CageGroup(desiredSum, cells);
        cageGroups.add(cageGroup);
        cageGroup.calculate();
    }

    private Cell[] getCells(int[] cellCoordinates) {
        ArrayList<Cell> newCells = new ArrayList<>();
        for (int cellCoordinate : cellCoordinates){
            int row = cellCoordinate/10 - 1;
            int col = cellCoordinate%10 - 1;
            newCells.add(cells[row][col]);
        }
        return (Cell[])newCells.toArray();
    }

    private void createBlockGroups() {
        for (int h=0; h<7; h+=3) {
            for (int i =0; i <7; i+=3) {
                Cell[] theCells = new Cell[9];
                for (int j = 0; j < 3; j++) {                               //rows
                    for (int k = 0; k < 3; k++) {                            //columns
                        theCells[3*j+k] = cells[h+j][k+i];
                        if (j+k == 4)
                            blocks[h+(i/3)] = new NineGroup("Block", h+(i/3), theCells);
                    }
                }
            }
        }
    }

    private void createColumnGroups() {
        for (int col=0; col<9; col++){
            Cell[] theCells = new Cell[9];
            for (int i=0; i<9; i++){
                theCells[i] = cells[i][col];
            }

            columns[col] =  new NineGroup("Column", col, theCells);
        }
    }

    private void createRowGroups() {
        for (int row=0; row<9; row++){
            rows[row] = new NineGroup("Row", row, cells[row]);
        }
    }

    private void createCells() {
        for (int row=0; row<9; row++){
            cells[row] = new Cell[9];
            for (int column=0; column<9; column++){
                cells[row][column] = new Cell(row, column);
            }
        }
    }

    @Override
    public String toString() {
        return "Hey";
    }
}
