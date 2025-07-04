package lt.kkava.burbulu_dede.configurations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.kkava.burbulu_dede.domain.AppProperties;
import lt.kkava.common_utils.services.ConfigurationService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AppConfiguration {
    private final ConfigurationService configurationService;

    @Bean
    public AppProperties getAppProperties() {
        AppProperties result = new AppProperties();
        result.setDbName(configurationService.getValue("DB_NAME"));
        result.setDbUrl(configurationService.getValue("DB_URL"));
        result.setStoragePath(configurationService.getValue("STORAGE_PATH"));
        result.setOwnerEmail(configurationService.getValue("OWNER_EMAIL"));
        return result;
    }
}
