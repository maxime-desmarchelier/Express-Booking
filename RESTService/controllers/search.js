const Trains = require("../models/trains");
const Classes = require("../models/class");
const Ticket = require("../models/ticket");
const {Op} = require("sequelize");

// exports function to get all trains
exports.getAll = async (req, res) => {
    try {
        // get all trains from the database
        const trains = await Trains.findAll({
            include: [{
                model: Classes, where: {
                    availableSeats: {
                        [Op.gt]: 0
                    }
                }, include: {model: Ticket}
            }]
        });
        return res.status(200).json(trains);
    } catch (error) {
        console.error(error);
        // return a json object with a message indicating an error occurred
        return res.status(500).json({message: 'An error occurred while fetching trains'});
    }
}

// exports function to get all trains from a specific station
exports.getAllFrom = async (req, res) => {
    try {
        // Find all trains from a specific station
        const trains = await Trains.findAll({
            where: {
                departure: req.params.from
            }, include: [{
                model: Classes, where: {
                    availableSeats: {
                        [Op.gt]: 0
                    }
                }, include: {model: Ticket}
            }]
        });
        return res.status(200).json(trains);
    } catch (error) {
        console.error(error);
        // return a json object with a message indicating an error occurred
        return res.status(500).json({message: 'An error occurred while fetching trains'});
    }
}

// exports function to get all trains from a specific station to another specific station
exports.getAllFromTo = async (req, res) => {
    try {
        // Find all trains from a specific station to another specific station include classes and ticket
        const trains = await Trains.findAll({
            where: {
                departure: req.params.from, arrival: req.params.to
            }, include: [{
                model: Classes, where: {
                    availableSeats: {
                        [Op.gt]: 0
                    }
                }, include: {model: Ticket}
            }]
        });
        return res.status(200).json(trains);
    } catch (error) {
        console.error(error);
        // return a json object with a message indicating an error occurred
        return res.status(500).json({message: 'An error occurred while fetching trains'});
    }
}

// exports function to get all trains from a specific station to another specific station on a specific date
exports.getAllFromToOnDate = async (req, res) => {
    try {
        // Find all trains from a specific station to another specific station on a specific date
        const trains = await Trains.findAll({
            where: {
                departure: req.params.from, arrival: req.params.to, departureDatetime: {
                    [Op.between]: [req.params.date + ' 00:00:00', req.params.date + ' 23:59:59']
                }
            }, include: [{
                model: Classes, where: {
                    availableSeats: {
                        [Op.gt]: 0
                    }
                }, include: {model: Ticket}
            }]
        });
        return res.status(200).json(trains);
    } catch (error) {
        console.error(error);
        // return a json object with a message indicating an error occurred
        return res.status(500).json({message: 'An error occurred while fetching trains'});
    }
}

// exports function to get all trains from a specific station to another specific station on a specific date in a specific class
exports.getAllFromToOnDateClass = async (req, res) => {
    try {
        // Find all trains from a specific station to another specific station on a specific date in a specific class
        const trains = await Trains.findAll({
            where: {
                departure: req.params.from, arrival: req.params.to, departureDatetime: {
                    [Op.between]: [req.params.date + ' 00:00:00', req.params.date + ' 23:59:59']
                }
            }, include: [{
                model: Classes, where: {
                    name: req.params.class, availableSeats: {
                        [Op.gt]: 0
                    }
                }, include: {model: Ticket}
            }]
        });
        return res.status(200).json(trains);
    } catch (error) {
        console.error(error);
        // return a json object with a message indicating an error occurred
        return res.status(500).json({message: 'An error occurred while fetching trains'});
    }
}

// exports function to get all trains from a specific station to another specific station on a specific date in a specific class with a minimum number of seats
exports.getAllFromToOnDateClassMinSeats = async (req, res) => {
    try {
        // Find all trains from a specific station to another specific station on a specific date in a specific class with a minimum number of seats
        const trains = await Trains.findAll({
            where: {
                departure: req.params.from, arrival: req.params.to, departureDatetime: {
                    [Op.between]: [req.params.date + ' 00:00:00', req.params.date + ' 23:59:59']
                }
            }, include: [{
                model: Classes, where: {
                    name: req.params.class, availableSeats: {
                        [Op.gte]: req.params.nbseats
                    }
                }, include: {model: Ticket}
            }]
        });
        return res.status(200).json(trains);
    } catch (error) {
        console.error(error);
        // return a json object with a message indicating an error occurred
        return res.status(500).json({message: 'An error occurred while fetching trains'});
    }
}
