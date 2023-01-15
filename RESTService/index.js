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

// import the trains model
const Trains = require('./models/trains');

// start the server
(async () => {
    try {
        // sync the database models
        await sequelize.sync(
            {force: false}
        );
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