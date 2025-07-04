package lt.kkava.burbulu_dede.services;

import freemarker.template.Configuration;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.kkava.burbulu_dede.domain.AppProperties;
import lt.kkava.burbulu_dede.domain.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Properties;

@AllArgsConstructor
@Service
@Slf4j
public class EmailService {
    private final AppProperties appProperties;

    public void sendText(String subject, String bodyText, String recipient, String from, String replyTo, String cc) {
        send(subject, bodyText, recipient, "text/plain; charset=UTF-8", from, replyTo, cc);
    }

    public void sendHtml(String subject, String bodyText, String recipient, String from, String replyTo, String cc) {
        send(subject, bodyText, recipient, "text/html; charset=UTF-8", from, replyTo, cc);
    }

    private void send(String subject, String bodyText, String recipients, String contentType, String from,
                      String replyTo, String cc) {
        try {
            MimeMessage message = new MimeMessage(Session.getInstance(new Properties()));
            message.setRecipients(jakarta.mail.Message.RecipientType.TO, recipients);
            message.setFrom(from);
            if (StringUtils.isNotBlank(replyTo)) {
                message.setReplyTo(InternetAddress.parse(replyTo));
            }
            if (StringUtils.isNotBlank(cc)) {
                message.setRecipients(jakarta.mail.Message.RecipientType.CC, InternetAddress.parse(cc));
            }
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart("alternative");

            MimeBodyPart body = new MimeBodyPart();
            body.setContent(bodyText, contentType);

            multipart.addBodyPart(body);
            message.setContent(multipart);

            message.saveChanges();
            File file = FileUtils.getFile(appProperties.getStoragePath(), "emailer",
                    "out", Constants.DATE_FORMATTER_yyyyMMdd_dot_HHmmssSSS_file.format(LocalDateTime.now()) + ".eml");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                message.writeTo(fos);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
