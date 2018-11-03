# Kubernetes
Manages the lifecycle or containerized (Docker) applications

#### Minikube Installation
Minikube is a Kubernetes runtime for local development. 

Note: the following sequence only works on Ubuntu
- [install docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/#set-up-the-repository)
```
sudo apt-get update

sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common
 
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
   
sudo apt-get update

sudo apt-get install docker-ce
```
- [install minkube](https://kubernetes.io/docs/tasks/tools/install-minikube/)
```
curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.30.0/minikube-linux-amd64 && chmod +x minikube && sudo cp minikube /usr/local/bin/ && rm minikube
```
- [install kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
```
sudo snap install kubectl --classic
```
- change ownership of files to avoid having to sudo every kubectl commands
```
sudo chown $USER /home/$USER/.minikube/**
sudo chown $USER /home/$USER/.kube/**
```

#### Start Minikube
```
minikube start --vm-driver=none
```
#### Verify Installation
```
kubectl cluster-info
```
