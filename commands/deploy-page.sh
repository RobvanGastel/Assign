#!/bin/bash

# SSH to server
# TODO: Fix only works on Linux or MacOS!
ssh rob@***REMOVED*** -p 2122 << EOF

cd /opt/assign-app

# Pull the new changes
sudo git pull

cd ./assign-page/

# Update docker with the new build
docker build -t assign-page .

cd ..

docker-compose up -d

EOF