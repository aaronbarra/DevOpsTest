package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected WebDriverWait wait;
    protected Select select;
    public WebDriver getDriver() {
        return driver;
    }
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement searchElementWeb(By localizador){
        return this.driver.findElement(localizador);
    }

    public void goToPage(String url){
        this.driver.get(url);
    }

    //Driver Browser
    public WebDriver conexionBrowser(String browser,String propertyDriver,String rutaDriver){
        switch (browser){
            case "CHROME":
                System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
                this.driver = new ChromeDriver(new ChromeDriverService.Builder().usingPort(65535).build());
                return this.driver;
            case "EDGE":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver =  new EdgeDriver();
                return this.driver;
            case "FIREFOX":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver =  new FirefoxDriver();
                return this.driver;
            default:
                this.driver = null;
                return this.driver;
        }
    }

    //Scroll
    public void ScrollElementWeb(By locator){
        js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].scrollIntoView();", this.driver.findElement(locator));
    }

    public void ScrollElementWeb(WebElement element){
        js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitXSec(int mili){
        try{
            Thread.sleep(mili);
        }catch (Exception ex){
            System.out.println("Fallo la espera en milisegundos definida.");
        }
    }

    //Espera en base a evento
    //public WebElement waitForElementWeb(By locator){
    //    wait = new WebDriverWait(this.driver);
    //    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    //}

    //click
    public void click(WebElement element){
        element.click();
    }

    public void click(By locator){
        this.driver.findElement(locator).click();
    }

    //obtenerTexto
    public String getText(WebElement element){
        return element.getText();
    }

    public String getText(By locator){
        return this.driver.findElement(locator).getText();
    }

    //Submit
    public void submitForm(By locator){
        this.driver.findElement(locator).submit();
    }

    //agregarTexto
    public void addText(WebElement element, String text){
        element.sendKeys(text);
    }

    public void addText(By locator,String text){
        this.driver.findElement(locator).sendKeys(text);
    }

    //Maximizar la pagina
    public void maxBrowser(){
        driver.manage().window().maximize();
    }

    public void selectDDlText(WebElement element,String text){ //Febrero
        select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void closeBrowser(){
        this.driver.quit();
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,800)");
    }

}