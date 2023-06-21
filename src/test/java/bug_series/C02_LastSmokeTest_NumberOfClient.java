package fruveg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class C02_LastSmokeTest_NumberOfClient {

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }


    @Test
    public void test01() {

        // Entering site
        driver.get("https://my.frachtpilot.de/login");

        // enter the username and password

        driver.findElement(By.xpath("(//input)[1]")).sendKeys("info@fruveg.de",
                Keys.TAB, "Ea1453571!", Keys.ENTER);

        //Choise "Kundemzatz"
        driver.findElement(By.xpath("(//a[@class='nav-link'])[15]")).click();

        //Click "versandatum"
        driver.findElement(By.xpath("(//button)[5]")).click();

        // Enter a date
        WebElement date = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));

        date.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        date.sendKeys("19.06.2023", Keys.TAB, "19.06.2023", Keys.ENTER);

        //click to anwenden
        driver.findElement(By.xpath("(//a[text()='Anwenden'])[2]")).click();

        //Select button

        WebElement zeige = driver.findElement(By.xpath("(//select)[1]"));
        Select select = new Select(zeige);
        select.selectByIndex(3);

        // Take a daily client number
        String numberOfClientsText = driver.findElement(By.xpath("(//span)[45]")).getText();

        System.out.println("numberOfClientsText = " + numberOfClientsText);

        String [] number = numberOfClientsText.split(" ");
        System.out.println("number = " + Arrays.toString(number));

        String numberOfClients = number[5];
        System.out.println("numberOfClients = " + numberOfClients);

        int numberOfClient = Integer.valueOf(numberOfClients);
        System.out.println("numberOfClient = " + numberOfClient);

        // Verify number of clients
        ////tr[@class="item"]

        List<WebElement> allOfRow = driver.findElements(By.xpath("//tr[@class='item']"));
        System.out.println("allOfRow Size " + allOfRow.size());
        Assert.assertEquals(numberOfClient, allOfRow.size());


    }

    @After
    public void tearDown() throws Exception {
       driver.quit();
    }
}
