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
import java.util.ArrayList;
import java.util.TreeMap;

public class BettingInfoScrapper {
//    //private static ArrayList<String[]> soccer = new ArrayList<>();
//    private static ArrayList<String[]> basketball = new ArrayList<>();
//    private static ArrayList<String[]> tennis = new ArrayList<>();
//    private static ArrayList<String[]> hockey = new ArrayList<>();
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    //Declare  blank sheet
    private static XSSFSheet sheet;



    public static void main(String[] args) {
        readInformation("#soccer");
//        System.out.println();
//        readInformation("#basketball");
//        System.out.println();
//        readInformation("#tennis");
//        System.out.println();
//        readInformation("#hockey");

        try
        {
            //Write workbook into file system
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\slius\\OneDrive\\Рабочий стол\\BettingInformation.xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (Exception e) { e.printStackTrace(); }

    }

    private static void readInformation(String sportType){
        System.out.println("Read " + sportType + " data!");
        ArrayList<String[]> dataHolder = new ArrayList<>();
        String[] elementRow = new String[4] ;
        final String url = "https://hintwise.com/";

        try{
            final Document bettingInfo = Jsoup.connect(url).get();

            for(Element row : bettingInfo.select(sportType +  " table.items tr")){


                final String time = row.select(".cellStyle.cell40").text();
                final String teams = row.select(".cellTEAMS.cellStyle").text();
                final String division = row.select(".hidden-xs.cellStyle").text();

                String prediction = row.select(".cellStyle.lr20.cell90").text();
                prediction = prediction.replace("Away", "A");
                prediction = prediction.replace("Draw", "D");
                prediction = prediction.replace("Home", "H");
                elementRow[0] = time;
                elementRow[1] = teams;
                elementRow[2] = division;
                elementRow[3] = prediction;
                //System.out.println(elementRow[0] + elementRow[1] + elementRow[2] + elementRow[3]);

                dataHolder.add(elementRow);

                //System.out.println(time + "\t" + teams + "\t" + division + "\t" + prediction);
            }
            //TODO: dataHolder is filled with the last row of data!!! FIX data storing problem!!!

            //System.out.println(dataHolder.get(20)[1]);
            extractData(sportType, dataHolder);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private static void extractData(String sportType, ArrayList<String[]> dataHolder)
    {
        System.out.println("Writing "+ sportType + " data");

        workbook = new XSSFWorkbook();

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



        //Declare couple neccessary objects,
        Object[] objArr = null;
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        int cellNum = 0;

        sheet = workbook.createSheet(sportType);

        for(int rows = 0; rows < dataHolder.size(); rows++){

            row = sheet.createRow(rowNum++);

            if(rowNum == 1)
            {
                objArr = new Object[] {"Time", "Home vs Away", "League","Prediction"};
                for (Object obj : objArr)
                {
                    cell = row.createCell(cellNum++);
                    cell.setCellStyle(headers);
                    cell.setCellValue((String)obj);

                }
                objArr = null;
            }else{
                objArr = dataHolder.get(rows);
                //System.out.println(objArr[1]);
                //break;
                cellNum = 0;
                for(Object obj: objArr){
                    cell = row.createCell(cellNum++);
                    cell.setCellStyle(tableStyle);

                    if(obj instanceof String)
                    {
                        cell.setCellValue((String)obj);

                    } else if(obj instanceof Integer)
                    {
                        cell.setCellValue((Integer)obj);
                    }

                }
                cellNum = 0;
            }






        }

    }

}
