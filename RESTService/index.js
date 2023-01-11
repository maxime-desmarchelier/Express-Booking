const express = require('express');

const app = express();
app.use(express.json());
app.use(express.urlencoded({extended: true}));

app.use('/tests', require('./routes/tests'));

(async () => {
    try {
        app.listen(process.env.EXTERNAL_PORT);
        console.log('Server started on port ' + process.env.EXTERNAL_PORT);
    } catch (error) {
        console.log(error);
    }
})();
