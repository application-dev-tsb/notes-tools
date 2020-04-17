# SSH Tunnel

## Pre-requisite
```
npm install -g http-proxy-to-socks
```

## Steps
- connect to the instance while exposing a socks proxy
```sh
ssh -D 5001 username@xx.xxx.xxx.xx
```

- translate the socks proxy into HTTP proxy
```
hpts -s 127.0.0.1:5001 -p 8080
```

- use the proxy from postman
```
Proxy server : 127.0.0.1:8080
```


[Source](https://medium.com/teamarimac/access-a-server-through-ssh-tunnel-and-send-api-requests-through-postman-in-windows-10-b7307974c1a0)
