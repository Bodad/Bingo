package sudoku;

public class SumGroup extends CellGroup {
    private int desiredSum;
    private int currentSum;

    protected void setDesiredSum(int desiredSum){
        this.desiredSum = desiredSum;
    }

    public int getDesiredSum(){
        return desiredSum;
    }


}
