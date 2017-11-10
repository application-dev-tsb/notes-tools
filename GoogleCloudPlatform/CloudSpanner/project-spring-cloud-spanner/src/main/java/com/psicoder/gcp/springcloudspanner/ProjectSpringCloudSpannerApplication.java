package com.psicoder.gcp.springcloudspanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectSpringCloudSpannerApplication implements CommandLineRunner {

	@Autowired CloudSpannerDemonstrator demonstrator;

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringCloudSpannerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Demo demo = new Demo();
//		demo.setName("Test Row");
//
//		demonstrator.create(demo);

		demonstrator.createDatabase();
	}
}
