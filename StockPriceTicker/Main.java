package StockPriceTicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface StockObserver {
    public void update (StockExchange stockExchange);
}

interface StockSubject {
    public void registerObserver (StockObserver observer);
    public void removeObserver (StockObserver observer);
    public void notifyObservers();
}

class StockExchange implements StockSubject {
    private Map<String, Double> stocks = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    public Map<String, Double> getStock () {return stocks;}

    public void registerObserver (StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver (StockObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers () {
        for (StockObserver observer : observers) {
            observer.update(this);
        }
    }

    public void updatePrice (String shareName, Double price) {
        stocks.put(shareName, price);
        notifyObservers();
    }
}

class PriceDisplay implements StockObserver {
    public void update (StockExchange stockExchange) {
        System.out.println("Price Display");
        for(Map.Entry<String, Double> entry: stockExchange.getStock().entrySet()){
            String shareName = entry.getKey();
            Double price = entry.getValue();
            System.out.println("Share: "+shareName+" "+"Price: "+price);
        }
    }
}

class AlertService implements StockObserver {
    public void update (StockExchange stockExchange) {
        System.out.println("Alerting");
        for(Map.Entry<String, Double> entry: stockExchange.getStock().entrySet()){
            String shareName = entry.getKey();
            Double price = entry.getValue();
            System.out.println("Share: "+shareName+" "+"Price: "+price);
        }
    }
}

class TradingBot implements StockObserver {
    public void update (StockExchange stockExchange) {
        System.out.println("Trading Bot Logs");
        for(Map.Entry<String, Double> entry: stockExchange.getStock().entrySet()){
            String shareName = entry.getKey();
            Double price = entry.getValue();
            System.out.println("Share: "+shareName+" "+"Price: "+price);
        }
    }
}

public class Main {
    public static void main (String args[]) {
        StockExchange stockExchange = new StockExchange();
        PriceDisplay priceDisplay = new PriceDisplay();
        TradingBot tradingBot = new TradingBot();
        AlertService alertService = new AlertService();
        stockExchange.registerObserver(priceDisplay);
        stockExchange.registerObserver(tradingBot);
        stockExchange.registerObserver(alertService);
        stockExchange.updatePrice("SAP", 147.28);
        stockExchange.updatePrice("Goldman Sachs", 198.87);
        stockExchange.updatePrice("Oracle", 165.87);
        stockExchange.removeObserver(tradingBot);
        stockExchange.updatePrice("Nvidia", 134.98);
    }
}
