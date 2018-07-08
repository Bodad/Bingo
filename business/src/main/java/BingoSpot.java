import javax.inject.Inject;


public class BingoSpot {
    LogManager logManager;
    boolean isChecked = false;
    final int number;

    public BingoSpot(LogManager logManager, int number) {
        this.logManager = logManager;
        this.number = number;
//        logManager.log("Created number {} {}", this.number, this.isChecked ? "Checked" : "");
    }

    public void check() {
        isChecked = true;
//        logManager.log("Checked number {}", this.number);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean checkIfNumber(int number) {
        if (this.number == number) {
            check();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%d, %s", number, isChecked ? "Checked" : "Not Checked");
    }
}
