# Service
An abstraction which defines a logical set of Pods and a policy by which to access them

#### Services can be exposed in different ways by specifying a type in the ServiceSpec:
- ClusterIP (default) - Exposes the Service on an internal IP in the cluster. This type makes the Service only reachable from within the cluster.
- NodePort - Exposes the Service on the same port of each selected Node in the cluster using NAT. Makes a Service accessible from outside the cluster using <NodeIP>:<NodePort>. Superset of ClusterIP.
- LoadBalancer - Creates an external load balancer in the current cloud (if supported) and assigns a fixed, external IP to the Service. Superset of NodePort.
- ExternalName - Exposes the Service using an arbitrary name (specified by externalName in the spec) by returning a CNAME record with the name. No proxy is used. This type requires v1.7 or higher of kube-dns

#### Nodeport
Lets expose one of our app as a service
- expose the sample deployment
```
kubectl expose deployment/kubernetes-bootcamp --type="NodePort" --port 8080
```
- check the status of the service
```
kubectl describe services/kubernetes-bootcamp
```
- node port
```
kubectl get services/kubernetes-bootcamp -o go-template='{{(index .spec.ports 0).nodePort}}'
```
- verify service exposure
```
kubectl get services/kubernetes-bootcamp -o go-template='{{(index .spec.ports 0).nodePort}}'
curl $(minikube ip):$NODE_PORT
```

#### Labels
Labels make it so much easier to find kubernetes resources
- get the pod name that we want to label
```
kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}'
```
- get the pod data, note the 'Labels' section
```
kubectl describe pods $POD_NAME
```
- apply the label (by force, using the overwrite flag)
```
kubectl label pod $POD_NAME app=v1 --overwrite
```
- now you can select resource by label
```
kubectl get pods -l app=v1
```

#### Deleting a Service (by Label)
```
kubectl delete service -l run=kubernetes-bootcamp
```
