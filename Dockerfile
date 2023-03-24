FROM node:slim
WORKDIR /Diploma/gate-simulator
COPY gate-simulator/package*.json ./
COPY gate-simulator/app*.js ./
COPY gate-simulator/data*.json ./
COPY gate-simulator/package-lock*.json ./
EXPOSE 9999
RUN npm install
CMD [ "npm", "start" ]
