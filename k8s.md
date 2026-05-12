To create a secret
```sh
kubectl create secret generic -c aclab --from-env-file .env
```

To log in to docker in k8s
```sh
kubectl create secret docker-registry regcred \
  --docker-server=https://index.docker.io/v1/ \
  --docker-username=your-username \
  --docker-password=your-password \
  --docker-email=your-email \
  -n aclab
```
