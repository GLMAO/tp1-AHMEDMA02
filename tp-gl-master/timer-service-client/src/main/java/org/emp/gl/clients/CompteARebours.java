package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private final String name;
    private int remaining;
    private TimerService timerService;

    public CompteARebours(String name, int start) {
        this.name = name;
        this.remaining = start;
        System.out.println("CompteARebours " + name + " initialized with " + start);
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
            if (remaining <= 0) {
                return;
            }
            remaining--;
            System.out.println(name + " -> " + remaining);
            if (remaining <= 0) {
                // unregister from service when countdown reaches 0
                if (timerService != null) {
                    timerService.removeTimeChangeListener(this);
                    System.out.println(name + " finished and unsubscribed.");
                }
            }
        }
    }

}
