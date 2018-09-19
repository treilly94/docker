FROM openjdk:8

# Update package manager and install dependencies
RUN  apt-get update -y && apt-get install -y maven 

# Add spark
ADD http://mirror.novg.net/apache/spark/spark-2.3.1/spark-2.3.1-bin-hadoop2.7.tgz /home/
RUN tar -xvzf /home/spark-2.3.1-bin-hadoop2.7.tgz -C /home/

# Make data directory and set as cwd
RUN mkdir /home/data/
WORKDIR /home/