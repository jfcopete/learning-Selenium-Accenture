import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
public class MercadoLibre {
    public static WebDriver driver;
    public static void main(String[] args) {
        System.out.println("Escribre el producto que quieres buscar: ");
        String product="";
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
             product = reader.readLine();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(product);
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        System.out.println("Web driver inicializado");

        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.co");
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);

        WebElement search;
        search= driver.findElement(By.xpath("//input[@name=\"q\"]"));
        search.sendKeys("MercadoLibre Colombia\n");

        WebElement ml;
        ml=driver.findElement(By.xpath("//a[@href=\"https://www.mercadolibre.com.co/\"]"));
        ml.click();

        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement mlpage;
        mlpage=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"as_word\"]")));
        mlpage.sendKeys(product+"\n");

        WebElement getProduct;
        getProduct=driver.findElement(By.xpath("//h1[@class=\"ui-search-breadcrumb__title\"]"));

        if (getProduct.getText().equalsIgnoreCase(product))
        {
            System.out.println("Producto buscado");
        }else
        {
            System.out.println("Producto no encontrado");
        }
    }
}
