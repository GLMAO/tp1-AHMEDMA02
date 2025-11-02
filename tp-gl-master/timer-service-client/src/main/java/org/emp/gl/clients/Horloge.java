package org.emp.gl.clients ;

import java.beans.PropertyChangeEvent;

import org.emp.gl.timer.service.TimerService ;
import org.emp.gl.timer.service.TimerChangeListener ;


public class Horloge implements TimerChangeListener {

    String name;
    TimerService timerService;

    public Horloge(String name) {
        this.name = name;
        System.out.println("Horloge " + name + " initialized!");
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

    public void afficherHeure() {
        if (timerService != null)
            System.out.println(name + " affiche " +
                    timerService.getHeures() + ":" +
                    timerService.getMinutes() + ":" +
                    timerService.getSecondes());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }

}
