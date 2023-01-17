const controller = require('../controllers/tests');
const router = require('express').Router();

router.get('/tests/hello/:name', controller.helloWorld);
router.get('/tests/db', controller.dbConnection);

module.exports = router;