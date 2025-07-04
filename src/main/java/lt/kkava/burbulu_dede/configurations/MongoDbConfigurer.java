package lt.kkava.burbulu_dede.configurations;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.management.JMXConnectionPoolListener;
import lombok.AllArgsConstructor;
import lt.kkava.burbulu_dede.domain.AppProperties;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@AllArgsConstructor
public class MongoDbConfigurer {
    private final AppProperties appProperties;

    @Bean
    public MongoDatabase mongoDatabase() {
        MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(appProperties.getDbUrl()))
                        .applyToConnectionPoolSettings(builder -> builder.addConnectionPoolListener(new JMXConnectionPoolListener()))
                        .build();
        MongoClient mongoClient = MongoClients.create(settings);
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return mongoClient.getDatabase(appProperties.getDbName()).withCodecRegistry(pojoCodecRegistry);
    }
}
