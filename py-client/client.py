from zeep import Client


class TrainBookingWSClient:
    def __init__(self):
        self.authEndpoint = Client('http://localhost:8080/ws/auth.wsdl')
        self.bookEndpoint = Client('http://localhost:8080/ws/booking.wsdl')
        self.searchEndpoint = Client('http://localhost:8080/ws/train.wsdl')
        self.token = None

    def connexion(self, user, password):
        self.token = self.authEndpoint.service.Auth(user, password)
        return self.token != "0"

    def signup(self, user, password):
        return self.authEndpoint.service.AuthCreate(user, password)

    def search(self, data):
        return self.searchEndpoint.service.Search(**data)

    def book(self, data):
        data["token"] = self.token
        return self.bookEndpoint.service.BookTicket(**data)
