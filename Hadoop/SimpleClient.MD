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

## Create the Mapper and Reducer classes
```java
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				word.set(tokenizer.nextToken());
				context.write(word, one);
			}
		}
	}
	
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {

		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}
```

## Configure the Job on the Main class
```java
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();

	      Job job = new Job(conf, "wordcount");

	      job.setJarByClass(App.class);
	      job.setOutputKeyClass(Text.class);
	      job.setOutputValueClass(IntWritable.class);

	      job.setMapperClass(Map.class);
	      job.setReducerClass(Reduce.class);

	      job.setInputFormatClass(TextInputFormat.class);
	      job.setOutputFormatClass(TextOutputFormat.class);

	      FileInputFormat.addInputPath(job, new Path(args[0]));
	      FileOutputFormat.setOutputPath(job, new Path(args[1]));

	      job.waitForCompletion(true);
	}
```

## Copy Client JAR
```
pscp -i C:\Users\KWL\Desktop\DriveSync\aws-priv.ppk C:\Users\KWL\.m2\repository\com\lbibera\demo-wordcount\0.0.1-SNAPSHOT\demo-wordcount-0.0.1-SNAPSHOT.jar ec2-user@ec2-54-149-125-168.us-west-2.compute.amazonaws.com:/home/ec2-user
```

## Run Client Jar
```
./hadoop-2.7.3/bin/hadoop jar demo-wordcount-0.0.1-SNAPSHOT.jar input.txt output
```
