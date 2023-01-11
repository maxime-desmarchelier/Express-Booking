exports.helloWorld = async (req, res, next) => {
    try {
        res.status(200).json('Hello ' + req.params.name);
        next();
    } catch (error) {
        next(error);
    }
}