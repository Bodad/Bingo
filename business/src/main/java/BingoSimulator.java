import javax.inject.Inject;


public class BingoSimulator {

    @Inject
    LogManager logManager;

    @Inject BingoMachine bingoMachine;

    public void run() {
        logManager.log("Bingo Simulator");
        int numberOfGames = 1000;

        int totalIterations = 0;

        for (int i=0; i<numberOfGames; i++) {
            totalIterations += startNewGame();
        }

        logManager.log("Average Iterations: {}", totalIterations/numberOfGames);
    }

    private int startNewGame(){
        bingoMachine.startNewGame();

        BingoCard bingoCard = new BingoCard(logManager, bingoMachine);
//        logManager.log(bingoCard.toString());

        boolean winner = false;
        int iterations = 0;
        while(!winner){
            iterations++;
            int drawnBall = bingoMachine.drawBall();
            winner = bingoCard.playNumber(drawnBall);




        }

//        logManager.log("Balls Drawn: " + bingoMachine.getBallsDrawn());

//        logManager.log(bingoCard.toString());
        logManager.log(String.format("Player won after %d iterations", iterations));
        return iterations;
    }
}
