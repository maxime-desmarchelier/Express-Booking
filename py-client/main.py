import json

from client import TrainBookingWSClient

ws = TrainBookingWSClient()


def login():
    user = input("Enter your username: ")
    password = input("Enter your password: ")
    if ws.connexion(user, password):
        print("Connexion successful")
        menu()
    else:
        print("Connexion failed")
        main()


def searchParams():
    data = {"from": "", "to": "", "date": "", "class": "", "minseats": 1}
    departure = input("Enter your departure (X to search): ")
    if departure == "X":
        return data
    data["from"] = departure
    destination = input("Enter your destination (X to search): ")
    if destination == "X":
        return data
    data["to"] = destination
    date = input("Enter your date (X to search): ")
    if date == "X":
        return data
    data["date"] = date
    _class = input("Enter your class (X to search): ")
    if _class == "X":
        return data
    data["class"] = _class
    minseats = input("Enter your minseats (X to search): ")
    if minseats == "X":
        return data
    data["minseats"] = minseats


def search():
    print("Recherche")
    data = searchParams()
    trains = ws.search(data)
    if len(trains) == 0:
        print("No train found")
        menu()
    print("Trains found:")
    for train in trains:
        print("========= %s =========" % train["idTrain"])
        print("%s -> %s : %s" % (train.departure, train.arrival, train.departureDatetime[:19].replace("T", " ")))
        for _class in train["classes"]:
            print("  %s - seats available : %s" % (_class["name"], _class["availableSeats"]))
            for ticket in _class["tickets"]:
                print("    %s : %d€" % (ticket["type"].replace("_", " "), ticket["price"]))

    book()


def book():
    print("Book")
    data = {"company": "SNCF", "idTrain": input("Enter your train id: "), "class": input("Enter your class: "),
            "NBSeats": int(input("Enter your number of seats: "))}
    try:
        if ws.book(data):
            print("Booking successful")
        else:
            print("Booking failed")
    except Exception as e:
        print("Booking failed")
        print(e)

    menu()


def menu():
    print("1. Recherche")
    print("2. Book")
    print("3. Retour")
    choice = input("Enter your choice: ")
    if choice == "1":
        search()
    elif choice == "2":
        book()
    elif choice == "3":
        main()


def signup():
    print("Inscription")
    user = input("Enter your username: ")
    password = input("Enter your password: ")
    if ws.signup(user, password):
        print("Inscription successful")
    else:
        print("Inscription failed")
    main()


def main():
    print("Welcome to trainbooking system")
    print("1. Connexion")
    print("2. Inscription")
    print("3. Quitter")
    choice = input("Enter your choice: ")
    if choice == "1":
        login()
    elif choice == "2":
        signup()
    elif choice == "3":
        exit()


if __name__ == "__main__":
    main()
