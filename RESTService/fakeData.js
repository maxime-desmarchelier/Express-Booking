const sequelize = require('./database');
const Train = require('./models/trains');
const cls = require('./models/class');
const hoaxer = require('hoaxer');

function makeid(length) {
    var result = '';
    var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for (var i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
}

// Define a function to generate a random class
const generateRandomClass = (cls) => {
    return {
        name: cls,
        remaining_seats: hoaxer.random.number({min: 2, max: 15}),
        price: hoaxer.random.number({min: 30, max: 200})
    }
}
// Use a loop to create 10 trains
for (let i = 0; i < 10; i++) {
    let departure;
    let arrival;
    do {
        departure = hoaxer.random.arrayElement(["Paris", "Lyon", "Lille"]);
        arrival = hoaxer.random.arrayElement(["Paris", "Lyon", "Lille"]);
    } while (departure === arrival);
    Train.create({
        arrival: arrival,
        departure: departure,
        departure_datetime: hoaxer.date.between('2023-01-15', '2023-01-25'),
        id_train: makeid(5)
    }).then(train => {
        // Create 3 classes for each train
        for (let j = 0; j < 3; j++) {
            let arrcls = ['First', 'Standard', 'Business'];
            cls.create(generateRandomClass(arrcls[j])).then(ts => {
                train.addClass(ts);
            });
        }
    });
}