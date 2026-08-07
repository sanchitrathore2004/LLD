package ShoppingCartSystem;

import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private Double price;

    public Product (String name, Double price) {
        this.name=name;
        this.price=price;
    }

    public String getName () {return name;}
    public Double getPrice () {return price;}
}

class Catalog {
    private List<Product> products;
    private Catalog catalog;

    private Catalog () {
        this.products=new ArrayList<>();
    }

    public Catalog createCatalog () {
        if(this.catalog!=null) return this.catalog;
        this.catalog=new Catalog();
        return this.catalog;
    }

    public Product findMyName (String name) {
        for(int i=0;i<products.size();i++){
            if(products.get(i).getName().equals(name)) return products.get(i);
        }
        return null;
    }
}

class Customer {
    private String name;
    private Cart cart;

    public void checkout () {
        cart.printAllItems();
        cart.printTotal();
        cart.clearCart();
    }

    public String getName () {return name;}
}

class Cart {
    private Customer owner;
    private List<Product> products;

    public Cart (Customer owner, List<Product> products) {
        this.owner=owner;
        this.products=products;
    }

    public void setOwner (Customer owner) {
        this.owner=owner;
    }

    public Customer getOwner () {return owner;}

    public void addItem (Product product) {
        products.add(product);
    }

    public void removeItem (Product product) {
        for(int i=0;i<products.size();i++){
            if(products.get(i).equals(product)) {
                products.remove(i);
                return ;
            }
        }
    }

    public void printAllItems () {
        for(int i=0;i<products.size();i++){
            System.out.println(products.get(i).getName()+" "+products.get(i).getPrice());
        }
    }

    public void printTotal () {
        Double total=0.00;
        for(int i=0;i<products.size();i++) total += products.get(i).getPrice();
        System.out.println("Total: "+total);
    }

    public void clearCart () {
        this.products=new ArrayList<>();
    }
}

public class Main {
    public static void main (String args[]){

    }
}
