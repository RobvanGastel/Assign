#!/bin/bash

# Show current directory
echo pwd

sudo git pull

cd ./assign-api/

# Build the maven package
sudo mvn package

sudo cp -r /opt/assign-app/assign-api/target/assign-api-1.0-SNAPSHOT.war /opt/assign-app/assign-api/configuration

cd configuration

sudo mv assign-api-1.0-SNAPSHOT.war assign-api.war

cd ..

docker build -t wildfly .
