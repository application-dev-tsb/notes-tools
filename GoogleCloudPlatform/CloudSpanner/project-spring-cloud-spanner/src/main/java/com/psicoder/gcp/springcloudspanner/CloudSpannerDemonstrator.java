package com.psicoder.gcp.springcloudspanner;

import com.google.cloud.spanner.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static java.util.Arrays.asList;

@Component
public class CloudSpannerDemonstrator {

    @Value("${app.gcp.projectId}") String projectId;
    @Value("${app.spanner.instanceId}") String instanceId;
    @Value("${app.spanner.databaseId}") String databaseId;
    @Value("${app.spanner.demo.tableName}") String tableName;

    Spanner spanner;

    /**
     * docs:
     * https://developers.google.com/api-client-library/java/google-oauth-java-client/oauth2
     */
    @PostConstruct
    @SneakyThrows
    public void init() {

        //using authentication:
        //https://cloud.google.com/docs/authentication/getting-started
        //don't forget to set the environment variable for the service account:
        //GOOGLE_APPLICATION_CREDENTIALS=<path_to_service_account_file>
        SpannerOptions options = SpannerOptions.getDefaultInstance();

        this.spanner = options.getService();
    }

    @PreDestroy
    public void destroy() {
        spanner.close();
    }

    public Demo create(Demo demo) {
        DatabaseId db = DatabaseId.of(this.projectId, this.instanceId, this.databaseId);
        DatabaseClient dbClient = spanner.getDatabaseClient(db);

        Mutation mutation = Mutation
                .newInsertBuilder(tableName)
                .set("name")
                .to(demo.getName())
                .build();

        dbClient.write(asList(mutation));

        return demo;
    }
}
