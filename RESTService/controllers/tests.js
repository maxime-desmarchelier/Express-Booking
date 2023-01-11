const sequelize = require('../database');


exports.dbConnection = async (req, res, next) => {
    try {
        await sequelize.authenticate().then(() => {
            res.status(200).json('Connection has been established successfully.');
        })
            .catch(err => {
                res.status(400).json('Unable to connect to the database:', err);
            });
    } catch (error) {
        next(error);
    }
}

exports.helloWorld = async (req, res, next) => {
    try {
        res.status(200).json('Hello ' + req.params.name);
        next();
    } catch (error) {
        next(error);
    }
}