const controller = require('../controllers/booking');
const router = require('express').Router();

router.put('/:id/CLASS/:class/SEATS/:seats', controller.updateSeats);

module.exports = router;