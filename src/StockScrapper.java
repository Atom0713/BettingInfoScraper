import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class StockScrapper {

    public static void main(String[] args) {
        final String url = "https://sharestobeclosed.telegraph.co.uk/indices/";

        try
        {
            final Document document = Jsoup.connect(url).get();

            //loops through each row
            for(Element row : document.select(//header:table; class:tablesorte.full; row;tr
                    "table.tablesorter.full tr")){
                /*
                * Some tables have an empty first element, code below skips it.
                 */
                if(row.select("td:nth-of-type(1)").text().equals(null)){
                }else{
                    final String ticker = row.select("td:nth-of-type(1)").text();
                    final String name = row.select("td:nth-of-type(2)").text();
                    //get price in original state
                    final String tempPrice = row.select("td.right:nth-of-type(3)").text();
                    //get rid of commas in prices, Double wont accept commas.

                    final String priceWithoutCommas = tempPrice.replace(",", "");
                    if(!priceWithoutCommas.isEmpty()){
                        final Double price = Double.parseDouble(priceWithoutCommas);
                        //System.out.println(price);
                        System.out.println(ticker + "\t" + name +"\t" + price);
                    }




                }
            }

        }catch (Exception ex)
        {//doesn't solve the problem just specifies it.
            ex.printStackTrace();
        }



    }
}
