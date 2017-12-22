#!/bin/bash

# SSH to server
# TODO: Fix only works on Linux or MacOS!
ssh rob@***REMOVED*** -p 2122 << EOF

cd /opt/assign-app

# Pull the new changes
sudo git pull

# Update API
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

# Update website
cd ./assign-page/

# Update docker with the new build
docker build -t assign-page .

cd ..

docker-compose up -d

EOF