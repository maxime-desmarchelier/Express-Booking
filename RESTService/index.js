const express = require('express');

const app = express();
app.use(express.json());
app.use(express.urlencoded({extended: true}));

app.use('/tests', require('./routes/tests'));

(async () => {
    try {
        app.listen(3000);
        console.log('Server started on port 3000');
    } catch (error) {
        console.log(error);
    }
})();
