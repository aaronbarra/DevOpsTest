package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
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
        Assert.assertTrue(!valorDolar.isEmpty());
        homePage.closeBrowser();
    }
}