import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;

public class Main {
    @Inject
    BingoSimulator bingoSimulator;


//    @Inject TestInjection testInjection;

    public static void main (String[] args){
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        Main application = container.instance().select(Main.class).get();
        application.run();
        weld.shutdown();
    }


    public void run(){
        bingoSimulator.run();
//testInjection.sayHello();
    }

}
