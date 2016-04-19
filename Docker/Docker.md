# Commands:

Docker
```
ps 
    list all running images
    
run <imageName> 
    starts an image, downloads it locally if its not available
    
stop $(docker ps -a -q)
    stops all the containers
    
rm $(docker ps -a -q)
    deletes all the containers
```
