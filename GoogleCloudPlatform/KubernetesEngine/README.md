# GCP Kubernetes Engine
- install (one time)
```
gcloud components install kubectl
gcloud config set project [PROJECT_ID]
gcloud config set compute/zone us-central1-b
```
- deploy
```
kubectl run web --image=gcr.io/google-samples/hello-app:1.0 --port=8080
```
