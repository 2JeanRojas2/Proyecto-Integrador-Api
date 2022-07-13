package com.NewDestiny.controller;

import com.NewDestiny.event.RegistrationCompleteEvent;
import com.NewDestiny.model.dto.UserModelDto;
import com.NewDestiny.model.entity.UserEntity;
import com.NewDestiny.model.entity.VerificationToken;
import com.NewDestiny.service.implementation.EmailSenderService;
import com.NewDestiny.service.implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EmailSenderService emailSenderService;

    //Cuando se registre pasara el UserModelDto donde tendra los atributos
    @PostMapping("/register")
    public HttpStatus registerUser(@RequestBody UserModelDto userModelDto, final HttpServletRequest request){
        UserEntity user = userService.registerUser(userModelDto); // aqui guarda al usuario
        publisher.publishEvent(new RegistrationCompleteEvent( // Aqui se crea el evento que le enviara el token al usuario en un email y ese email se usa para verificar al usuario
                user,
                applicationUrl(request)
        ));
        return HttpStatus.CREATED;
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User verifies Successfully";
        }
        return "Bad User";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request){
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        UserEntity user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Sent";
    }

    private void resendVerificationTokenMail(UserEntity user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();
        //send verification email()
        emailSenderService.sendSimpleEmail(user.getEmail(), "Click the link to verify your account: " + url, "Reenvio verificacion usuario");
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();

    }
}
