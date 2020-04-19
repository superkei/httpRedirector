# Getting Started

### compile
>mvn clean install

### create docker image
>docker build -t superkei/http-redirector .

### deploy to kube via helm
>cd helm/http-redirector

##### for newly install
>helm install httpredirector .

##### for upgrade
update version in Chart.yaml 
>helm upgrade httpredirector .


### PS: remember to install ngix-ingress into kube
>kubectl create namespace nginx-ingress
>helm repo add stable https://kubernetes-charts.storage.googleapis.com/
>helm install -n nginx-ingress nginx-ingress stable/nginx-ingress --set controller.service.ports.http=80  --set rbac.create=true
if you got existing_kind: rbac.authorization.k8s.io/v1 exception, use this
>helm install -n nginx-ingress nginx-ingress stable/nginx-ingress --set controller.service.ports.http=80  --set rbac.create=false