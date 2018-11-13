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
- [create an image and deploy](/Docker/GoogleContainerRegistry.md) to GCP Container Registry
- deploy an image with replica set
```
kubectl run <deployment_name> --image gcr.io/<gcp_project>/<image>:<tag> --port 8080
```
- expose the replica set via a service [(http balancer)](https://cloud.google.com/kubernetes-engine/docs/tutorials/http-balancer)
```
#kubectl expose deployment <deployment_name> --type LoadBalancer --port 80 --target-port 8080
kubectl expose deployment <deployment_name> --target-port=8080 --type=NodePort
kubectl get service <deployment_name>
```
- create an ingress resource
```
#basic-ingress.yaml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: <ingress_name>
spec:
  backend:
    serviceName: <deployment_name>
    servicePort: 8080
```
```
kubectl apply -f basic-ingress.yaml
```
- verify
```
kubectl get ingress <ingress_name>
kubectl get service <deployment_name>
```
