package sudoku;

public class NineGroup extends SumGroup {
    private String groupType;
    private int groupNumber;

    public NineGroup(String groupType, int groupNumber, Cell[] cells) {
        setDesiredSum(45);
        this.groupNumber = groupNumber;
        this.groupType = groupType;

        for (Cell cell : cells){
            addCell(cell);
        }
    }

    @Override
    public String toString() {
        return String.format("%s-%d", groupType, groupNumber);
    }
}
