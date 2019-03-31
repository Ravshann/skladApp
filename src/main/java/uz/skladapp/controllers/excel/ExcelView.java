package uz.skladapp.controllers.excel;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import uz.skladapp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExcelView extends AbstractXlsView {


    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");


        //

        List<Optional<User>> users = new ArrayList<>();
        //System.out.println(userRepository.findAll());
//        users.add(userRepository.findById(Long.valueOf(1)));
        User u = new User();
        users.add(Optional.of(u));


        // create excel xls sheet
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Firstname");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("LastName");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Age");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Job Title");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Company");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Address");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("City");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Country");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("Phone Number");
        header.getCell(8).setCellStyle(style);


        int rowCount = 1;

        for (Optional<User> user : users) {
            Row userRow = sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue("fdfs");
            userRow.createCell(1).setCellValue(32.3);
            userRow.createCell(2).setCellValue(10);
            userRow.createCell(3).setCellValue("man");
            userRow.createCell(4).setCellValue("apple");
            userRow.createCell(5).setCellValue("ds");
            userRow.createCell(6).setCellValue("dasdasds");
            userRow.createCell(7).setCellValue("sdada");
            userRow.createCell(8).setCellValue("dsadas");

        }

    }

}