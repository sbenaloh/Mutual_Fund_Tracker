/**
 * Created by Steven on 3/20/2016.
 */

import java.io.*;
import java.util.*;

public class TestingMain {
    public static void main(String[] args) throws FileNotFoundException {
        Stock s1 = new Stock("MSFT", "Microsoft", 18.00);
        System.out.println(s1);
        File outputFile = new File("Stock.txt");
        PrintStream output = new PrintStream(outputFile);
        output.println(s1 + " 100");
        output.println(new Stock("APPL", "Apple", 22.50) + " 92");
        output.println(new Stock("JHNS", "Johnson&Johnson", 15.25) + " 500");

        Fund fund1 = new Fund("TICRX", "TIAA", 17.14, new Scanner(outputFile));
        System.out.println();
        fund1.printHoldings(System.out);
    }
}
