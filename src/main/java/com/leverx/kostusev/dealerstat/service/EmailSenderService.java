package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.entity.ConfirmationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmailSenderService {

    private final JavaMailSender emailSender;

    @Async
    public void sendMail(ConfirmationToken confirmToken) throws MailException {
        String recipientAddress = confirmToken.getUser().getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "http://localhost:8080/auth/confirm?token=" + confirmToken.getToken();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(recipientAddress);
        message.setSubject(subject);
        message.setText("Click to confirm: " + confirmationUrl);

        emailSender.send(message);
    }
}
