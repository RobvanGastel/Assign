# Deployment
Documentation and commands used in the deployment environment ( Ubuntu 17.04 ).


## Docker
Generic Docker commands to remember:

Build an image from current folder under given image name
```
$ docker build -t <username>/<app-name> .
``` 
See the list of build images
```
$ docker images
```
Let us run the image
```
$ docker run --name <app-name> -p 5000:1337 -d <username>/<app-name>
```

A good security practice is to run the Docker image (if possible) in read-only mode
by adding --read-only flag
See "Docker security" talk at mark 22:00 https://www.youtube.com/watch?v=oANurUSaOFs

Run docker image with a folder from HOST machine mounted
```
$ docker run -v /usr/source:/destination --name <app-name> -d <username>/<app-name>
```
inside the container /destination folder will be pointing at /usr/source from the HOST

Show all docker containers
```
$ docker ps -a
```

Let us see the app's console log to confirm that it has received a request
```
$ docker logs <app-name>
```
```
listening on port 1337 { subdomainOffset: 2, proxy: false, env: 'development' }
started server
  <-- GET /
  --> GET / 404 6ms -
```
you can follow the logs along using -f (--follow) option

Jump into the running container to run any commands
-i option means bind STDIO from the current shell
```
docker exec -it <app-name> bash
root@9f9f3ae62038:/usr/src/demo-server# ls
... list of files
root@9f9f3ae62038:/usr/src/demo-server# exit
```
If you want to quickly list files in the docker container (default folder)
you can use `docker exec`
```
$ docker exec -t demo ls
... list of files in /usr/src folder
```

To stop the running container
```
$ docker stop <app-name>
```

## WildFly Docker
The Backend is build in Java with Java EE7 as framework and as Server WildFly 
The ```Dockerfile``` used for deployment should look something like this.

```
# Use latest jboss/base-jdk:7 image as the base
FROM jboss/base-jdk:8

# Set the WILDFLY_VERSION env variable
ENV WILDFLY_VERSION 9.0.0.Final

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $HOME && curl http://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz | tar zx && mv $HOME/wildfly-$WILDFLY_VERSION $HOME/wildfly

# Set the JBOSS_HOME env variable
ENV JBOSS_HOME /opt/jboss/wildfly

# Expose the ports we're interested in
EXPOSE 8080

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
```
For the documentation visit [this link](https://hub.docker.com/r/jboss/wildfly/)

## Nodejs Docker
The frontend is build in [Nuxt.js](https://nuxtjs.org/) with express ( Based on Next.js ). 
The ```Dockerfile``` used for deployment should look something like this.
```
# Define node version
FROM node:6.10.2

# Set host for deployment
ENV HOST 0.0.0.0

# Create app directory
RUN mkdir -p /opt/assign-app/assign-page
WORKDIR /opt/assign-app/assign-page

# Install app dependencies
COPY . /opt/assign-app/assign-page
RUN npm install
RUN npm run build

# Choose port to expose
EXPOSE 80
CMD [ "npm", "start" ]
```
The file needs to be placed in the root directory of the app. The
```.dockerignore``` should have this as content:

```
.nuxt
node_modules
npm-debug.log
```
To build the image use:
```
$ docker build -t <username>/node-web-app .
```
To run the image use the following command with ```-p``` redirecting a public port to a private port inside the container.
```
$ docker run -p 49160:8080 -d <username>/node-web-app
```

## MySQL Docker
