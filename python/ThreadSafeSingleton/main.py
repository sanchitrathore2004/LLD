import threading

class ThreadSafeSingleton:
    _instance = None
    _lock = threading.Lock()
    def __init__(self) -> None:
        if ThreadSafeSingleton._instance is not None:
            raise Exception("Use get_instance() instead.")

    @staticmethod
    def get_instance():
        with ThreadSafeSingleton._lock:
         if ThreadSafeSingleton._instance is None:
                ThreadSafeSingleton._instance = ThreadSafeSingleton()
        print(id(ThreadSafeSingleton._instance))
        return ThreadSafeSingleton._instance

def main():
    threads = [threading.Thread(target=ThreadSafeSingleton.get_instance) for _ in range(5)]
    [t.start() for t in threads]

if __name__=="__main__":
    main()