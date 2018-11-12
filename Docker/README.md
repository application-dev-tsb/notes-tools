# Docker
Docker is a container runtime and containerization framework

- [Deploying an Image to GCP Container Registry](GoogleContainerRegistry.md)


## Commands
```
help <command>
    lists the arguments for the command

images
    lists all images
    
ps 
    list all running images
    
run <imageName> 
    starts a container, downloads it locally if its not available
    --name
        the name of the container, useful for linking
    --detach / -d
        runs the program in the background
    --interactive / -i
        standard input stays open
    --tty 
        allocate a virtual terminal
    
stop <container>
stop $(docker ps -a -q)
    stops all the containers
    
restart <container>
    starts a container if it was stopped
    
rm $(docker ps -a -q)
    deletes all the containers
    
logs <container>
    shows the logs for the container
```

## Running Command Line
```docker
docker run --interactive --tty \
    --link web:web \
    --name web_test \
    busybox:latest /bin/sh
```
