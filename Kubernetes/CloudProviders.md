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

# wait for it...seriously...it takes a while, go grab some coffee
```

#### Deployment with NodePort+Ingress
Lets do it the [hard way](https://cloud.google.com/kubernetes-engine/docs/tutorials/http-balancer)
```
# runs the container and opens the selected port, with 3 replicas
kubectl run my-app-deployment --image=gcr.io/kwler-net/my-app:v1 --port=80 --replicas=3
kubectl get deployments

# create a cluster load balancer vi NodePort
# target-port => 80, Name or number for the port on the container that the service should direct traffic to. Optional.
# port => 80, The port that the service should serve on. Copied from the resource being exposed, if unspecified
kubectl expose deployment my-app-deployment --type=NodePort --name=my-app-nodeport --target-port=80 --port=8080
kubectl get services

# node-port => 31558, internal port on this cluster
# example
# NAME           TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)        AGE
# kubernetes     ClusterIP   10.35.240.1    <none>        443/TCP        1d
# my-node-port   NodePort    10.35.240.97   <none>        8080:31558/TCP   1m

# create an ingress service to expose the app to the world
# filename: my-app-ingress.yaml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: my-app-ingress
spec:
  backend:
    serviceName: my-app-nodeport
    servicePort: 8080
	
# create ingress service	
kubectl apply -f my-app-ingress.yaml
kubectl get ingress

# wait for it...seriously...it takes a while, go grab some coffee
```

#### References
- [kubectl](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands) commands: a reference

