const Sequelize = require('sequelize');
const db = require('../database');

// define the trains model
const Trains = db.define('trains', {
    id: {
        type: Sequelize.INTEGER,
        autoIncrement: true,
        allowNull: false,
        primaryKey: true
    },
    departure: {
        type: Sequelize.STRING,
        allowNull: false
    },
    arrival: {
        type: Sequelize.STRING,
        allowNull: false,
    },
    departure_datetime: {
        type: Sequelize.DATE,
        allowNull: false
    }
});

module.exports = Trains;
