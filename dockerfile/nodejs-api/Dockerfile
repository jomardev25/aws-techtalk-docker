# Use an official node runtime as a parent image
FROM node:alpine

# Define environment variable
ARG WORK_SPACE=/usr/app
ENV UPLOAD_PATH=/uploads

# Set the working directory to /usr/app
WORKDIR $WORK_SPACE

# Create upload folder inside /usr/app -> /usr/app/uploads
RUN mkdir .$UPLOAD_PATH 

# Copy package.json inside /usr/app
COPY package.json .

# Install any needed packages
RUN npm install

# Copy the current directory contents into the container at /usr/app
COPY . .

# Expose container port 8080
EXPOSE 8080

# Run npm run start when the container launches
CMD [ "node", "index.js" ]