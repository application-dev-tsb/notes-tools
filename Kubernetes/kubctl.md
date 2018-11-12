# kubctl

#### AWS EKS
- make sure you have the latest and greatest version of AWS CLI, otherwise its a shit-ton of pain
- [A shit-ton more work](https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html)
```
aws eks update-kubeconfig --name <cluster>
```

#### GCP GKE
- [easy peasy lemon squeezy](https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html)
```
gcloud components install kubectl
gcloud container clusters get-credentials <cluster>
```
