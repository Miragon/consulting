FROM node:latest as builder

WORKDIR /app

COPY package.json .
COPY yarn.lock .

RUN corepack enable
RUN yarn set version berry
RUN yarn install

COPY ./src ./src
COPY ./public ./public
COPY ./index.html ./tsconfig.json ./tsconfig.node.json ./vite.config.ts ./

ARG VITE_BASE_URL
ENV VITE_BASE_URL=$VITE_BASE_URL

FROM builder as dev
CMD ["yarn", "dev"]

FROM builder as build
RUN yarn build

FROM nginx:latest as prod
COPY docker/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]