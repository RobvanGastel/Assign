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

cd ./assign-page/

# Update docker with the new build
docker build -t assign-page .

cd ..

docker-compose up -d

# Clean up docker
yes | docker system prune

EOF