# Google Container Registry
Deploying a docker image to GCP Container Registry

#### Pre-requisites
- [Docker](https://docs.docker.com/install/)
- [GCP Cloud Console](https://cloud.google.com/sdk/docs/quickstart-windows)
- GCP Account

## Steps
- Create a simple app with a Dockerfile
```docker
# Dockerfile
# The Dockerfile defines the image's environment
# Import Python runtime and set up working directory
FROM python:2.7-slim
WORKDIR /app
ADD . /app

# Install any necessary dependencies
RUN pip install -r requirements.txt

# Open port 80 for serving the webpage
EXPOSE 80

# Run app.py when the container launches
CMD ["python", "app.py"]
```
```python
# requirements.txt
# This file defines the image's dependencies
Flask
```
```python
# app.py
# The Docker image contains the following code
from flask import Flask
import os
import socket

app = Flask(__name__)

@app.route("/")
def hello():
    html = "<h3>Hello, World!</h3>"
    return html

if __name__ == "__main__":
  app.run(host='0.0.0.0', port=80)
```
- build the docker image
```
docker build -t my-app .
```
- authenticate using gcloud
```
gcloud auth configure-docker
```
- tag the new image
```
docker tag my-app gcr.io/[PROJECT-ID]/my-app:v1
```
- push the new image
```
docker push gcr.io/[PROJECT-ID]/my-app:v1
```
