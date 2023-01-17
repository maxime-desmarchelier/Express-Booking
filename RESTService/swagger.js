const swaggerAutogen = require('swagger-autogen')()

const outputFile = './swagger_output.json'
const endpointsFiles = ['./routes/booking.js', './routes/search.js', './routes/tests.js']

swaggerAutogen(outputFile, endpointsFiles).then(() => {
});