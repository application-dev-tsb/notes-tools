# Scaling
Accomplished by changing the number of replicas in a Deployment

#### Creating replicas
- scale up the deployment
```
kubectl scale deployments/kubernetes-bootcamp --replicas=4
```
- verify
```
kubectl get deployments
```
- show individual deployments
```
kubectl get pods -o wide
```
