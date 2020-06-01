/* *****************************************************************************
 *  Name:              Artem Slyusarenko
 *  Last modified:     06/08/2019
 **************************************************************************** */

package root;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;


public class Main {

    public static void main(String[] args) {
        ReadHtml readhtml = new ReadHtml(new String[]{"#soccer", "#basketball", "#hockey", "#tennis"});

        XSSFWorkbook workbook = WriteToExcelSheet.getWorkbook();

        autoSizeColumns(workbook,1,8);

        try
        {
            //Write workbook into file system
            FileOutputStream out = new FileOutputStream(new File("logs/BettingInformation.xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (Exception e) { e.printStackTrace(); }

        // this is bullshit

    }



    public static void autoSizeColumns(Workbook workbook, int sheetNum, int colNum)
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
