# Getting Started

### compile
>mvn clean install

### create docker image
>docker build -t superkei/http-redirector .

### deploy to kube via helm
>cd helm/http-redirector

#####for newly install
>helm install <name> .

#####for upgrade
update version in Chart.yaml 
>helm upgrade <name> .
