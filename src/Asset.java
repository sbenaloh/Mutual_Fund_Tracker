/**
 * Created by Steven on 3/20/2016.
 */
public class Asset implements Comparable<Asset> {
    public String ticker;
    public double price;
    public String company;

    public int compareTo(Asset other) {
        return ticker.compareTo(other.ticker);
    }

    public String toString(){
        String result = ticker + " " + company + " " + price;
        return result;
    }

}
