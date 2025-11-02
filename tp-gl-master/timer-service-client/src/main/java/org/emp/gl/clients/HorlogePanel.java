package org.emp.gl.clients;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class HorlogePanel extends JPanel implements TimerChangeListener {

    private final JLabel label;
    private TimerService timerService;
    private final String name;

    public HorlogePanel(String name) {
        this.name = name;
        this.setLayout(new BorderLayout());
        label = new JLabel(name + " : --:--:--");
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
                label.setText(name + " : " + time);
            }
        }
    }
}
