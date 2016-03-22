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
