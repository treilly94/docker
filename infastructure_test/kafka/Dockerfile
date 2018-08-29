# Based on the below quickstart
# https://kafka.apache.org/documentation/

FROM ubuntu:xenial

# Update package manager and install dependencies
RUN apt-get update -y && apt-get install -y supervisor default-jdk

# Download and extract kafka
ADD http://apache.mirror.anlx.net/kafka/2.0.0/kafka_2.11-2.0.0.tgz /
RUN tar -xzf kafka_2.11-2.0.0.tgz
WORKDIR /kafka_2.11-2.0.0/

# Configure supervisor to run both zookeper and kafka servers
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

CMD ["/usr/bin/supervisord"]