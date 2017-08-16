# Define node version
FROM node:6.10.2

# Set host for deployment
ENV HOST 0.0.0.0

# Create app directory
RUN mkdir -p /opt/assign-app/assign-dashboard

# Install app dependencies
COPY . /opt/assign-app/assign-dashboard
WORKDIR /opt/assign-app/assign-dashboard
RUN npm install
RUN npm run build

# Choose port to expose
EXPOSE 3000
CMD [ "npm", "start" ]