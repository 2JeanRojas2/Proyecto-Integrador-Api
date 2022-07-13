package com.NewDestiny.event.listener;

import com.NewDestiny.event.RegistrationCompleteEvent;
import com.NewDestiny.model.entity.UserEntity;
import com.NewDestiny.service.implementation.EmailSenderService;
import com.NewDestiny.service.implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //crear el token de verificacion para el usuario con el link
        UserEntity user = event.getUser();
        String token = UUID.randomUUID().toString();// UUID generador de números pseudoaleatorios criptográficamente fuerte
        userService.saveVerificationTokenForUser(token,user);
        //eviar mail al usuario
        String url = event.getAplicationUrl() + "/verifyRegistration?token=" + token;
        //send verification email()
        emailSenderService.sendSimpleEmail(user.getEmail(), "Click the link to verify your account: " + url,"Confirmacion usuario");
    }
}
