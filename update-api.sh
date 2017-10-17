#!/bin/bash

# Pull the new changes
sudo git pull

cd ./assign-api/

# Build the maven package
sudo mvn package

# Place the .war file in the right directory
sudo cp -r /opt/assign-app/assign-api/target/assign-api-1.0-SNAPSHOT.war /opt/assign-app/assign-api/configuration

cd configuration

sudo mv assign-api-1.0-SNAPSHOT.war assign-api.war

# Update docker with the new build
cd ..

docker build -t wildfly .

cd ..

docker-compose up -d
