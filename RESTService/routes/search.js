const controller = require('../controllers/search');
const router = require('express').Router();

router.get('/train', controller.getAll);
router.get('/train/FROM/:from', controller.getAllFrom);
router.get('/train/FROM/:from/TO/:to', controller.getAllFromTo);
router.get('/train/FROM/:from/TO/:to/ON/:date', controller.getAllFromToOnDate);


module.exports = router;