package team.free.openapitest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public Sheet readFile() throws IOException {
        FileInputStream file = new FileInputStream("/Users/jcw/only2.xlsx");
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);
        System.out.println("sheet = " + sheet);
        return sheet;
    }
}
