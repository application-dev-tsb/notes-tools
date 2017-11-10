package com.psicoder.gcp.springcloudspanner;

import com.google.cloud.spanner.*;
import com.google.spanner.admin.database.v1.CreateDatabaseMetadata;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Log
@Component
public class CloudSpannerDemonstrator {

    @Value("${app.gcp.projectId}")
    String projectId;

    @Value("${app.spanner.instanceId}")
    String instanceId;

    @Value("${app.spanner.databaseId}")
    String databaseId;

    @Value("${app.spanner.demo.tableName}")
    String tableName;

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

        log.info("App: " + options.getApplicationName());
        log.info("Host: " + options.getHost());
        log.info("Version: " + options.getLibraryVersion());
        log.info("Scopes: " + options.getScopes().stream().collect(Collectors.joining(",")));

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

    public void createDatabase() {
        DatabaseAdminClient dbAdminclient = spanner.getDatabaseAdminClient();
        DatabaseId dbId = DatabaseId.of(this.projectId, this.instanceId, this.databaseId);

        Operation<Database, CreateDatabaseMetadata> op = dbAdminclient
                .createDatabase(
                        dbId.getInstanceId().getInstance(),
                        "database-" + System.currentTimeMillis(),
                        asList("CREATE TABLE Singers (\n"
                                        + "  SingerId   INT64 NOT NULL,\n"
                                        + "  FirstName  STRING(1024),\n"
                                        + "  LastName   STRING(1024),\n"
                                        + "  SingerInfo BYTES(MAX)\n"
                                        + ") PRIMARY KEY (SingerId)",
                                "CREATE TABLE Albums (\n"
                                        + "  SingerId     INT64 NOT NULL,\n"
                                        + "  AlbumId      INT64 NOT NULL,\n"
                                        + "  AlbumTitle   STRING(MAX)\n"
                                        + ") PRIMARY KEY (SingerId, AlbumId),\n"
                                        + "  INTERLEAVE IN PARENT Singers ON DELETE CASCADE")
                );

        Database db = op.waitFor().getResult();
        log.info("Created Database: " + db.getId());
    }
}
