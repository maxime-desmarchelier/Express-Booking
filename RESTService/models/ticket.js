const Sequelize = require('sequelize');
const sequelize = require('../database');
const Cls = require("./class");

// define the ticket model
const Ticket = sequelize.define('ticket', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    type: {
        type: Sequelize.ENUM('flexible', 'non_flexible'),
        allowNull: false
    },
    price: {
        type: Sequelize.FLOAT,
        allowNull: false
    }
}, {
    timestamps: false
});

Cls.hasMany(Ticket);
Ticket.belongsTo(Cls);

module.exports = Ticket;