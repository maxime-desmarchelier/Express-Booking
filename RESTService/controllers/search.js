const Trains = require("../models/trains");
const {Op} = require("sequelize");

// exports function to get all trains
exports.getAll = async (req, res) => {
    try {
        // get all trains from the database
        const trains = await Trains.findAll();
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
            }
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
        // Find all trains from a specific station to another specific station
        const trains = await Trains.findAll({
            where: {
                departure: req.params.from,
                arrival: req.params.to
            }
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
                departure: req.params.from,
                arrival: req.params.to,
                departure_datetime: {
                    [Op.between]: [req.params.date + ' 00:00:00', req.params.date + ' 23:59:59']
                }
            }
        });
        return res.status(200).json(trains);
    } catch (error) {
        console.error(error);
        // return a json object with a message indicating an error occurred
        return res.status(500).json({message: 'An error occurred while fetching trains'});
    }
}
