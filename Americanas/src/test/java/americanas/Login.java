package americanas;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Login {
	String url = "https://www.americanas.com.br";
	WebDriver driver; 
	String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()); //variavel utilizada para criar as pastas dos prints
	
	// tirar print da tela - pasta onde serao salvo os prints
		public void Print(String NomePrint) throws IOException {
			File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(foto, new File("C:\\Users\\Priscila\\ftsod125-workspace\\Americanas\\target\\evidencias\\login\\" + nomePasta + "\\" + NomePrint + ".png"));
			
		}
		
	@Given("^que acesso o site das Lojas Americanas$")
	public void que_acesso_o_site_das_Lojas_Americanas() throws Throwable {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Priscila\\ftsod125-workspace\\Americanas\\drivers\\chrome\\80\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url); 
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
		System.out.println("Passo 1 - Acessei o site da Americanas");
	}

	@Given("^clico em Entrar$")
	public void clico_em_Entrar() throws Throwable {
		driver.findElement(By.cssSelector(".usr-grt-text")).click();
		{
			WebElement element = driver.findElement(By.id("h_usr-signin"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.id("h_usr-signin")).click();
		System.out.println("Passo 2 - Cliquei em entrar");
	}

	@Given("^informo o e-mail \"([^\"]*)\"$")
	public void informo_o_e_mail(String email) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.id("email-input")).click();
		driver.findElement(By.id("email-input")).clear();
		driver.findElement(By.id("email-input")).sendKeys(Keys.chord(email));
		System.out.println("Passo 3 - Informei o e-mail");
	}

	@Given("^informo a senha \"([^\"]*)\" e clico em Continuar$")
	public void informo_a_senha_e_clico_em_Continuar(String senha) throws Throwable {
		driver.findElement(By.id("password-input")).click();
		driver.findElement(By.id("password-input")).clear();
		driver.findElement(By.id("password-input")).sendKeys(Keys.chord(senha));
		Print("Passo 4 - Dados informados para o login"); //tira o print nesse passo
		driver.findElement(By.id("login-button")).click();
		System.out.println("Passo 4 - Informei a senha e cliquei em continuar");
		
	}

	@Then("^confirmo o login no site$")
	public void confirmo_o_login_no_site() throws Throwable {
		Thread.sleep(70000);
		assertEquals("olá joao", driver.findElement(By.cssSelector(".usr-grt-text")).getText());
		System.out.println("Passo 5 - Confirmei que o login foi feito");
		Print("Passo 5 - Confirmacao de login feito");
		driver.quit();
	}
}
