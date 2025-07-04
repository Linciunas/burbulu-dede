package lt.kkava.burbulu_dede.domain;

import lombok.Data;

@Data
public class AppProperties {
    private String storagePath;
    private String dbName;
    private String dbUrl;
    private String ownerEmail;
}
