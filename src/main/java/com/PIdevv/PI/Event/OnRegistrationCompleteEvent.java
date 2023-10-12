package com.PIdevv.PI.Event;

import com.PIdevv.PI.Entities.User;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {


    private User user;

    public OnRegistrationCompleteEvent( User user) {
        super(user);

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
