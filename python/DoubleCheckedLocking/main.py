import threading

class DoubleCheckedLocking:
    _instance = None
    _lock = threading.Lock()
    def __init__(self) -> None:
        if DoubleCheckedLocking._instance is not None:
            raise Exception("Use get_instance() instead.")

    @staticmethod
    def get_instance():
        if DoubleCheckedLocking._instance is None:
            with DoubleCheckedLocking._lock:
                if DoubleCheckedLocking._instance is None:
                    DoubleCheckedLocking._instance = DoubleCheckedLocking()
        return DoubleCheckedLocking._instance

def main():
    singleton = DoubleCheckedLocking.get_instance()
    print(singleton)
    singleton2 = DoubleCheckedLocking.get_instance()
    print(singleton2)

if __name__=="__main__":
    main()