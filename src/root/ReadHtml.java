package root;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class ReadHtml {
    private static final String url = "https://hintwise.com/";

    public ReadHtml() {
    }

    public ReadHtml(String[] sportType ) {
        for (String sport : sportType){

            ArrayList<String[]> data = readInformation(sport);
            WriteToExcelSheet outputStream = new WriteToExcelSheet(sport, data);


        }

    }

    public static String getUrl() {
        return url;
    }

    private static ArrayList<String[]> readInformation(String sportType){

        ArrayList<String[]> rows = new ArrayList<String[]>();
        rows.add(new String[]{"Time", "Home vs Away", "League", "Prediction",
                " Odds Home Win", "Odds Draw", "Odds Away Win"});

        try{
            /*
             * Extract store and write scrapped data.
             */
            final Document bettingInfo = Jsoup.connect(url).get();
            System.out.println(bettingInfo);

            for(Element rowElement : bettingInfo.select(sportType +  " table.items tr")){



                if(rowElement.select(".cellStyle.cell40").text().equals("")) {
                    continue;
                }
                final String time = rowElement.select("td.cellStyle.cell40").text();
                final String teams = rowElement.select("td.cellTEAMS").text();
                final String division = rowElement.select("td.hidden-xs.cellStyle").text();

                String prediction = rowElement.select(".cellStyle.lr20.cell90").text();

                String oddsHomeWin = " ", oddsDraw = " ", oddsAwayWin = " ";
                switch (sportType.replace("#", "")){
                    case "soccer":


                        oddsHomeWin = rowElement.select("td.cellOdds.homewin input.btn-info").attr("value");
                        oddsDraw = (rowElement.select("td.cellOdds input[name=yt1]").attr("value"));
                        oddsAwayWin = (rowElement.select("td.cellOdds input[name=yt2]").attr("value"));
                        break;
                    case "basketball":
                        // TODO parse basketball games info 
                        System.out.println("Basketball");
                        // merge changes to develop
                        
                        break;
                    case "tennis":
                        //System.out.println(rowElement.select("div.tennis td.cellOdds.winLose.homewin input.btn.btn-xs.btn-default.btn-addToBlog").attr("value"));
                        oddsHomeWin = rowElement.select("div.tennis td.cellOdds.winLose.homewin input.btn.btn-xs.btn-default.btn-addToBlog").attr("value");
                        //oddsAwayWin = Double.parseDouble(rowElement.select("td.winLose.cellOdds:nth-of-type(11) input.btn.btn-xs.btn-info.btn-addToBlog").attr("value"));
//                            final String lastFiveHomeLose = rowElement.select("td.center.cellLast:nth-of-type(8)  div.winline-style.lose-background").text();
//                            final String lastFiveHomeWin = rowElement.select("td.center.cellLast:nth-of-type(8)  div.winline-style.win-background").text();
//                            lastFiveHome = lastFiveHome.concat(lastFiveHomeLose).concat(lastFiveHomeWin);
//
//                            final String lastFiveAwayLose = rowElement.select("td.center.cellLast:nth-of-type(9)  div.winline-style.lose-background").text();
//                            final String lastFiveAwayWin = rowElement.select("td.center.cellLast:nth-of-type(9) div.winline-style.win-background").text();
//                            lastFiveAway = lastFiveAway.concat(lastFiveAwayLose).concat(lastFiveAwayWin);
                        break;
                    case "hockey":
                        break;
                }
                rows.add(new String[]{time.strip(), teams.strip(), division.strip(), prediction.strip(), oddsHomeWin, oddsDraw, oddsAwayWin});
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return rows;
    }

}
