class LazySingleton:
    _instance = None
    def __init__(self) -> None:
        if LazySingleton._instance is not None:
            raise Exception(f"Use get_instance() instead.")

    @staticmethod
    def get_instance():
        if LazySingleton._instance is None:
            LazySingleton._instance = LazySingleton()
        return LazySingleton._instance

def main():
    singleton = LazySingleton.get_instance()
    print(singleton._instance)
    singleton2 = LazySingleton.get_instance()
    print(singleton2)

if __name__=="__main__":
    main()