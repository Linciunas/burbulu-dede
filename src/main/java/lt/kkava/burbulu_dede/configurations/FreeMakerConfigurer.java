package lt.kkava.burbulu_dede.configurations;

import freemarker.template.TemplateExceptionHandler;
import lombok.AllArgsConstructor;
import lt.kkava.burbulu_dede.Application;
import lt.kkava.burbulu_dede.domain.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@AllArgsConstructor
public class FreeMakerConfigurer {

    @Bean("templateConfig")
    public freemarker.template.Configuration templateConfig() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        cfg.setLocale(Constants.LOCALE_VILNIUS);
        cfg.setClassForTemplateLoading(Application.class, "/email_templates");

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        return cfg;
    }
}
