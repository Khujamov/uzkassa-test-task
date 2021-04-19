package uz.kassa.test.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.kassa.test.util.Utils;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String email, String confirmToken) {
        String confirmUrl = Utils.CONFIRM_URL + confirmToken;
        String text = Utils.text.replace("%token%", confirmUrl);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(email);
            messageHelper.setSubject("Confirmation Link");
            messageHelper.setText(text, true);
            javaMailSender.send(messageHelper.getMimeMessage());
        }catch (Exception e) {
            LOGGER.error("Error {}", e.getMessage());
        }
    }
}
