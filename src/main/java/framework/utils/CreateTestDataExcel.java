package framework.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class CreateTestDataExcel {
    public static void main(String[] args) throws Exception {
        Workbook workbook = new XSSFWorkbook();

        // Sheet: SmokeCases
        Sheet smokeSheet = workbook.createSheet("SmokeCases");
        smokeSheet.createRow(0).createCell(0).setCellValue("username");
        smokeSheet.getRow(0).createCell(1).setCellValue("password");
        smokeSheet.getRow(0).createCell(2).setCellValue("expected_url");
        smokeSheet.getRow(0).createCell(3).setCellValue("description");

        smokeSheet.createRow(1).createCell(0).setCellValue("standard_user");
        smokeSheet.getRow(1).createCell(1).setCellValue("secret_sauce");
        smokeSheet.getRow(1).createCell(2).setCellValue("inventory");
        smokeSheet.getRow(1).createCell(3).setCellValue("Login voi standard user");

        smokeSheet.createRow(2).createCell(0).setCellValue("problem_user");
        smokeSheet.getRow(2).createCell(1).setCellValue("secret_sauce");
        smokeSheet.getRow(2).createCell(2).setCellValue("inventory");
        smokeSheet.getRow(2).createCell(3).setCellValue("Login voi problem user");

        smokeSheet.createRow(3).createCell(0).setCellValue("performance_glitch_user");
        smokeSheet.getRow(3).createCell(1).setCellValue("secret_sauce");
        smokeSheet.getRow(3).createCell(2).setCellValue("inventory");
        smokeSheet.getRow(3).createCell(3).setCellValue("Login voi glitch user");

        // Sheet: NegativeCases
        Sheet negativeSheet = workbook.createSheet("NegativeCases");
        negativeSheet.createRow(0).createCell(0).setCellValue("username");
        negativeSheet.getRow(0).createCell(1).setCellValue("password");
        negativeSheet.getRow(0).createCell(2).setCellValue("expected_error");
        negativeSheet.getRow(0).createCell(3).setCellValue("description");

        negativeSheet.createRow(1).createCell(0).setCellValue("standard_user");
        negativeSheet.getRow(1).createCell(1).setCellValue("wrong_password");
        negativeSheet.getRow(1).createCell(2).setCellValue("Username and password do not match");
        negativeSheet.getRow(1).createCell(3).setCellValue("Wrong password");

        negativeSheet.createRow(2).createCell(0).setCellValue("locked_out_user");
        negativeSheet.getRow(2).createCell(1).setCellValue("secret_sauce");
        negativeSheet.getRow(2).createCell(2).setCellValue("locked out");
        negativeSheet.getRow(2).createCell(3).setCellValue("Locked user");

        negativeSheet.createRow(3).createCell(0).setCellValue("");
        negativeSheet.getRow(3).createCell(1).setCellValue("secret_sauce");
        negativeSheet.getRow(3).createCell(2).setCellValue("Username is required");
        negativeSheet.getRow(3).createCell(3).setCellValue("Empty username");

        negativeSheet.createRow(4).createCell(0).setCellValue("standard_user");
        negativeSheet.getRow(4).createCell(1).setCellValue("");
        negativeSheet.getRow(4).createCell(2).setCellValue("Password is required");
        negativeSheet.getRow(4).createCell(3).setCellValue("Empty password");

        negativeSheet.createRow(5).createCell(0).setCellValue("");
        negativeSheet.getRow(5).createCell(1).setCellValue("");
        negativeSheet.getRow(5).createCell(2).setCellValue("Username is required");
        negativeSheet.getRow(5).createCell(3).setCellValue("Empty both fields");

        // Sheet: BoundaryCases
        Sheet boundarySheet = workbook.createSheet("BoundaryCases");
        boundarySheet.createRow(0).createCell(0).setCellValue("username");
        boundarySheet.getRow(0).createCell(1).setCellValue("password");
        boundarySheet.getRow(0).createCell(2).setCellValue("expected_error");
        boundarySheet.getRow(0).createCell(3).setCellValue("description");

        boundarySheet.createRow(1).createCell(0).setCellValue("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        boundarySheet.getRow(1).createCell(1).setCellValue("secret_sauce");
        boundarySheet.getRow(1).createCell(2).setCellValue("Username and password do not match");
        boundarySheet.getRow(1).createCell(3).setCellValue("Long username");

        boundarySheet.createRow(2).createCell(0).setCellValue("admin' OR 1=1 --");
        boundarySheet.getRow(2).createCell(1).setCellValue("password");
        boundarySheet.getRow(2).createCell(2).setCellValue("Username and password do not match");
        boundarySheet.getRow(2).createCell(3).setCellValue("SQL injection simple");

        boundarySheet.createRow(3).createCell(0).setCellValue("!@#$%^&*");
        boundarySheet.getRow(3).createCell(1).setCellValue("secret_sauce");
        boundarySheet.getRow(3).createCell(2).setCellValue("Username and password do not match");
        boundarySheet.getRow(3).createCell(3).setCellValue("Special chars username");

        boundarySheet.createRow(4).createCell(0).setCellValue("standard_user");
        boundarySheet.getRow(4).createCell(1).setCellValue("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        boundarySheet.getRow(4).createCell(2).setCellValue("Username and password do not match");
        boundarySheet.getRow(4).createCell(3).setCellValue("Long password");

        // Write to file
        String path = "src/test/resources/testdata/login_data.xlsx";
        new java.io.File("src/test/resources/testdata").mkdirs();
        try (FileOutputStream fos = new FileOutputStream(path)) {
            workbook.write(fos);
            System.out.println("Excel file created: " + path);
        }
        workbook.close();
    }
}
