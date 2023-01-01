package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.PropertiesDriven;

public class TestCases {
    private HomePage homePage;
    private final String browser = PropertiesDriven.getProperty("browser"); //Este valor eventualmente se vera modificado
    private final String propertyDriver = PropertiesDriven.getProperty("propertyDriver");
    private final String urlDriver = PropertiesDriven.getProperty("urlDriver");
    private final String url = PropertiesDriven.getProperty("url");
    private WebDriver driver;


    @BeforeMethod
    public void preparacionTest(){
        homePage = new HomePage(driver);
        homePage.conexionBrowser(browser,propertyDriver,urlDriver);
        homePage.goToPage(url);
        homePage.maxBrowser();

    }
    @Test
    public void CP001_ObtenerValorDolar_DiaHora(){
        homePage.locatorTitulo();
        String dia= homePage.diaDolar();
        String hora = homePage.getHora();
        String valorDolar= homePage.valorDolar();
        String estadoMercado = homePage.estadoMercado();

        homePage.resultadoValorDolar();
        homePage.createLogs(dia,estadoMercado,hora,valorDolar);
    }




}