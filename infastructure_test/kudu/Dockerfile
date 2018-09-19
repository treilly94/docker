# Based on the below instilation guide
# https://kudu.apache.org/docs/installation.html

FROM ubuntu:xenial

# Add cloudera to the lists
ADD http://archive.cloudera.com/kudu/ubuntu/xenial/amd64/kudu/cloudera.list /etc/apt/sources.list.d/

# Update package manager and install dependencies
RUN apt-get update -y && apt-get install -y --allow-unauthenticated default-jdk kudu kudu-master kudu-tserver libkuduclient0 libkuduclient-dev supervisor

CMD ["/usr/bin/supervisord"]