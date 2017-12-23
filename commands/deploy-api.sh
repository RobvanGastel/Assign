#!/bin/bash

# SSH to server
# TODO: Fix only works on Linux or MacOS!
ssh rob@***REMOVED*** -p 2122 << EOF

cd /opt/assign-app

# Pull the new changes
# Make sure to configure git on the server:
# sudo chown -R user:user /opt/app/.git
# sudo chown -R user:user /opt/app/
git pull

cd ./assign-api/

# Build the maven package
mvn package

# Place the .war file in the right directory
cp -r /opt/assign-app/assign-api/target/assign-api-1.0-SNAPSHOT.war /opt/assign-app/assign-api/configuration

cd configuration

mv assign-api-1.0-SNAPSHOT.war assign-api.war

# Update docker with the new build
cd ..

docker build -t wildfly .

cd ..

docker-compose up -d

# Clean up docker
yes | docker system prune

EOF