import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BingoMachine {
    @Inject
    LogManager logManager;

    List<Integer> balls;
    List<Integer> ballsCalled;
    Random random = new Random();

    public void startNewGame(){
//        logManager.log("Starting new game");
        balls = new ArrayList<>();
        for (int i=1; i<76; i++){
            balls.add(i);
        }

        ballsCalled = new ArrayList<>();
    }

    public int drawBall(){
        int nextBallIndex = random.nextInt(balls.size());
        int drawnBallNumber = balls.get(nextBallIndex);
        ballsCalled.add(drawnBallNumber);
        balls.remove(nextBallIndex);
        return drawnBallNumber;
    }

    public String getBallsDrawn() {
        StringBuilder stringList = new StringBuilder();
        for (int ball : ballsCalled){
            stringList.append(String.format("%d, ", ball));
        }
        return stringList.toString();
    }
}
