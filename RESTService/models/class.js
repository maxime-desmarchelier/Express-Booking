const Sequelize = require("sequelize");
const sequelize = require("../database");
const Train = require("./trains");


const Cls = sequelize.define('class', {
    name: {
        type: Sequelize.ENUM('First', 'Standard', 'Business')
    },
    remaining_seats: {
        type: Sequelize.INTEGER,
        allowNull: false
    }
});

Train.hasMany(Cls);
Cls.belongsTo(Train);

module.exports = Cls;