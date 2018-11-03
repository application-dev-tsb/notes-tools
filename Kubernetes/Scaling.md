# Scaling
Accomplished by changing the number of replicas in a Deployment

#### Scaling Up: Creating Replicas
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
- well, thats it if you exposed it as a service, the replicas are load-balanced
```
kubectl describe services/kubernetes-bootcamp

curl $(minikube ip):$(kubectl get services/kubernetes-bootcamp -o go-template='{{(index .spec.ports 0).nodePort}}')
```

#### Scaling Down
- just set the 'desired state'
```
kubectl scale deployments/kubernetes-bootcamp --replicas=2
```
- scale to zero
```
kubectl scale deployments/kubernetes-bootcamp --replicas=0
```
