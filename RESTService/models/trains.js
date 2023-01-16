const Sequelize = require('sequelize');
const sequelize = require('../database');

// define the trains model
const Train = sequelize.define('train', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    id_train: {
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
    departure_datetime: {
        type: Sequelize.DATE,
        allowNull: false
    },
}, {
    timestamps: false
});


module.exports = Train;

