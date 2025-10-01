import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login"); // Site de teste do Selenium
    }

    @Test
    public void testeLogin() throws InterruptedException {
        // Pausa inicial para visualizar o carregamento
        Thread.sleep(1000);

        WebElement campoUsuario = driver.findElement(By.id("username"));
        campoUsuario.sendKeys("tomsmith");
        Thread.sleep(1000); // pausa para visualizar digitação

        WebElement campoSenha = driver.findElement(By.id("password"));
        campoSenha.sendKeys("SuperSecretPassword!");
        Thread.sleep(1000); // pausa para visualizar digitação

        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        botaoLogin.click();

        // Pausa para visualizar a transição
        Thread.sleep(2000);

        // Verificação de sucesso
        WebElement mensagemSucesso = driver.findElement(By.id("flash"));
        assertTrue(mensagemSucesso.getText().contains("You logged into a secure area!"));

        // Pausa final para visualizar a página logada
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
