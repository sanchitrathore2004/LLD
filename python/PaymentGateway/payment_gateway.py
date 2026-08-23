from abc import ABC, abstractmethod

class PaymentStrategy(ABC):
    @abstractmethod
    def pay(self, amount: float) -> None:
        pass

class CardStrategy(PaymentStrategy):
    def pay(self, amount: float) -> None:
        print("Paid", amount, "using Card")

class UpiStrategy(PaymentStrategy):
    def pay(self, amount: float) -> None:
        print("Paid", amount, "using UPI")

class PaymentProcessor:
    def __init__(self, strategy: PaymentStrategy) -> None:
        self.strategy = strategy
    def set_strategy(self, strategy: PaymentStrategy) -> None:
        self.strategy = strategy
    def process_payment(self, amount: float) -> None:
        self.strategy.pay(amount)

def main():
    payment_processor = PaymentProcessor(CardStrategy())
    payment_processor.process_payment(100.0)
    payment_processor = PaymentProcessor(UpiStrategy())
    payment_processor.process_payment(104.02)

if __name__=="__main__":
    main()