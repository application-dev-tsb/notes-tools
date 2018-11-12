# Cloud Providers

## AWS EKS
- make sure you have the latest and greatest version of AWS CLI, otherwise its a shit-ton of pain
- [A shit-ton more work](https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html)
```
aws eks update-kubeconfig --name <cluster>
```

## GCP GKE
- [install and setup:](https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html) easy peasy lemon squeezy
```
gcloud components install kubectl

gcloud config set project <gcp_project>
gcloud config set compute/zone <gcp_zone>
gcloud container clusters get-credentials <cluster>
```
- [create an image and deploy](/Docker/GoogleContainerRegistry.md) to GCP Container Registries
- deploy
```
kubectl run <deployment_name> --image gcr.io/google-samples/hello-app:1.0 --port 8080
```
