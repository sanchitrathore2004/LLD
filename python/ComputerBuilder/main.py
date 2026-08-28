class Computer:
    class Builder:
        def __init__(self, model: str):
            self.model = model
            self.ram = None
            self.disk = None
            self.cpu = None
            self.gpu = None
        def RAM(self, ram: int) -> "Computer.Builder":
            self.ram = ram
            return self
        def DISK(self, disk: int) -> "Computer.Builder":
            self.disk = disk
            return self
        def CPU(self, cpu: str) -> "Computer.Builder":
            self.cpu = cpu
            return self
        def GPU(self, gpu: str) -> "Computer.Builder":
            self.gpu = gpu
            return self
        def build(self) -> "Computer":
             return Computer(self)
        
    def __init__(self, builder: Builder) -> None:
            self.model = builder.model
            self.ram = builder.ram
            self.disk = builder.disk
            self.cpu = builder.cpu
            self.gpu = builder.gpu
    def show(self):
         print(f"Model: {self.model}, RAM: {self.ram}, DISK: {self.disk}, CPU: {self.cpu}, GPU: {self.gpu}")

def main():
     computer = Computer.Builder("AsusTuf").CPU("i7 13th Gen").GPU("NVIDIA RTX 3050ti").RAM(16).DISK(512).build()
     computer.show()

if __name__=="__main__":
     main()