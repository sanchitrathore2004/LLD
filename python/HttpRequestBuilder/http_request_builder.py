class HttpRequest:
    class Builder:
        def __init__(self, url: str) -> None:
            self.url = url
            self.method = None
            self.headers = dict()
            self.params = dict()
            self.timeout = None
        def set_method(self, method: str) -> "HttpRequest.Builder":
            self.method = method
            return self
        def set_headers(self, headers: dict) -> "HttpRequest.Builder":
            self.headers = headers
            return self
        def set_params(self, params: dict) -> "HttpRequest.Builder":
            self.params = params
            return self
        def set_timeout(self, timeout: int) -> "HttpRequest.Builder":
            self.timeout = timeout
            return self
        def build(self) -> "HttpRequest":
            return HttpRequest(self)

    def __init__(self, builder: Builder) -> None:
        self.url = builder.url
        self.method = builder.method
        self.headers = builder.headers
        self.params = builder.params
        self.timeout = builder.timeout

    def display(self) -> None:
        print("Http Request")
        print("URL: ", self.url)
        print("Method: ", self.method)
        print("Headers: ", self.headers)
        print("Params: ", self.params)
        print("Timeout: ", self.timeout)

def main():
    http_request = HttpRequest.Builder("https://internbook.live").set_method("POST").set_headers({"Bearer ": "Token"}).set_params({"id": 1}).set_timeout(3000).build()
    http_request.display()

if __name__=="__main__":
    main()