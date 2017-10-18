#!/bin/bash

cd /opt/assign-app

# Pull the new changes
sudo git pull

cd ./assign-page/

# Update docker with the new build
docker build -t assign-page .

cd ..

docker-compose up -d
