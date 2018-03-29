# AWS Elastic MapReduce

## Start a Permanent Cluster
```sh
aws emr create-cluster \
--name "emr_dev" \
--applications Name=Spark Name=Zeppelin Name=Hadoop Name=Hive Name=Pig \
--release-label emr-5.12.0 \
--instance-type m3.xlarge --instance-count 3 \
--no-auto-terminate \
--termination-protected \
--use-default-roles \
--log-uri "s3://mapreduce-test-input-1/logs/emr_dev" \
--configurations "https://s3-us-west-2.amazonaws.com/public-bucketx/emr-bootstrap/emr-config.json" \
--bootstrap-actions "Path=s3://bucketx/emr-bootstrap/python_install.sh,Name=InstallPyLibs" \
--ec2-attributes AdditionalSlaveSecurityGroups=sg-f5644a8e,AdditionalMasterSecurityGroups=sg-f5644a8e,KeyName=dev-lyndon,AvailabilityZone=us-west-2a \
--enable-debugging
```
