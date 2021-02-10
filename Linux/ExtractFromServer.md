```
curl --location --request POST 'https://vpc-test-es-xx.us-east-2.es.amazonaws.com/candidates/_search' \
--header 'Content-Type: application/json' \
--data-raw '{
    "query": {
        "match": {
            "currentWorkTitle.fulltext": "Systems Engineer"
        }
    },
    "size": 10000
}' \
--output Systems_Engineer.json

curl --location --request POST 'https://vpc-test-es-xx.us-east-2.es.amazonaws.com/candidates/_search' \
--header 'Content-Type: application/json' \
--data-raw '{
    "query": {
        "match": {
            "currentWorkTitle.fulltext": "Software Developer"
        }
    },
    "size": 10000
}' \
--output Software_Developer.json


scp ec2-user@3.19.xx.177:/home/ec2-user/Mainframe_Developer.json ~/Desktop/


scp ec2-user@3.19.xx.177:/home/ec2-user/Salesforce.json ~/Desktop/


scp ec2-user@3.19.xx.177:/home/ec2-user/Software_Developer.json ~/Desktop/


scp ec2-user@3.19.xx.177:/home/ec2-user/Software_QA.json ~/Desktop/


scp ec2-user@3.19.xx.177:/home/ec2-user/Systems_Engineer.json ~/Desktop/

cat Salesforce.json | jq ".hits.hits[] | {id:._id, score:._score, fullName:._source.fullName, workTitle: ._source.currentWorkTitle, taggedFunction: ._source.currentFunction}"



cat Mainframe_Developer.json | jq ".hits.hits[] | [._score, ._source.currentWorkTitle, ._source.currentFunction] | @csv" -r > Mainframe_Developer.csv
cat Salesforce.json | jq ".hits.hits[] | [._score, ._source.currentWorkTitle, ._source.currentFunction] | @csv" -r > Salesforce.csv
cat Software_Developer.json | jq ".hits.hits[] | [._score, ._source.currentWorkTitle, ._source.currentFunction] | @csv" -r > Software_Developer.csv
cat Software_QA.json | jq ".hits.hits[] | [._score, ._source.currentWorkTitle, ._source.currentFunction] | @csv" -r > Software_QA.csv
cat Systems_Engineer.json | jq ".hits.hits[] | [._score, ._source.currentWorkTitle, ._source.currentFunction] | @csv" -r > Systems_Engineer.csv
```
