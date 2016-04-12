/**
 * Created by Steven on 3/20/2016.
 */

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.*;
import java.util.*;
import java.net.*;

public class TestingMain {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException {
        //printMultiple();
        readFundData();
    }

    private static void readFundData() throws IOException {
        Scanner dataFile = new Scanner(new URL("https://www.sec.gov/Archives/edgar/data/1084380/000093041316006085/0000930413-16-006085.txt").openStream());
        Scanner console = new Scanner(System.in);
        String cont = "Y";

        while (!cont.equals("n")) {
            System.out.print("Read next? ");
            cont = console.nextLine();
            if (dataFile.hasNext()) {
                System.out.println(dataFile.next());
            } else {
                System.out.println("Data file empty");
            }
        }
    }

    private static StockQuote getStockQuote(Stock s) {
        try {
            return s.getQuote(true);
        } catch (IOException e) {
            return new StockQuote("");
        }
    }

    private static void printMultiple() throws FileNotFoundException, InterruptedException {
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
                if (i < 2) Thread.sleep(5000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
