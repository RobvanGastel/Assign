# Define node version
FROM node:6.10.2

# Set host for deployment
ENV HOST 0.0.0.0

# Create app directory
RUN mkdir -p /opt/assign-app/assign-page

# Install app dependencies
COPY . /opt/assign-app/assign-page
WORKDIR /opt/assign-app/assign-page
RUN npm install

# Set env to production
ARG APP_NODE_ENV=production
ENV NODE_ENV ${NODE_ENV}
RUN npm run build

# Choose port to expose
EXPOSE 3000
CMD [ "npm", "start" ]
