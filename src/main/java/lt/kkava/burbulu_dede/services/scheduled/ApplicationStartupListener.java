package lt.kkava.burbulu_dede.services.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.kkava.burbulu_dede.domain.AppProperties;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationStartupListener {
    private final AppProperties appProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void setDefaultLocale() {
        Locale.setDefault(Locale.of("lt", "LT"));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printSettings() {
        log.info("starage path: " + appProperties.getStoragePath());
        log.info("DB url: " + appProperties.getDbUrl());
        log.info("DB name: " + appProperties.getDbName());
        log.info("Owner email: " + appProperties.getOwnerEmail());
        log.info("Total mem: " + Runtime.getRuntime().totalMemory());
        log.info("Max mem: " + Runtime.getRuntime().maxMemory());
        log.info("Free mem: " + Runtime.getRuntime().freeMemory());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createFolders() throws IOException {
        FileUtils.forceMkdir(FileUtils.getFile(appProperties.getStoragePath(), "emailer", "out"));
    }
}

