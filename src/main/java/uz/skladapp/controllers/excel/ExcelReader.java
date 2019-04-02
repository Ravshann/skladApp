package uz.skladapp.controllers.excel;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import uz.skladapp.model.Client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    List<Client> clients = new ArrayList<>();
    public static final String SAMPLE_XLSX_FILE_PATH = "src/excel_files/test.xlsx";

    public void main(String[] args) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");



        //Java 8 forEach wih lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop and generating Client model\n");

        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                Client client = new Client();
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        client.setClient_name(dataFormatter.formatCellValue(cell));
                    } else if (cell.getColumnIndex() == 1) {
                        client.setRegion(dataFormatter.formatCellValue(cell));
                    }
                }
                clients.add(client);
            }


        }
        System.out.println(clients.size());
    for (Client client : clients) {
            System.out.println(client.getClient_name());

    }

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                printCellValue(cell);
            });
            System.out.println();
        });

        // Closing the workbook
        workbook.close();

    }

    public List<Client> listofClients(){
        String [] a = null;
        try {
            main(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  clients;
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }
}