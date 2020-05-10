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


### to install prometheus+grafana for monitoring
>kubectl create namespace monitoring
>helm install prometheus -n monitoring stable/prometheus
>helm install grafana stable/grafana -n monitoring --set service.type=LoadBalancer,service.port=3001
access the grafana via 3001 port
PS: To get password:
>kubectl get secret -n monitoring grafana -o jsonpath="{.data.admin-password}" | base64 --decode
add data source http://prometheus-server.monitoring (for prometheus-server)
import plugin https://grafana.com/grafana/dashboards/4701
start checking on dashboard