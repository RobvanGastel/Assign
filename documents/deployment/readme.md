# Deployment
Documentation and commands used in the deployment environment ( Ubuntu 17.04 ).

To build the entire enviornment make sure you create mysql first en all the images are build.

In head dir:
```
$ docker-compose up -d
```

**IMAGES** <br/>
In asign-page dir:
```
$ docker build -t assign-page .
```
In assign-api dir:
```
$ docker build -t wildfly .
```
No dir specified:
```
$ docker run --name mysql -e MYSQL_USER=***REMOVED*** -e MYSQL_PASSWORD=***REMOVED*** -e MYSQL_DATABASE=assign -e MYSQL_ROOT_PASSWORD=***REMOVED*** -p 5306:3306 -d mysql
```

## Load balancer
For Nginx a reverse proxy container is used, with [these specs](https://hub.docker.com/r/jwilder/nginx-proxy/). Together with the Nginx container we use docker-compose. To set up the wildfly part I used [this](https://github.com/jwilder/nginx-proxy/issues/560) as documentation.

to run docker compose on a directory: 
```
$ docker-compose up -d
```

Open port on ( only use this for debugging ):
```
$ iptables -I INPUT 1 -i eth0 -p tcp --dport 8080 -j ACCEPT
```

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
by adding --read-only flag for refference see [this video](https://youtu.be/oANurUSaOFs?t=22m)

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

Delete every Docker containers
Must be run first because images are attached to containers
```
$ docker rm -f $(docker ps -a -q)
```
Delete every Docker image
```
$ docker rmi -f $(docker images -q)
```

## WildFly Docker
The Backend is build in Java with Java EE7 as framework and as Server WildFly 
The ```Dockerfile``` used for deployment should look something like this.


To boot with MYSQL with variables:
```
$ docker run --name mysql -e MYSQL_USER=***REMOVED*** -e MYSQL_PASSWORD=***REMOVED*** -e MYSQL_DATABASE=assign -e MYSQL_ROOT_PASSWORD=***REMOVED*** -p 5306:3306 -d mysql
```

To build the Wildfly image:
```
$ docker build -t wildfly .
```
Datasource: `assignDS`

Database: `assign, -u ***REMOVED*** -p ***REMOVED***`

War file: `assign-api.war`

For documentation visit [this link](https://github.com/arun-gupta/docker-images/tree/master/wildfly-mysql-javaee7)

Install Java 1.8 with: 
```
$ apt-get install software-properties-common
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
```
Then, depending on the version you want to install, execute one of the following commands:
```
$ sudo apt-get install oracle-java8-installer
```

Edit the environment file to set the JAVA_HOME when its not set yet.
```
$ sudo nano /etc/environment
```

Depending on where you installed your Java, you will need to provide the full path. For this example, I use open-jdk-1.8 with the path:  `/usr/lib/jvm/java-8-oracle`
Add the following lines:
```
JAVA_HOME=/usr/lib/jvm/java-8-oracle
export JAVA_HOME
```
Test if the variable is been set right with:
```
$ echo $JAVA_HOME
```

To install Maven use: 
```
$ sudo apt-get install maven
```
To build the the war use in the assign-api directory with the pom.xml
```
$ sudo mvn package
```


## Nodejs Docker
The frontend is build in [Nuxt.js](https://nuxtjs.org/) with express ( Based on Next.js ). 
The `Dockerfile` used for deployment should look something like this.
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
EXPOSE 3000
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
$ docker build -t assign-page .
```

## MySQL Docker
MySQL is running on version 5.7.19 with the [Dockerfile from the Docs](https://github.com/docker-library/docs/tree/master/mysql).

Starting a MySQL container:
```
$ docker run --name mysql -e MYSQL_ROOT_PASSWORD=F4F2mbut -e MYSQL_DATABASE=assign -d mysql:latest
```



