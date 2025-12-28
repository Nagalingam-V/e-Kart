package com.example.eKart.user.messaging.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String toEmail, String fullName) {

        String subject = "Welcome to e-Kart 🎉";

        String body =
                "Welcome " + fullName + ",\n\n" +
                        "We are glad to have you.\n" +
                        "From now onwards, you are an e-Kart user.\n\n" +
                        "Regards,\n" +
                        "e-Kart Team";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
