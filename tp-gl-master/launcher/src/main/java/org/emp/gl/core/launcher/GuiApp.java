package org.emp.gl.core.launcher;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.emp.gl.clients.HorlogePanel;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

public class GuiApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
        });
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Horloge GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));

        TimerService timerService = new DummyTimeServiceImpl();

        HorlogePanel hp1 = new HorlogePanel("GUI-1");
        hp1.setTimerService(timerService);
        frame.add(hp1);

        HorlogePanel hp2 = new HorlogePanel("GUI-2");
        hp2.setTimerService(timerService);
        frame.add(hp2);

        frame.pack();
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
