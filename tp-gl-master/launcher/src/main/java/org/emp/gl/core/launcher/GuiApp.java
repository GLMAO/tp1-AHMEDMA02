package org.emp.gl.core.launcher;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
        // try to set a modern look and feel (Nimbus)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            // ignore - fallback to default
        }

        JFrame frame = new JFrame("Horloge GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top padding
        frame.add(Box.createVerticalStrut(8), BorderLayout.NORTH);

        // Center panel with dark background and spacing
        JPanel center = new JPanel();
        center.setBackground(new Color(20, 24, 28));
        center.setLayout(new GridLayout(0, 1, 8, 8));
        center.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        TimerService timerService = new DummyTimeServiceImpl();

        HorlogePanel hp1 = new HorlogePanel("GUI-1");
        hp1.setTimerService(timerService);
        center.add(hp1);

        HorlogePanel hp2 = new HorlogePanel("GUI-2");
        hp2.setTimerService(timerService);
        center.add(hp2);

        HorlogePanel hp3 = new HorlogePanel("GUI-3");
        hp3.setTimerService(timerService);
        center.add(hp3);

        frame.add(center, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(420, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
