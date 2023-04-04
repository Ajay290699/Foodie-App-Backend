package com.niit.MailNotificationService.rabbitmq;


import com.niit.MailNotificationService.mailSender.SendMail;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private SendMail mailSender;


    @RabbitListener(queues = "mail_queue")
    public void sendMail(EmailDTO emailDTO) {
        System.out.println(emailDTO);
        mailSender.send(emailDTO.getReciever(), emailDTO.getSubject(), emailDTO.getMessageBody());
    }
}
