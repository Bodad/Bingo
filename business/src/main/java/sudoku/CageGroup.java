package sudoku;

import java.util.ArrayList;

public class CageGroup extends SumGroup{
    public CageGroup(int desiredSum, Cell[] cells) {
        super();

        for (Cell cell : cells){
            addCell(cell);
        }

        setDesiredSum(desiredSum);
    }

    public void calculate() {
        ArrayList<int> cellContents = calculateSumNumbers(getDesiredSum(), getNumberOfCells());
    }

    private ArrayList<int> calculateSumNumbers(int desiredSum, int numberOfCells) {
        ArrayList<Integer> = new ArrayList<>();
        




    }
}
