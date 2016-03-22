/**
 * Created by Steven on 3/20/2016.
 */

import java.io.*;
import java.util.*;

public class Fund extends Asset {
    private Map<Stock, Double> holdings;
    private double totalWorth;

    public Fund() {
        holdings = new TreeMap<Stock, Double>();
        totalWorth = 0;
    }

    public Fund(String ticker, String company, double price, Scanner input) {
        this.ticker = ticker;
        this.company = company;
        this.price = price;
        holdings = new TreeMap<Stock, Double>();
        totalWorth = 0;
        while (input.hasNext()){
            String stockTicker = input.next();
            String stockCompany = input.next();
            double stockPrice = input.nextDouble();
            double quantity = input.nextDouble();
            holdings.put(new Stock(stockTicker, stockCompany, stockPrice), quantity);
            totalWorth += price * quantity;
        }
    }

    //Prints the holdings in order of percentage in the fund
    public void printHoldings(PrintStream output) {
        Comparator<Stock> comparator = new percentTotalComparator();
        //PriorityQueue<Stock> inOrder = new PriorityQueue<Stock>(comparator);

        holdings.keySet().stream()
                .sorted(comparator)
                .peek(output::print)
                .map(holdings::get)
                .map(s -> " " + s)
                .forEach(output::println);
    }

    private class percentTotalComparator implements Comparator<Stock> {
        public int compare(Stock x, Stock y){
            double xWorth = x.price * holdings.get(x);
            double yWorth = y.price * holdings.get(y);
            if (xWorth - yWorth > 0) {
                return -1;
            } else if (xWorth - yWorth < 0) {
                return 1;
            } else {
                return x.compareTo(y);
            }
        }
    }
}
