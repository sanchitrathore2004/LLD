from abc import ABC, abstractmethod

class Middleware(ABC):
    def __init__(self) -> None:
        pass
    @abstractmethod
    def execute(self) -> None:
        pass

class BasicRequestHandler(Middleware):
    def __init__(self) -> None:
        pass
    def execute(self) -> None:
        print(f"Execute Basic Request Handler")

class MiddlewareDecorator(Middleware, ABC):
    def __init__(self, inner: Middleware) -> None:
        self.inner = inner

class RateLimitingDecorator(MiddlewareDecorator):
    def __init__(self, inner: Middleware) -> None:
        super().__init__(inner)

    def execute(self) -> None:
        print(f"Execute Rate Limiting Middleware")
        self.inner.execute()

class AuthenticationDecorator(MiddlewareDecorator):
    def __init__(self, inner: Middleware) -> None:
        super().__init__(inner)

    def execute(self) -> None:
        print(f"Execute Authentication Middleware")
        self.inner.execute()


def main():
    basic_request_handler = BasicRequestHandler()
    rate_limiting_middleware = RateLimitingDecorator(basic_request_handler)
    rate_limiting_middleware.execute()
    authentication_middleware = AuthenticationDecorator(rate_limiting_middleware)
    authentication_middleware.execute()

if __name__=="__main__":
    main()