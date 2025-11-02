package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge ;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl ;
import org.emp.gl.timer.service.TimerService ;
import java.util.Random;


public class App {

    public static void main(String[] args) {

        testDuTimeService();
    }

    private static void testDuTimeService() {
        TimerService timerService = new DummyTimeServiceImpl();

        Horloge h1 = new Horloge("Num 1");
        h1.setTimerService(timerService);

        Horloge h2 = new Horloge("Num 2");
        h2.setTimerService(timerService);

        CompteARebours c1 = new CompteARebours("C1", 5);
        c1.setTimerService(timerService);

        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            int start = 10 + rnd.nextInt(11); 
            CompteARebours c = new CompteARebours("CR-" + (i + 1), start);
            c.setTimerService(timerService);
        }

        try {
            Thread.sleep(30_000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
