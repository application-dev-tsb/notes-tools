# GCP Kubernetes Engine
- install (one time)
```
gcloud components install kubectl
gcloud config set project <project>
gcloud config set compute/zone <zone>
```
- choose cluister
```
gcloud container clusters get-credentials <cluster>
```
- deploy
```
kubectl run web --image=gcr.io/<project>/<image>:<tag> --port=8080
```
