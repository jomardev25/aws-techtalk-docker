# Use an official node runtime as a parent image
FROM node:alpine

# Define environment variable
ARG WORK_SPACE=/usr/app
ENV APP_NAME NodeJSAPI

# Set the working directory to /usr/app
WORKDIR $WORK_SPACE

# Install nodemon globally
RUN npm install --global nodemon

# Create uploads folder inside /usr/app
RUN mkdir uploads

# Copy package.json inside /usr/app
COPY package.json .

# Install any needed packages
RUN npm install

# Copy the current directory contents into the container at /usr/app
COPY . .

# Don't use root user
USER node

# Make port 80 available to the world outside this container
EXPOSE 8080

# Run npm run start when the container launches
CMD [ "npm", "run" "start" ]