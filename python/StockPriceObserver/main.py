from abc import ABC, abstractmethod
import time

class Observer:
    def __init__(self) -> None:
        pass
    @abstractmethod
    def update(self, price: dict):
        pass

class User(Observer):
    def __init__(self, name: str) -> None:
        self.name = name
    def update(self, price: dict) -> None:
        print(f"User: {self.name}")
        for key, val in price.items():
            print(f"Name: {key} Price: {val}")

class StockSubject(ABC):
    def __init__(self) -> None:
        pass

    @abstractmethod
    def add_observer(self, user: User) -> None:
        pass
    @abstractmethod
    def remove_observer(self, user: User) -> None:
        pass
    @abstractmethod
    def notify_observers(self) -> None:
        pass

class StockTicker(StockSubject):
    def __init__(self) -> None:
        self.observers = []
        self.prices = {}
    def add_observer(self, user: User) -> None:
        return self.observers.append(user)
    def remove_observer(self, user: User) -> None:
        return self.observers.remove(user)
    def notify_observers(self) -> None:
        for observer in self.observers:
            observer.update(self.prices)
    def update_price(self, name: str, price: float) -> None:
        self.prices[name]=price
        self.notify_observers()

def main():
    user1 = User("sanchit")
    user2 = User("tanushri")
    stock_ticker = StockTicker()
    stock_ticker.add_observer(user1)
    stock_ticker.add_observer(user2)
    stock_ticker.update_price("SAP", 254.1256)
    time.sleep(2)
    stock_ticker.update_price("GoldmanSachs", 436.15456)
    time.sleep(2)
    stock_ticker.update_price("Oracle", 136.1374676)

if __name__=="__main__":
    main()