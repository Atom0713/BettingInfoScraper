package root;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;

public class WriteToExcelSheet {
    // Excel file
    private final static XSSFWorkbook workbook = new XSSFWorkbook();
    // sheet within excel file
    private static XSSFSheet sheet;

    public WriteToExcelSheet(String sportType, ArrayList<String[]> data) {
        writeInfoToExcel(sportType, data);
    }

    public static XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public static XSSFSheet getSheet() {
        return sheet;
    }



    private static void writeInfoToExcel(String sportType, ArrayList<String[]> data){
        /*
         * Style and font attributes of the excel sheet headers and cells.
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


        //Create sheet named after the sport type
        sheet = workbook.createSheet(sportType.replace("#",""));



        /*
        * Print scraped information to excel sheet
         */
        int rowNum = 0;

        Row row = null;
        Cell cell = null;
        int cellNum = 0;

        for (String[] rowEl : data) {

            row = sheet.createRow(rowNum++);
            for (String el : rowEl){

                if (rowNum - 1 == 0){
                    // Fill first row of a sheet with column headers with special styling
                    cell = row.createCell(cellNum++);
                    cell.setCellStyle(headers);
                    cell.setCellValue(el);
                    continue;
                }
                cell = row.createCell(cellNum++);
                cell.setCellStyle(tableStyle);
                cell.setCellValue(el);
            }
            cellNum = 0;
        }
    }

}
