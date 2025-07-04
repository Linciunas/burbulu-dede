package lt.kkava.burbulu_dede.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.kkava.burbulu_dede.domain.AppProperties;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import lt.kkava.burbulu_dede.domain.RenginioUzsakymas;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceBurbulai {
    private final Configuration templateConfig;
    private final EmailService emailService;
    private final AppProperties appProperties;

    public void sendRenginioUzsakymas(RenginioUzsakymas renginioUzsakymas) {
        try {
            Map<String, Object> values = Map.of("order", renginioUzsakymas);
            String subject = "Renginio užsakymas";
            Template template = templateConfig.getTemplate("uzsakymas_renginys.flt", StandardCharsets.UTF_8.toString());
            String templateString;
            try (Writer stringWriter = new StringWriter()) {
                template.setOutputEncoding(StandardCharsets.UTF_8.toString());
                template.process(values, stringWriter);
                templateString = stringWriter.toString();
            }
            emailService.sendHtml(subject, templateString, appProperties.getOwnerEmail(), null, renginioUzsakymas.getEmail(), null);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void sendOrder(ProduktuUzsakymas produktuUzsakymas) {
        try {
            Map<String, Object> values = Map.of("order", produktuUzsakymas);
            String subject = "Naujas prekių užsakymas";
            Template template = templateConfig.getTemplate("uzsakymas_prekiu.flt", StandardCharsets.UTF_8.toString());
            String templateString;
            try (Writer stringWriter = new StringWriter()) {
                template.setOutputEncoding(StandardCharsets.UTF_8.toString());
                template.process(values, stringWriter);
                templateString = stringWriter.toString();
            }
            emailService.sendHtml(subject, templateString, appProperties.getOwnerEmail(), null, produktuUzsakymas.getEmail(), null);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
