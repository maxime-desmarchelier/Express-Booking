const controller = require('../controllers/tests');
const router = require('express').Router();

router.get('/hello/:name', controller.helloWorld);
router.get('/db', controller.dbConnection);

module.exports = router;