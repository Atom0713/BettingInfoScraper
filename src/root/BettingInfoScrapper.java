/* *****************************************************************************
 *  Name:              Artem Slyusarenko
 *  Last modified:     06/08/2019
 **************************************************************************** */

package root;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;


public class BettingInfoScrapper {

    //Declare  blank sheet
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    private static XSSFSheet sheet;
    private static final String url = "https://hintwise.com/";




    public static void main(String[] args) {
         // Read and write soccer data!
        readInformation("#soccer");
        // Read and write basketball data!
        readInformation("#basketball");
        // Read and write tennis data!
        readInformation("#tennis");
        // Read and write hockey data!
        readInformation("#hockey");

        autisizeColumns(workbook,4,8);

        try
        {
            //Write workbook into file system
            FileOutputStream out = new FileOutputStream(new File("logs/BettingInformation.xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (Exception e) { e.printStackTrace(); }

    }

    private static void readInformation(String sportType){

        /*
        * Style and font attributes of the excel sheets.
         */
        XSSFCellStyle headers = workbook.createCellStyle();
        XSSFCellStyle tableStyle = workbook.createCellStyle();

        XSSFFont headersFont = workbook.createFont();
        XSSFFont tableFont = workbook.createFont();

        headersFont.setBold(true);
        headersFont.setFontHeightInPoints((short)10);
        tableFont.setFontHeightInPoints((short)8);

        tableStyle.setFont(tableFont);
        tableStyle.setWrapText(true);
        tableStyle.setAlignment(HorizontalAlignment.CENTER);
        tableStyle.setVerticalAlignment(VerticalAlignment.CENTER);


        headers.setFont(headersFont);
        headers.setFillForegroundColor(IndexedColors.YELLOW.index);
        headers.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headers.setBorderBottom(BorderStyle.THIN);
        headers.setBorderRight(BorderStyle.THIN);
        headers.setVerticalAlignment(VerticalAlignment.CENTER);
        headers.setAlignment(HorizontalAlignment.CENTER);


        //Declare couple neccessary objectsand variables,
        Object[] objArr = null;
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        int cellNum = 0;

        //Create sheet named by the type of sport
        sheet = workbook.createSheet(sportType.replace("#",""));

        /*
        * Fill first row of a sheet with column headers
         */
        row = sheet.createRow(rowNum++);
        for (Object obj : new String[]{"Time", "Home vs Away", "League", "Prediction", "Last 5 Home", "Last 5 Away",
                " Odds Home Win", "Odds Away Win"})
        {
            cell = row.createCell(cellNum++);
            cell.setCellStyle(headers);
            cell.setCellValue((String)obj);

        }
        String[] elementRow = new String[8];

        try{
            /*
            * Extract store and write scrapped data.
             */
            final Document bettingInfo = Jsoup.connect(url).get();

            for(Element rowElement : bettingInfo.select(sportType +  " table.items tr")){

                if(!rowElement.select(".cellStyle.cell40").text().equals("")){
                    final String time = rowElement.select(".cellStyle.cell40").text();
                    final String teams = rowElement.select(".cellTEAMS.cellStyle").text();
                    final String division = rowElement.select(".hidden-xs.cellStyle").text();

                    String lastFiveHome = "";
                    String lastFiveAway = "";
                    Double oddsHomeWin = 0.00;
                    Double oddsAwayWin = 0.00;
                    Double oddsDraw = 0.00;
                    switch (sportType.replace("#", "")){
                        case "soccer":
                            oddsHomeWin = Double.parseDouble(rowElement.select(".homewin.winLose_Soccer.cellOdds input.btn.btn-xs.btn-info.btn-addToBlog").attr("value"));
                            oddsAwayWin = Double.parseDouble(rowElement.select("td.winLose_Soccer.cellOdds:nth-of-type(19) input.btn.btn-xs.btn-info.btn-addToBlog").attr("value"));
                            break;
                        case "basketball":
                            break;
                        case "tennis":
                            System.out.println(rowElement.select("div.tennis td.cellOdds.winLose.homewin input.btn.btn-xs.btn-default.btn-addToBlog").attr("value"));
                            oddsHomeWin = Double.parseDouble(rowElement.select("div.tennis td.cellOdds.winLose.homewin input.btn.btn-xs.btn-default.btn-addToBlog").attr("value"));
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
                    //final Double oddsAwayWin = Double.parseDouble(rowElement.select("td.winLose.cellOdds:nth-of-type(11)").text());

                    String prediction = rowElement.select(".cellStyle.lr20.cell90").text();
                    prediction = prediction.replace("Away", "A");
                    prediction = prediction.replace("Draw", "D");
                    prediction = prediction.replace("Home", "H");

                    elementRow[0] = time;
                    elementRow[1] = teams;
                    elementRow[2] = division;
                    elementRow[3] = prediction;
                    //elementRow[4] = lastFiveHome;
                    //elementRow[5] = lastFiveAway;
                    elementRow[6] = oddsHomeWin.toString();
                    elementRow[7] = oddsAwayWin.toString();

                    row = sheet.createRow(rowNum++);

                    objArr = elementRow;
                    cellNum = 0;
                    for (Object obj : objArr) {
                        cell = row.createCell(cellNum++);
                        cell.setCellStyle(tableStyle);

                        if (obj instanceof String) {
                            cell.setCellValue((String) obj);

                        } else if (obj instanceof Integer) {
                            cell.setCellValue((Integer) obj);
                        }
                    }
                }
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void autisizeColumns(Workbook workbook, int sheetNum, int colNum)
    {
        for(int sheets = 0; sheets < sheetNum; sheets++)
        {
            for(int columns = 0; columns < colNum; columns++)
            {
                workbook.getSheetAt(sheets).autoSizeColumn(columns);
            }
        }
    }

}
