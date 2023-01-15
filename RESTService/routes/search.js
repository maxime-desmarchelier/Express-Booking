const controller = require('../controllers/search');
const router = require('express').Router();

router.get('/train', controller.getAll);
router.get('/train/FROM/:from', controller.getAllFrom);
router.get('/train/FROM/:from/TO/:to', controller.getAllFromTo);
router.get('/train/FROM/:from/TO/:to/ON/:date', controller.getAllFromToOnDate);
router.get('/train/FROM/:from/TO/:to/ON/:date/CLASS/:class', controller.getAllFromToOnDateClass);
router.get('/train/FROM/:from/TO/:to/ON/:date/CLASS/:class/MINSEATS/:nbseats', controller.getAllFromToOnDateClassMinSeats);


module.exports = router;