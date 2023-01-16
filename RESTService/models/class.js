const Sequelize = require("sequelize");
const sequelize = require("../database");
const Train = require("./trains");


const Cls = sequelize.define('class', {
    name: {
        type: Sequelize.ENUM('First', 'Standard', 'Business')
    },
    availableSeats: {
        type: Sequelize.INTEGER,
        allowNull: false
    }
}, {
    timestamps: false
});

Train.hasMany(Cls);
Cls.belongsTo(Train);

module.exports = Cls;