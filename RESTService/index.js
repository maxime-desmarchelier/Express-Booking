const express = require('express');
const sequelize = require('./database');
const swaggerUi = require('swagger-ui-express')
const swaggerFile = require('./swagger_output.json')
const fakeData = require('./fakeData');

// create an express app
const app = express();
// parse incoming json
app.use(express.json());
// parse incoming url encoded data
app.use(express.urlencoded({extended: true}));

// mount the routes for tests and search
app.use('/', require('./routes/tests'));
app.use('/', require('./routes/search'));
app.use('/', require('./routes/booking'));
app.use('/doc', swaggerUi.serve, swaggerUi.setup(swaggerFile))


// import the trains model
const Trains = require('./models/trains');
const Classes = require('./models/class');
const Ticket = require('./models/ticket');


// start the server
(async () => {
    try {
        // sync the database models
        sequelize.sync({force: true}).then(() => {
            console.log('Database synced');
            fakeData.generateFakeData();
        });


        let port = 3000;
        if (process.env.EXTERNAL_PORT) {
            port = process.env.EXTERNAL_PORT;
        }
        app.listen(port);
        console.log('Server started on port ' + port);
    } catch (error) {
        console.log(error);
    }
})();