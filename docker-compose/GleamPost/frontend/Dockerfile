# First Step is to bundle the react app
FROM node:alpine AS builder
WORKDIR /usr/app
COPY ./package.json .
COPY ./package-lock.json .
RUN npm install
COPY . .
RUN npm run build

# Second Step is to serve the static files in nginx
FROM nginx:1.25.2
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /usr/app/build .
EXPOSE 80
COPY ./conf.d/default.conf /etc/nginx/conf.d/default.conf
ENTRYPOINT ["nginx", "-g", "daemon off;"]