const express = require('express');
const sequelize = require('./database');

// create an express app
const app = express();
// parse incoming json
app.use(express.json());
// parse incoming url encoded data
app.use(express.urlencoded({extended: true}));

// mount the routes for tests and search
app.use('/tests', require('./routes/tests'));
app.use('/search', require('./routes/search'));
app.use('/booking', require('./routes/booking'));


// import the trains model
const Trains = require('./models/trains');
const Classes = require('./models/class');
const Ticket = require('./models/ticket_type');


// start the server
(async () => {
    try {
        // sync the database models
        sequelize.sync({force: true}).then(() => {
            console.log('Database synced');
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