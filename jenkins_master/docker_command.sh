# https://jenkins.io/doc/book/installing/#downloading-and-running-jenkins-in-docker
docker run \
  -u root \
  --rm \
  -d \
  -p 8080:8080 \
  -p 50000:50000 \
  -v /home/treilly/Documents/docker/jenkins_master:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  --name jenkins-master
  jenkinsci/blueocean