# Cloud Providers

## AWS EKS
- make sure you have the latest and greatest version of AWS CLI, otherwise its a shit-ton of pain
- [A shit-ton more work](https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html)
```
aws eks update-kubeconfig --name <cluster>
```

## GCP GKE

#### Installation/Setup
- [install and setup:](https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html) easy peasy lemon squeezy
```
gcloud components install kubectl

gcloud config set project <gcp_project>
gcloud config set compute/zone <gcp_zone>
gcloud container clusters get-credentials <cluster>
```
- [create an image and deploy](/Docker/GoogleContainerRegistry.md) to GCP Container Registry

#### Deployment with LoadBalancer
The easy/recommended way to expose a replica set
```
# deploy a replica set and open port 8080
kubectl run my-app-deployment --image=gcr.io/<gcp_project>/<image>:<tag> --port=8080 --replicas=3
kubectl get deployments

# exposes a deployment on 'target-port' 8080, to the outside world via 'port' 80
kubectl expose deployment <deployment_name> --type LoadBalancer --port 80 --target-port 8080
kubectl get services
```

#### Deployment with NodePort+Ingress
Lets do it the [hard way](https://cloud.google.com/kubernetes-engine/docs/tutorials/http-balancer)
```
# deploy a replica set and open port 8080
kubectl run <deployment_name> --image gcr.io/<gcp_project>/<image>:<tag> --port 8080
kubectl get deployments

# create a NodePort
kubectl expose deployment <deployment_name> --target-port=8080 --type=NodePort --port 80
kubectl get service <deployment_name>

# create a new file:
# basic-ingress.yaml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: <ingress_name>
spec:
  backend:
    serviceName: <deployment_name>
    servicePort: 8080

# deploy the service
kubectl apply -f basic-ingress.yaml
kubectl get ingress <ingress_name>
kubectl get service <deployment_name>
```

#### References
- [kubectl](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands) commands: a reference

