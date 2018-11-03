# Updating the Deployment
#### Rollout a new image
- its as simple as setting a new image
```
kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=jocatalin/kubernetes-bootcamp:v2
```
- verify
```
kubectl rollout status deployments/kubernetes-bootcamp
```

#### Rollback
This example rollout fails midway, so we neet to undo the rollout procedure

- rollout a non-existent image
```
kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=gcr.io/google-samples/kubernetes-bootcamp:v10
```
- check the deployment
```
kubectl get deployments
kubectl get pods
kubectl describe pods
```
- abort rollout
```
kubectl rollout undo deployments/kubernetes-bootcamp
```
