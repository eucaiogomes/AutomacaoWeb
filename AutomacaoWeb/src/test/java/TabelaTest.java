import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabelaTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configura o ChromeDriver automaticamente
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void validarValorDueDeFrank() {
        // 2. Navegar para a página
        driver.get("https://the-internet.herokuapp.com/tables");

        // 3. Localizar a célula da tabela com o nome "Frank"
        WebElement frankCell = driver.findElement(By.xpath("//table[@id='table1']//td[contains(text(), 'Frank')]"));

        // 3b. Obter a linha inteira (pai do td)
        WebElement frankRow = frankCell.findElement(By.xpath("./parent::tr"));

        // 4. Localizar a célula "Due" na linha de Frank (4ª coluna)
        WebElement dueCell = frankRow.findElement(By.xpath("./td[4]"));

        // 4b. Obter o texto
        String dueValue = dueCell.getText();

        // 4c. Validar
        assertEquals("$51.00", dueValue);

        // 5. Imprimir mensagem
        System.out.println("Teste concluído: Valor 'Due' de Frank é " + dueValue);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
