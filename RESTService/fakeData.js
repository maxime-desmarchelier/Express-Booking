const Train = require('./models/trains');
const cls = require('./models/class');
const Ticket = require('./models/ticket');

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

function generateFakeData() {
    Train.findAll().then((trains) => {
        // If there are no trains in the database, generate some
        if (trains.length === 0) {
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
                    departureDatetime: hoaxer.date.between('2023-01-15', '2023-01-25'),
                    idTrain: makeid(5)
                }).then(train => {
                    // Create 3 classes for each train
                    for (let j = 0; j < 3; j++) {
                        let arrcls = ['First', 'Standard', 'Business'];
                        cls.create(generateRandomClass(arrcls[j])).then(ts => {
                            let arrticket = ['flexible', 'non_flexible'];
                            let price = hoaxer.datatype.number({min: 30, max: 200});
                            for (let k = 0; k < 2; k++) {
                                Ticket.create({type: arrticket[k], price: price + k * 15}).then(tkt => {
                                    ts.addTicket(tkt);
                                });
                            }
                            train.addClass(ts);
                        });
                    }
                });
            }
        }
    });
}

// Define a function to generate a random class
const generateRandomClass = (cls) => {
    return {
        name: cls,
        availableSeats: hoaxer.datatype.number({min: 2, max: 15}),
        price: hoaxer.datatype.number({min: 30, max: 200})
    }
}


module.exports = {generateFakeData};
