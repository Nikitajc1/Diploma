FROM node:latest
WORKDIR /Diploma/gate-simulator
COPY gate-simulator/package*.json ./
COPY gate-simulator/app*.js ./
COPY gate-simulator/data*.json ./
COPY gate-simulator/package-lock*.json ./
#COPY gate-simulator/server*.js ./
EXPOSE 9999
RUN npm install
CMD [ "npm", "start" ]
