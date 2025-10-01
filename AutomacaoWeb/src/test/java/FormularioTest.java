import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormularioTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void testeCincoUsuarios() throws InterruptedException {
        // Lista de usuários (nome, sobrenome)
        String[][] usuarios = {
                {"Joao", "Silva"},
                {"Maria", "Oliveira"},
                {"Pedro", "Santos"},
                {"Ana", "Costa"},
                {"Lucas", "Pereira"}
        };

        for (String[] usuario : usuarios) {
            driver.get("C:\\Users\\gcaio\\AutomacaoWeb\\AutomacaoWeb\\src\\main\\java\\org\\example\\index.html");


            WebElement campoNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname")));
            campoNome.clear();
            campoNome.sendKeys(usuario[0]);

            WebElement campoSobrenome = driver.findElement(By.id("lname"));
            campoSobrenome.clear();
            campoSobrenome.sendKeys(usuario[1]);

            // Clicando no botão submit
            WebElement botaoSubmit = driver.findElement(By.cssSelector("input[type='submit']"));
            botaoSubmit.click();

            // Espera até que a URL contenha "action_page.php"
            wait.until(ExpectedConditions.urlContains("action_page.php"));

            // Verificação
            String urlAtual = driver.getCurrentUrl();
            assertTrue(urlAtual.contains("action_page.php"), "O formulário não foi enviado corretamente.");

            // Pausa visual de 2 segundos antes do próximo usuário
            Thread.sleep(2000);
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
