FROM openjdk:8

# Update package manager and install dependencies
RUN  apt-get update -y && apt-get install -y maven 

# Make data directory and set as cwd
RUN mkdir /data/
WORKDIR /data/