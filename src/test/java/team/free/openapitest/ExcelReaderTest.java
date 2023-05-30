package team.free.openapitest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import team.free.openapitest.util.ExcelReader;

class ExcelReaderTest {

    private final ExcelReader excelReader = new ExcelReader();

    @Test
    void readTest() throws Exception {
        Sheet sheet = excelReader.readSheet("/Users/jcw/only2.xlsx");
        int columnWidth = sheet.getColumnWidth(0);
        for (Row row : sheet) {
            Cell cell = row.getCell(4);
            System.out.println("cell = " + cell);
            String cellString = row.getCell(4).toString();
            System.out.println("cellString = " + cellString);
        }
    }
}