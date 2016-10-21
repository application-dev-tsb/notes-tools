# Hadoop Single-Node Setup

## Make Sure Java 8 is Installed
```
sudo yum install java-1.8.0
sudo yum remove java-1.7.0-openjdk
```

## Download and Extract the Hadoop Installation
```
wget http://mirror.rise.ph/apache/hadoop/common/stable/hadoop-2.7.3-src.tar.gz
tar -zxvf /home/ec2-user/hadoop-2.7.3-src.tar.gz
```

####Resources:
- [Single-Node Setup](https://hadoop.apache.org/docs/current2/hadoop-project-dist/hadoop-common/SingleCluster.html)
