package sudoku;

import bingo.BingoCard;
import bingo.BingoMachine;
import common.LogManager;

import javax.inject.Inject;


public class SudokuSimulator {

    @Inject
    LogManager logManager;


    public void run() {
        logManager.log("Sudoku Simulator");

        SudokuBoard board = new SudokuBoard(logManager);
        logManager.log(board.toString());

    }


}
