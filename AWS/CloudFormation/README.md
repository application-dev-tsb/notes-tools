# AWS: CloudFormation
Automated provisioning for everything AWS. Be sure to know the individual tech before authoring a Stack Template. 

## Reference: Things you need for that JSON/YAML files
- [Resource Types](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-template-resource-type-ref.html): the most important thing
- [Parameters](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/parameters-section-structure.html)
- [AWS Parameter Types](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/parameters-section-structure.html#aws-specific-parameter-types)

## Basics:
- Top Level:
```json
{
    "AWSTemplateFormatVersion": "2010-09-09",
    "Description" : "...",
    "Metadata": {
        ...
    },
    "Resources": {
        ...
    },
    "Parameters": {
        ...
    }
}
```
- EC2 on VPC
```json
{  
   "Type":"AWS::EC2::Instance",
   "Properties":{  
      "ImageId":"ami-e99f4896",
      "InstanceType":"t2.micro",
      "KeyName":{  
         "Ref":"KeyName"
      },
      "NetworkInterfaces":[  
         {  
            "AssociatePublicIpAddress":"true",
            "DeviceIndex":"0",
            "DeleteOnTermination":"true",
            "GroupSet":[  
               {  
                  "Ref":"SSHPublic"
               }
            ]
         }
      ],
      "Tags":[  
         {  
            "Key":"Name",
            "Value":"Bastion"
         }
      ]
   },
   "Metadata":{  
      "AWS::CloudFormation::Designer":{  
         "id":"46e067b1-9588-487c-b947-3520b4745901"
      }
   }
}
```
