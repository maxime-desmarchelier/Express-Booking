const controller = require('../controllers/search');
const router = require('express').Router();

router.get('/search/train', controller.getAll);
router.get('/search/train/FROM/:from', controller.getAllFrom);
router.get('/search/train/FROM/:from/TO/:to', controller.getAllFromTo);
router.get('/search/train/FROM/:from/TO/:to/ON/:date', controller.getAllFromToOnDate);
router.get('/search/train/FROM/:from/TO/:to/ON/:date/CLASS/:class', controller.getAllFromToOnDateClass);
router.get('/search/train/FROM/:from/TO/:to/ON/:date/CLASS/:class/MINSEATS/:nbseats', controller.getAllFromToOnDateClassMinSeats);

module.exports = router;