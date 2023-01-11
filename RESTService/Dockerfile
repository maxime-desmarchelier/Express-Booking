FROM node

RUN npm i npm@latest -g

COPY package.json package-lock.json* ./

RUN npm install --no-optional && npm cache clean --force

# copy in our source code last, as it changes the most
WORKDIR /opt
COPY . .

EXPOSE 3000

CMD [ "node", "index.js" ]