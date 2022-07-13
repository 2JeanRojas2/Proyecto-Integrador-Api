package com.NewDestiny.event;

import com.NewDestiny.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private UserEntity user;
    private String aplicationUrl;

    public RegistrationCompleteEvent(UserEntity user, String aplicationUrl) {
        super(user);
        this.user = user;
        this.aplicationUrl = aplicationUrl;
    }
}
