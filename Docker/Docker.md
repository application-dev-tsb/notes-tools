# Commands:

Docker
```
help <command>
    lists the arguments for the command

images
    lists all images
    
ps 
    list all running images
    
run <imageName> 
    starts an image, downloads it locally if its not available
    --detach / -d
        runs the program in the background
    --interactive / -i
        standard input stays open
    --tty 
        allocate a virtual terminal
    
stop $(docker ps -a -q)
    stops all the containers
    
rm $(docker ps -a -q)
    deletes all the containers
```
