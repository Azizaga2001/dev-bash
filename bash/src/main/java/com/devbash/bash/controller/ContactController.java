package com.devbash.bash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{10,15}$");

    @PostMapping("/send-contact")
    public String sendContact(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String birthdate) {
        // Валидация email и телефона
        if (!EMAIL_PATTERN.matcher(email).matches() || !PHONE_PATTERN.matcher(phone).matches()) {
            return "redirect:/cookies.html?error=validation";
        }

        String subject = "Контактные данные для проверки";
        String text = "Имя: " + name + "\nEmail: " + email + "\nТелефон: " + phone + "\nДата рождения: " + birthdate;

        // Отправить письмо себе (админу)
        SimpleMailMessage adminMessage = new SimpleMailMessage();
        adminMessage.setTo("azizovgul4@gmail.com"); // замени на свой email
        adminMessage.setSubject(subject);
        adminMessage.setText(text);
        mailSender.send(adminMessage);

        // Отправить письмо пользователю
        String userSubject = "Здравствуйте, " + name + "! Вы отправили данные на dev-bash.com";
        String userText = "Здравствуйте, " + name + ", вы отправляли данные с сайта dev-bash.com.\n" +
                "Если в течение 2 недель не совершите оплату корзины, вам придется вводить данные заново.";

        SimpleMailMessage userMessage = new SimpleMailMessage();
        userMessage.setTo(email);
        userMessage.setSubject(userSubject);
        userMessage.setText(userText);
        mailSender.send(userMessage);

        // После успешной отправки — редирект на /
        return "redirect:/home-en";
    }
}