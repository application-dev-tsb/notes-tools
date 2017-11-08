package com.psicoder.gcp.springcloudspanner;

import com.google.cloud.spanner.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
public class CloudSpannerDemonstrator {

    @Value("${app.gcp.projectId}") String projectId;
    @Value("${app.spanner.instanceId}") String instanceId;
    @Value("${app.spanner.databaseId}") String databaseId;
    @Value("${app.spanner.demo.tableName}") String tableName;

    Spanner spanner;

    @PostConstruct
    public void init() {
        SpannerOptions options = SpannerOptions.newBuilder()
                .setProjectId(projectId)
                .build();

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

        dbClient.write(Arrays.asList(mutation));

        return demo;
    }
}
