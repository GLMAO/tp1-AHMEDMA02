package org.emp.gl.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Modernized clock panel for GUI display. Uses larger font, centered text and
 * a simple colored background to look more modern.
 */
public class HorlogePanel extends JPanel implements TimerChangeListener {

    private final JLabel label;
    private TimerService timerService;
    private final String name;

    public HorlogePanel(String name) {
        this.name = name;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(320, 80));
        this.setBackground(new Color(34, 40, 49));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 70, 90), 1, true),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)));

        label = new JLabel(name + "  —  --:--:--");
        label.setForeground(new Color(235, 241, 255));
        label.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label, BorderLayout.CENTER);
    }

    public void setTimerService(TimerService timerService) {
        if (this.timerService != null) {
            this.timerService.removeTimeChangeListener(this);
        }
        this.timerService = timerService;
        if (this.timerService != null) {
            this.timerService.addTimeChangeListener(this);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (timerService != null) {
                String time = String.format("%02d:%02d:%02d", timerService.getHeures(), timerService.getMinutes(), timerService.getSecondes());
                label.setText(name + "  —  " + time);
            }
        }
    }
}
