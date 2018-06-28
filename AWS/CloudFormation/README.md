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
