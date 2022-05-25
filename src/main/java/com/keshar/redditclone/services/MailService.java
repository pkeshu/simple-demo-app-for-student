package com.keshar.redditclone.services;

import com.keshar.redditclone.model.entities.NotificationEmail;
import com.keshar.redditclone.utils.exception.SpringRedditException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    private static final String TAG = "MailService";

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("keshar@pickndropnepal.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };

        try {
            mailSender.send(messagePreparator);
            log.info(TAG, "sendMail: Activation Email sent!!");

        } catch (MailException e) {
            log.info(TAG, "sendMail: >>>"+e.getLocalizedMessage());
            throw new SpringRedditException("Exception occurred when sending mail to >>" + notificationEmail.getRecipient());
        }
    }
}
