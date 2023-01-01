package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LogExcel  {
   /* public static void main(String[] args) {

        Workbook book = new XSSFWorkbook();
        final String nameFile = "LogDolar.xlsx";
        Sheet sheet = book.createSheet("Hoja 1");
        String[] headers = {"Dia", "Hora", "Valor"};
        int rowIndex = 0;

        Row row = sheet.createRow(rowIndex);
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            Cell cell = row.createCell(i);
            cell.setCellValue(header);
        }
        rowIndex++;
        for (int i = 0; i < datosDolares.size(); i++) {
            row = sheet.createRow(rowIndex);
            DatosDolar datosDolars = datosDolares.get(i);
            row.createCell(0).setCellValue(datosDolars.getDia());
            row.createCell(1).setCellValue(datosDolars.getHora());
            row.createCell(2).setCellValue(datosDolars.getValor());
            rowIndex++;
        }
        Row firstRow = sheet.createRow(0);
        Cell firstCell = firstRow.createCell(0);
        firstCell.setCellValue("Dia");
        File actualDirectory = new File(".");
        String ubication = actualDirectory.getAbsolutePath();
        String outputFileDirectory = ubication.substring(0, ubication.length() - 1) + nameFile;
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(outputFileDirectory);
            book.write(outputStream);
            book.close();
            System.out.println("Libro guardado correctamente");

        } catch (FileNotFoundException ex) {
            System.out.println("Error de filenotfound");
        } catch (IOException ex) {
            System.out.println("Error de IOException");
        }
    }

    */
}