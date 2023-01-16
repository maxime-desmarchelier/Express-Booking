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
            id_train: req.params.id
        }, include: [{
            model: Classes,
            as: 'classes',
            where: {
                name: req.params.class,
                available_seats: {
                    [Op.gte]: req.params.seats
                }
            }
        }]
    }).then(train => {
        if (train) {
            Classes.update({
                available_seats: Sequelize.literal(`available_seats - ${req.params.seats}`)
            }, {
                where: {
                    name: req.params.class,
                    trainId: train.id
                }
            }).then(() => {
                res.status(200).send('Update successful');
            });
        } else {
            res.status(404).send('Train not found or not enough available seats');
        }
    }).catch(err => {
        console.log(err);
        res.status(500).send(err);
    });
}
