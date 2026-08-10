package PizzaTopingDecorator;

class PrizeConfig {
    public static Double plainCost = 49.56;
    public static Double pepperoniCost = 13.35;
    public static Double mushroomCost = 24.66;
}

interface Pizza {
    public Double getCost();
}

class PlainPizza implements Pizza {
    @Override
    public Double getCost () {
        return PrizeConfig.plainCost;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected final Pizza inner;

    public PizzaDecorator (Pizza inner) {
        this.inner = inner;
    }
}

class PepperoniPizza extends PizzaDecorator {

    public PepperoniPizza (Pizza inner) {
        super(inner);
    }

    @Override
    public Double getCost() {
        return inner.getCost()+PrizeConfig.pepperoniCost;
    }
}

class MushroomPizza extends PizzaDecorator {

    public MushroomPizza (Pizza inner) {
        super(inner);
    }

    @Override
    public Double getCost() {
        return inner.getCost()+PrizeConfig.mushroomCost;
    }
}

public class Main {
    public static void main (String args[]){
        Pizza plainPizza = new PlainPizza();
        System.out.println("Plain Pizza: "+plainPizza.getCost());

        Pizza pepperoniPizza = new PepperoniPizza(plainPizza);
        System.out.println("Pepperoni Pizza: "+pepperoniPizza.getCost());

        Pizza mushroomPizza = new MushroomPizza(plainPizza);
        System.out.println("Mushroom Pizza: "+mushroomPizza.getCost());

        Pizza completePizza = new MushroomPizza(pepperoniPizza);
        System.out.println("Complete Pizza: "+completePizza.getCost());
    }
}
