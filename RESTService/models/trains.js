const Sequelize = require('sequelize');
const sequelize = require('../database');

// define the trains model
const Train = sequelize.define('train', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    idTrain: {
        type: Sequelize.STRING,
        allowNull: false
    },
    arrival: {
        type: Sequelize.STRING,
        allowNull: false
    },
    departure: {
        type: Sequelize.STRING,
        allowNull: false
    },
    departureDatetime: {
        type: Sequelize.DATE,
        allowNull: false
    },
}, {
    timestamps: false
});


module.exports = Train;

