import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.annotation.Documented;

public class BettingInfoScrapper {
    public static void main(String[] args) {
        final String url = "https://hintwise.com/";

        try{
            final Document bettingInfo = Jsoup.connect(url).get();

            int count = 1;
            for(Element row : bettingInfo.select("table.items tr")){
//                if(count == 40){
//                    break;
//                }
                if(row.select("tr.odd:nth-of-type(1).cellStyle.cell40").text().equals(null)){

                }else{
                    final String time = row.select(".cellStyle.cell40").text();
                    final String teams = row.select(".cellTEAMS.cellStyle").text();
                    final String division = row.select(".hidden-xs.cellStyle").text();

                    String prediction = row.select(".cellStyle.lr20.cell90").text();
                    prediction = prediction.replace("Away", "A");
                    prediction = prediction.replace("Draw", "D");
                    prediction = prediction.replace("Home", "H");

                    if(!prediction.isEmpty())
                    {

                    }

                    System.out.println(time + "\t" + teams + "\t" + division + "\t" + prediction);


                }
                count++;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
