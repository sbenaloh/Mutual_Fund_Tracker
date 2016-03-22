/**
 * Created by Steven on 3/20/2016.
 *
 * This class keeps track of mutual fund data, such as the fund's ticker, the company that runs it, and the
 * individual stocks that make up the fund, and their respective quantities.
 */

import java.io.*;
import java.util.*;

public class Fund extends Asset {
    private Map<MyStock, Double> holdings;
    private double totalWorth;

    // POST: Constructs a new empty Fund object
    public Fund() {
        this("", "", 0, new Scanner(""));
    }

    // PRE : Inputted Scanner's contents are in standard format
    //       Inputted String ticker, String company are not empty, price is non-negative, otherwise throws
    //       IllegalArgumentException
    // POST: Constructs a new Fund object with the inputted ticker, company, and price.  Reads the composition
    //       of the fund from the inputted Scanner
    public Fund(String ticker, String company, double price, Scanner input) {
        if (ticker.isEmpty() || company.isEmpty() || price < 0)
            throw new IllegalArgumentException();
        this.ticker = ticker;
        this.company = company;
        this.price = price;
        holdings = new TreeMap<>();
        totalWorth = 0;
        while (input.hasNext()){
            String stockTicker = input.next();
            String stockCompany = input.next();
            double stockPrice = input.nextDouble();
            double quantity = input.nextDouble();
            holdings.put(new MyStock(stockTicker, stockCompany, stockPrice), quantity);
            totalWorth += price * quantity;
        }
    }

    // POST: Prints the Fund's holdings in order of total worth (price * quantity) to the inputted PrintStream
    public void printHoldings(PrintStream output) {
        Comparator<MyStock> comparator = new percentTotalComparator();

        holdings.keySet().stream()
                .sorted(comparator)
                .peek(output::print)
                .map(holdings::get)
                .map(s -> " " + s)
                .forEach(output::println);
    }

    private class percentTotalComparator implements Comparator<MyStock> {
        public int compare(MyStock x, MyStock y){
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
