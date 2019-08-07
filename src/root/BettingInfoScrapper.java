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
    private static XSSFSheet sheet;
    private static final String url = "https://hintwise.com/";
    //Declare  blank sheet




    public static void main(String[] args) {
        //sheet = workbook.createSheet("Soccer");
        readInformation("#soccer");
        System.out.println();
        //sheet = workbook.createSheet("Basketball");
        readInformation("#basketball");
        System.out.println();
        //sheet = workbook.createSheet("Tennis");
        readInformation("#tennis");
        System.out.println();
        //sheet = workbook.createSheet("Hockey");
        readInformation("#hockey");

        autisizeColumns(workbook,4,4);

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
        objArr = new Object[] {"Time", "Home vs Away", "League","Prediction"};
        for (Object obj : objArr)
        {
            cell = row.createCell(cellNum++);
            cell.setCellStyle(headers);
            cell.setCellValue((String)obj);

        }
        objArr = null;

        System.out.println("Read and write " + sportType.replace("#","") + " data!");
        String[] elementRow = new String[4] ;



        try{
            /*
            * Extract, store and write scrapped data.
             */
            final Document bettingInfo = Jsoup.connect(url).get();

            for(Element rowElement : bettingInfo.select(sportType +  " table.items tr")){

                if(rowElement.select(".cellStyle.cell40").text().equals("")){
                    //Skips empty row elements at any level of the table.
                }else{
                    final String time = rowElement.select(".cellStyle.cell40").text();
                    final String teams = rowElement.select(".cellTEAMS.cellStyle").text();
                    final String division = rowElement.select(".hidden-xs.cellStyle").text();

                    String prediction = rowElement.select(".cellStyle.lr20.cell90").text();
                    prediction = prediction.replace("Away", "A");
                    prediction = prediction.replace("Draw", "D");
                    prediction = prediction.replace("Home", "H");

                    elementRow[0] = time;
                    elementRow[1] = teams;
                    elementRow[2] = division;
                    elementRow[3] = prediction;

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
                    cellNum = 0;
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
