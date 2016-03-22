/**
 * Created by Steven on 3/20/2016.
 */

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.*;
import java.util.*;
import yahoofinance.*;
import java.math.*;

public class TestingMain {
    public static void main(String[] args) throws FileNotFoundException {
        MyStock s1 = new MyStock("MSFT", "Microsoft", 18.00);
        System.out.println(s1);
        File outputFile = new File("MyStock.txt");
        PrintStream output = new PrintStream(outputFile);
        output.println(s1 + " 100");
        output.println(new MyStock("APPL", "Apple", 22.50) + " 92");
        output.println(new MyStock("JHNS", "Johnson&Johnson", 15.25) + " 500");

        Fund fund1 = new Fund("TICRX", "TIAA", 17.14, new Scanner(outputFile));
        System.out.println();
        fund1.printHoldings(System.out);

        try {
            Stock s2 = YahooFinance.get("TICRX");
            BigDecimal price = s2.getQuote().getPrice();
            System.out.println();
            System.out.println(price);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
