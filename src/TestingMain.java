/**
 * Created by Steven on 3/20/2016.
 */

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.*;
import java.util.*;
import java.math.*;

public class TestingMain {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
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
            Stock s2 = YahooFinance.get("^IXIC");
            File testFile = new File("testFile.txt");
            PrintStream testStream =  new PrintStream(testFile);

            String[] tickers = {"MSFT", "AAPL", "INTC", "^IXIC", "TICRX"};
            Map<String, Stock> multiple = YahooFinance.get(tickers);

            for (int i = 0; i < 3; i++) {
                multiple.keySet().stream()
                        .peek(s -> testStream.print(s + " "))
                        .map(multiple::get)
                        .map(TestingMain::getStockQuote)
                        .map(StockQuote::getPrice)
                        .forEach(testStream::println);
//                StockQuote quote = s2.getQuote(true);
//                testStream.println(quote.getPrice());
                Thread.sleep(5000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StockQuote getStockQuote(Stock s) {
        try {
            return s.getQuote(true);
        } catch (IOException e) {
            return new StockQuote("");
        }
    }
}
