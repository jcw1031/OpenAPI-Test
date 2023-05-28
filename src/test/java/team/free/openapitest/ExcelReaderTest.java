package team.free.openapitest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;

class ExcelReaderTest {

    private final ExcelReader excelReader = new ExcelReader();

    @Test
    void readTest() throws Exception {
        Sheet sheet = excelReader.readFile();
        int columnWidth = sheet.getColumnWidth(0);
        for (Row row : sheet) {
            Cell cell = row.getCell(4);
            System.out.println("cell = " + cell);
        }
    }
}