from abc import ABC, abstractmethod

class Notification(ABC):
    def __init__(self) -> None:
        pass

    @abstractmethod
    def send(self, msg: str) -> None:
        pass

class Sms(Notification):
    def __init__(self) -> None:
        pass

    def send(self, msg: str) -> None:
        print(f"Sending {msg} via SMS.")

class Email(Notification):
    def __init__(self) -> None:
        pass

    def send(self, msg: str) -> None:
        print(f"Sending {msg} via Email.")

class NotificationCreator(ABC):
    def __init__(self) -> None:
        pass

    @abstractmethod
    def createNotification(self) -> Notification:
        pass

    def send(self, msg: str) -> None:
        self.createNotification().send(msg=msg)

class SmsCreator(NotificationCreator):
    def __init__(self) -> None:
        pass

    def createNotification(self) -> Notification:
        return Sms()

class EmailCreator(NotificationCreator):
    def __init__(self) -> None:
        pass

    def createNotification(self) -> Notification:
        return Email()


def main():
    sms = SmsCreator()
    sms.send("hello")
    email = EmailCreator()
    email.send("hello")

if __name__=="__main__":
    main()
