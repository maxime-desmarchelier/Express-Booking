const sequelize = require('../database');

// exports a function to test the database connection
exports.dbConnection = async (req, res, next) => {
    try {
        // authenticate the connection to the database
        await sequelize.authenticate()
            .then(() => {
                // if the connection is successful, send a 200 status
                res.status(200).send();
            })
            .catch(err => {
                // if the connection is not successful, send a 400 status with an error message
                res.status(400).json({ "error": 'Unable to connect to the database: ' + err });
            });
    } catch (error) {
        next(error);
    }
}

// exports a function to handle a hello world request
exports.helloWorld = async (req, res, next) => {
    try {
        // send a json object with a helloText property that includes the name passed in the request
        res.status(200).json({ "helloText": 'Hello ' + req.params.name });
        next();
    } catch (error) {
        next(error);
    }
}
