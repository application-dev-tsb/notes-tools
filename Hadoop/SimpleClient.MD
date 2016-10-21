# Basic Client JAR

## Add the Following Dependency
```xml
<dependency>
	<groupId>org.apache.hadoop</groupId>
	<artifactId>hadoop-client</artifactId>
  <version>2.7.3</version>
</dependency>
```

## Set the Correct Main class in the manifest
```xml
<build>
  <plugins>
	  <plugin>
		  <groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-jar-plugin</artifactId>
			<configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <mainClass>com.lbibera.demowordcount.App</mainClass>
          </manifest>
        </archive>
		  </configuration>
	  </plugin>
  </plugins>
</build>
```
