const Trains = require("../models/trains");
const Classes = require("../models/class");
const Sequelize = require('sequelize');
const {Op} = require("sequelize");


exports.updateSeats = async (req, res) => {
    if (!(['Standard', 'First', 'Business'].includes(req.params.class))) {
        res.status(404).send('Unknown class');
        return;
    }
    Trains.findOne({
        where: {
            idTrain: req.params.id
        }, include: [{
            model: Classes, as: 'classes', where: {
                name: req.params.class, availableSeats: {
                    [Op.gte]: req.params.seats
                }
            }
        }]
    }).then(train => {
        if (train) {
            Classes.findOne({
                where: {
                    name: req.params.class, trainId: train.id
                }
            }).then((classe) => {
                if (classe) {
                    classe.availableSeats = classe.availableSeats - req.params.seats;
                    classe.save();
                    res.status(200).send('Seats updated');
                } else {
                    res.status(400).send('Class not found');
                }
            });
        } else {
            res.status(400).send('Train not found or not enough available seats');
        }
    }).catch(err => {
        console.log(err);
        res.status(500).send(err);
    });
}
