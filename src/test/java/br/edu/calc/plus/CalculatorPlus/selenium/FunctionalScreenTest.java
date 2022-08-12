package br.edu.calc.plus.CalculatorPlus.selenium;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class FunctionalScreenTest {

    @LocalServerPort
    private int porta;

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void test() {
        driver.get("http:localhost:" + porta + "/login");
        driver.manage().window().setSize(new Dimension(1352, 616));

        driver.findElement(By.id("username")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("123");

        driver.findElement(By.cssSelector(".login100-form-btn")).click();
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector(".nav-item:nth-child(4) span")).click();
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.id("cpNome")).sendKeys("Tarefa para testar");
        driver.findElement(By.id("cpData")).sendKeys("2021-12-22");
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector(".btn-primary")).click();
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector(".nav-item:nth-child(5) span")).click();

        Assertions.assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).getText())
                .isEqualTo("Tarefa para testar");
    }

    @Test
    void test1() {
        driver.get("http:localhost:"+porta+"/login");
        driver.manage().window().setSize(new Dimension(1352, 616));

        driver.findElement(By.id("username")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("123");
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector(".login100-form-btn")).click();
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector("ul.nav:nth-child(3) > li:nth-child(2) > a:nth-child(1) > span:nth-child(2)")).click();


        driver.findElement(By.id("cpNome")).clear();
        driver.findElement(By.id("cpNome")).sendKeys("Teste de Alteração de Dados");
        try { Thread.sleep (2500); } catch (InterruptedException ex) {}
        driver.findElement(By.id("cpEmail")).clear();
        driver.findElement(By.id("cpEmail")).sendKeys("rennan@teste.com");
        try { Thread.sleep (2500); } catch (InterruptedException ex) {}
        driver.findElement(By.id("cpCpf")).clear();
        driver.findElement(By.id("cpCpf")).sendKeys("123");
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector(".btn-primary")).click();

        Assertions.assertThat(driver.findElement(By.cssSelector(".alert > span:nth-child(2) > strong:nth-child(1)")).getText()).isEqualTo("Dados alterados com sucesso.");
    }

    @Test
    void test2() {
        driver.get("http:localhost:"+porta+"/login");
        driver.manage().window().setSize(new Dimension(1352, 616));

        driver.findElement(By.id("username")).sendKeys("Meu Teste");
        driver.findElement(By.id("password")).sendKeys("123");
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector(".login100-form-btn")).click();
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        Assertions.assertThat(driver.findElement(By.cssSelector(".error")).getText()).isEqualTo("Login ou Senha incorreta");
    }

    @Test
    void test3() {
        driver.get("http:localhost:"+porta+"/login");
        driver.manage().window().setSize(new Dimension(1352, 616));

        driver.findElement(By.id("username")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("123");
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
        driver.findElement(By.cssSelector(".login100-form-btn")).click();

        driver.findElement(By.cssSelector("li.nav-item:nth-child(6) > a:nth-child(1) > span:nth-child(2)")).click();

        Assertions.assertThat(driver.findElement(By.cssSelector(".login100-form-title")).getText()).isEqualTo("LOGIN");
    }
}
