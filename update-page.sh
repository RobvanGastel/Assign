#!/bin/bash

# Pull the new changes
sudo git pull

cd ./assign-page/

# Update docker with the new build
cd ..

docker build -t assign-page .

cd ..

docker-compose up -d
