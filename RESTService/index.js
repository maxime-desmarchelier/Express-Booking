const express = require('express');

const app = express();
app.use(express.json());
app.use(express.urlencoded({extended: true}));

app.use('/tests', require('./routes/tests'));

(async () => {
    try {
        let port = 3000;
        if (process.env.EXTERNAL_PORT) {
            port = process.env.EXTERNAL_PORT;
        }
        app.listen(port);
        console.log('Server started on port ' + port);
    } catch (error) {
        console.log(error);
    }
})();
