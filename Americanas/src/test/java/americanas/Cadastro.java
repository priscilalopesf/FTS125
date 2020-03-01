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
import cucumber.api.java.en.When;

public class Cadastro {
	String url;
	WebDriver driver;
	String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()); //variavel utilizada para criar as pastas dos prints

	// tirar print da tela - pasta onde serao salvo os prints
	public void Print(String NomePrint) throws IOException {
		File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(foto,
				new File("C:\\Users\\Priscila\\ftsod125-workspace\\Americanas\\target\\evidencias\\cadastro\\"
						+ nomePasta + "\\" + NomePrint + ".png"));
	}

	@Given("^que acesso o site da loja Americanas$")
	public void que_acesso_o_site_da_loja_Americanas() throws Throwable {
		url = "https://www.americanas.com.br";
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Priscila\\ftsod125-workspace\\Americanas\\drivers\\chrome\\80\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		System.out.println("Passo 1 - Acessei o site da Americanas");
	}

	@When("^Clico em Cadastrar$")
	public void clico_em_Cadastrar() throws Throwable {
		driver.findElement(By.cssSelector(".usr-grt-text")).click();
		{
			WebElement element = driver.findElement(By.cssSelector("a.usr-signup"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector("a.usr-signup")).click();
		System.out.println("Passo 2 - Cliquei em cadastrar");
	}

	@When("^preencho o e-mail \"([^\"]*)\"$")
	public void preencho_o_e_mail(String email) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.id("email-input")).click();
		driver.findElement(By.id("email-input")).clear();
		driver.findElement(By.id("email-input")).sendKeys(Keys.chord(email));

		System.out.println("Passo 3 - Preenchi o e-mail");
	}

	@When("^preencho a senha \"([^\"]*)\"$")
	public void preencho_a_senha(String senha) throws Throwable {
		driver.findElement(By.id("password-input")).click();
		driver.findElement(By.id("password-input")).clear();
		driver.findElement(By.id("password-input")).sendKeys(Keys.chord(senha));

		System.out.println("Passo 4 - Preenchi a senha");
	}

	@When("^preencho o CPF \"([^\"]*)\"$")
	public void preencho_o_CPF(String cpf) throws Throwable {
		driver.findElement(By.id("cpf-input")).click();
		driver.findElement(By.id("cpf-input")).clear();
		driver.findElement(By.id("cpf-input")).sendKeys(Keys.chord(cpf));

		System.out.println("Passo 5 - Preenchi o CPF");
	}

	@When("^preencho o nome e sobrenome \"([^\"]*)\"$")
	public void preencho_o_nome_e_sobrenome(String nome) throws Throwable {
		driver.findElement(By.id("name-input")).click();
		driver.findElement(By.id("name-input")).clear();
		driver.findElement(By.id("name-input")).sendKeys(Keys.chord(nome));

		System.out.println("Passo 4 - Preenchi o nome");
	}

	@When("^preencho a data de nascimento \"([^\"]*)\"$")
	public void preencho_a_data_de_nascimento(String nascimento) throws Throwable {
		driver.findElement(By.id("birthday-input")).click();
		driver.findElement(By.id("birthday-input")).clear();
		driver.findElement(By.id("birthday-input")).sendKeys(Keys.chord(nascimento));
		Print("Passo 5 - Dados preenchidos 1");
		System.out.println("Passo 5 - Preenchi o aniversario");

	}

	@When("^seleciono o sexo$")
	public void seleciono_o_sexo() throws Throwable {
		driver.findElement(By.cssSelector(".radio:nth-child(2) > label")).click();

		System.out.println("Passo 6 - Selecionei o sexo");
	}

	@When("^preencho o telefone \"([^\"]*)\"$")
	public void preencho_o_telefone(String telefone) throws Throwable {
		driver.findElement(By.id("phone-input")).click();
		driver.findElement(By.id("phone-input")).clear();
		driver.findElement(By.id("phone-input")).sendKeys(Keys.chord(telefone));
		Print("Passo 7 - Dados preenchidos 2");
		System.out.println("Passo 7 - Preenchi o telefone");
	}

	@When("^clico no botao criar seu cadastro$")
	public void clico_no_bot_o_criar_seu_cadastro() throws Throwable {
		driver.findElement(By.cssSelector(".btn")).click();
		
		System.out.println("Passo 8 - Cliquei no botao para confirmar o cadastro");

	}

	@Then("^confirmo que o cadastro foi feito$")
	public void confirmo_que_o_cadastro_foi_feito() throws Throwable {
		Thread.sleep(70000);
		assertEquals("olá Joao", driver.findElement(By.cssSelector(".usr-grt-text")).getText());
		Print("Passo 9 - Confirmei que o cadastro foi feito");
		System.out.println("Passo 9 - Confirmei que o cadastro foi feito");
		driver.quit();
	}

}
