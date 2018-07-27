package bingo;

import common.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BingoCard {

    BingoSpot[][] bingoSpots = new BingoSpot[5][5];
    LogManager logManager;
    BingoMachine bingoMachine;

    public BingoCard(LogManager logManager, BingoMachine bingoMachine) {
        this.bingoMachine = bingoMachine;
        this.logManager = logManager;
        fillCardWithRandomNumbers();

        bingoSpots[2][2].check();
    }

    private void fillCardWithRandomNumbers() {
        Random random = new Random();
        List<List<Integer>> numbers = new ArrayList<>();

        for (int col = 0; col < 5; col++) {
            List<Integer> columnNumbers = new ArrayList();
            int columnBeginning = col * 15;
            for (int number = 0; number < 15; number++) {
                columnNumbers.add(columnBeginning + number);
            }
            numbers.add(columnNumbers);
        }

        for (int col = 0; col < 5; col++) {
            List<Integer> columnNumbers = numbers.get(col);
            for (int row = 0; row < 5; row++) {
                int nextInt = random.nextInt(15 - row);
                BingoSpot bingoSpot = new BingoSpot(logManager, columnNumbers.get(nextInt) + 1);
                bingoSpots[row][col] = bingoSpot;
                columnNumbers.remove(nextInt);
            }
        }
    }

    public Integer playNumber(int number) {
        boolean hasNumber = false;
        int hasBingo = 0;

        int column = (number - 1) / 15;

        for (int i = 0; i < 5; i++) {
            BingoSpot bingoSpot = bingoSpots[i][column];

            if (bingoSpot.checkIfNumber(number)) {
                hasNumber = true;
                break;
            }
        }

        if (hasNumber) {
            hasBingo = checkForWin();
        }

//        logManager.log("Playing number {} {} {}", number, hasNumber ? "check":"", hasBingo ? " BINGO":"");

        return hasBingo;
    }

    public Integer checkForWin() {
        boolean allowStandardBingo = true;
        boolean allowPostageStamps = false;
        boolean allowDiamonds = true;
        boolean allowCorners = true;

        boolean won = false;
       // howYouWon();


        if (allowPostageStamps && checkPostageStamps())
            return 4;
        if (allowCorners && checkCorners())
            return 3;
        if (allowDiamonds && checkDiamonds())
            return 2;
        if (allowStandardBingo && checkStandardBingo())
            return 1;
        return 0;
    }



    private boolean checkStandardBingo() {
        boolean won = false;

        //         check for all spots in a row
        for (int row = 0; row < 5; row++) {
            won = true;
            for (int col = 0; col < 5; col++) {
                won = isChecked(row, col);
                if (!won) break;
            }
        }

        if (won) return true;

        // check for all spots in a column
        for (int col = 0; col < 5; col++) {
            won = true;
            for (int row = 0; row < 5; row++) {
                won = isChecked(row, col);
                if (!won) break;
            }
        }

        if (won) return true;

        // diagonals
        if (hasSpots(new int[]{1, 1}, new int[]{2, 2}, new int[]{3, 3}, new int[]{4, 4}, new int[]{5, 5})) return true;
        if (hasSpots(new int[]{5, 1}, new int[]{4, 2}, new int[]{3, 3}, new int[]{2, 4}, new int[]{1, 5})) return true;

        return false;
    }

    private boolean checkCorners() {
        if (hasSpots(new int[]{1, 1}, new int[]{1, 5}, new int[]{5, 5}, new int[]{5, 1})) return true;
        if (hasSpots(new int[]{2, 2}, new int[]{2, 4}, new int[]{4, 4}, new int[]{4, 2})) return true;

        return false;
    }

    private boolean checkDiamonds() {
        if (hasSpots(new int[]{3, 1}, new int[]{1, 3}, new int[]{3, 5}, new int[]{5, 3})) return true;
        if (hasSpots(new int[]{3, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 3})) return true;

        return false;
    }

    private boolean checkPostageStamps() {
        if (hasPostageStamp(1, 1)) return true;
        if (hasPostageStamp(1, 2)) return true;
        if (hasPostageStamp(1, 3)) return true;
        if (hasPostageStamp(1, 4)) return true;

        if (hasPostageStamp(2, 1)) return true;
        if (hasPostageStamp(2, 4)) return true;

        if (hasPostageStamp(3, 1)) return true;
        if (hasPostageStamp(3, 4)) return true;

        if (hasPostageStamp(4, 1)) return true;
        if (hasPostageStamp(4, 2)) return true;
        if (hasPostageStamp(4, 3)) return true;
        if (hasPostageStamp(4, 4)) return true;

        return false;
    }

    private boolean hasPostageStamp(int row, int col) {
        return hasSpots(new int[]{row, col}, new int[]{row, col + 1}, new int[]{row + 1, col}, new int[]{row + 1, col + 1});
    }

    private boolean hasSpots(int[]... spots) {
        boolean yes = true;
        for (int[] spot : spots) {
            yes = isChecked(spot[0] - 1, spot[1] - 1);
            if (!yes) return false;
        }
        return true;
    }

    public boolean isChecked(int row, int col) {
        return bingoSpots[row][col].isChecked();
    }

    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder();
        stringList.append("\r\n");
        for (int i = 0; i < 5; i++) {
            StringBuilder rowStringList = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                BingoSpot bingoSpot = bingoSpots[i][j];
                boolean isChecked = bingoSpot.isChecked();
                rowStringList.append(String.format("%s%2d%s\t", isChecked ? "[" : " ", bingoSpot.number, isChecked ? "]" : " "));
            }
            stringList.append(rowStringList.toString() + "\r\n");
        }
        return stringList.toString();
    }

    public static void setWinType(int typeOfWin)
    {

    }
    public static Integer getWinType ()
    {
        return 1;
    }

}

