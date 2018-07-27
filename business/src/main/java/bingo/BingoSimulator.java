package bingo;

import common.LogManager;

import javax.inject.Inject;


public class BingoSimulator {

    @Inject
    LogManager logManager;

    @Inject BingoMachine bingoMachine;

    public void run() {
        logManager.log("Bingo Simulator");
        int numberOfGames = 1000000;
        int[] importantNumbers = new int[5];
        int totalIterations = 0;

        for (int i=0; i<numberOfGames; i++) {
            importantNumbers = startNewGame(importantNumbers);
            //totalIterations += startNewGame();
        }

        logManager.log("Average Iterations: {}", importantNumbers[0]/numberOfGames);
        logManager.log("Standard Wins: {}", importantNumbers[1]);
        logManager.log("Diamond Wins: {}", importantNumbers[2]);
        logManager.log("Corner Wins: {}", importantNumbers[3]);
        logManager.log("Postage Stamp Wins: {}", importantNumbers[4]);
    }

    private int[] startNewGame(int[] theImportantNumbers){
        bingoMachine.startNewGame();

        BingoCard bingoCard = new BingoCard(logManager, bingoMachine);
   //   logManager.log(bingoCard.toString());

        boolean winner = false;
        int[] importantNumbers = new int[5];
        importantNumbers[0] = 0;
        while(!winner){
            importantNumbers[0]++;
            int drawnBall = bingoMachine.drawBall();
            int holder = bingoCard.playNumber(drawnBall);
            if (holder == 0)
                winner = false;
            else {
                if (holder == 1)
                    importantNumbers[1]++;
                if (holder == 2)
                    importantNumbers[2]++;
                if (holder == 3)
                    importantNumbers[3]++;
                if (holder == 4)
                    importantNumbers[4]++;

                for (int i=0; i<5; i++)
                {
                    importantNumbers[i] = importantNumbers[i] + theImportantNumbers[i];
                }

                winner = true;
            }
        }

  //      logManager.log("Balls Drawn: " + bingoMachine.getBallsDrawn());

  //      logManager.log(bingoCard.toString());
  //      logManager.log(String.format("Player won after %d iterations", iterations));
        return importantNumbers;
    }
}
