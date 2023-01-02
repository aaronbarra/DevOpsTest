package pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseClass;
import utils.PropertiesDriven;

import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class HomePage extends BaseClass {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    //Locator

    By locatorValorDolar = By.xpath("//span[@class='text-2xl']");
    By locatorDiaDolar = By.xpath("//time[1][@class]");
    By locatorTituloPeso = By.xpath("//h1[contains(text(),'chileno')]");
    By locatorEstadoMercado= By.xpath("//span[2][contains(text(),'real')]");



    //Method

    public String valorDolar() {
        WebElement precioDolar = driver.findElement(locatorValorDolar);
        String valorDolar = precioDolar.getText();
        return valorDolar;
    }

    public String diaDolar() {
        WebElement diaDolar = driver.findElement(locatorDiaDolar);
        String dia = diaDolar.getText();
        return dia;
    }

    public String getHora() {
        String datetime = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm").format(LocalDateTime.now());
        return datetime;
    }
    public String estadoMercado() {
        try{
            WebElement estadoMercado= driver.findElement(locatorEstadoMercado);
            String estado= estadoMercado.getText();
            return estado;
        } catch(Exception e){
            String estado = "Mercado cerrado";
            return estado;
        }
    }

    public String resultadoValorDolar() {
        String resultado = "Valor dolar = $" + valorDolar() + " pesos Chilenos.\nUltima Actualizacion: " + diaDolar() + ", " + getHora();
        System.out.println(resultado);


        return resultado;
    }

    public class DatosDolar{

        private String dia;
        private String estadoMercado;
        private String hora;
        private String valor;

        public DatosDolar(String dia,String estadoMercado, String hora, String valor ) {
            this.dia = dia;
            this.estadoMercado = estadoMercado;
            this.hora = hora;
            this.valor = valor;

        }

        public String getDia() {
            return dia;
        }

        public void setDia(String dia) {
            this.dia = dia;
        }

        public String getEstadoMercado() {
            return estadoMercado;
        }

        public void setEstadoMercado(String estadoMercado) {
            this.estadoMercado = estadoMercado;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }


    }

    public void createLogs(String dia, String estadoMercado, String hora, String valor) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(PropertiesDriven.getProperty("rutaExcel"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sheets = excel.getNumberOfSheets();

        XSSFSheet sheet = null;
        for (int i = 0; i < sheets; i++) {
            //verificaremos la hoja a trabajar en base al nombre de esta.
            if (excel.getSheetName(i).equalsIgnoreCase(PropertiesDriven.getProperty("sheet"))) {
                sheet = excel.getSheetAt(i);
                int nrows = sheet.getLastRowNum();
                ArrayList<DatosDolar> datos = new ArrayList<>();
                datos.add(new DatosDolar(dia, estadoMercado, hora, valor));
                int rowIndex = nrows + 1;
                Row row = sheet.createRow(rowIndex);
                for (int v = 0; v < datos.size(); v++) {
                    row = sheet.createRow(rowIndex);
                    DatosDolar datosDolar = datos.get(v);
                    row.createCell(0).setCellValue(datosDolar.getDia());
                    row.createCell(1).setCellValue(datosDolar.getEstadoMercado());
                    row.createCell(2).setCellValue(datosDolar.getHora());
                    row.createCell(3).setCellValue(datosDolar.getValor());
                    rowIndex++;
                }
            }
        }
        String nameFile = "LogDolar.xlsx";

        File actualDirectory = new File(".");
        String ubication = actualDirectory.getAbsolutePath();
        String outputFileDirectory = ubication.substring(0, ubication.length() - 1) + nameFile;
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(outputFileDirectory);
            excel.write(outputStream);
            excel.close();
            System.out.println("Book save success");

        } catch (FileNotFoundException ex) {
            System.out.println("Error de filenotfound");
        } catch (IOException ex) {
            System.out.println("Error de IOException");
        }
        System.out.println();

    }

    public void locatorTitulo(){
        waitXSec(4000);
        ScrollElementWeb(searchElementWeb(locatorTituloPeso));
    }


}
