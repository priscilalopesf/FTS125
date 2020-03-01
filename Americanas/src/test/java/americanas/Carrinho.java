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
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class Carrinho {
	String url;
	WebDriver driver;
	String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()); //variavel utilizada para criar as pastas dos prints

	// tirar print da tela - pasta onde serao salvo os prints
			public void Print(String NomePrint) throws IOException {
				File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(foto, new File("C:\\Users\\Priscila\\ftsod125-workspace\\Americanas\\target\\evidencias\\carrinho\\" + nomePasta + "\\" + NomePrint + ".png"));
	}
			
	@Before
	public void iniciar() {
		url = "https://www.americanas.com.br";
		//Chrome Driver
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Priscila\\ftsod125-workspace\\Americanas\\drivers\\chrome\\80\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void finalizar() {
		driver.quit(); 
	}

	@Dado("^que acesso o site da Americanas$")
	public void que_acesso_o_site_da_Americanas() throws Throwable {
		driver.get(url); // Abre o site 
		System.out.println("Passo 1 - Acessei o site da Americanas"); //Comando para escrever no console
	}

	@Quando("^digito o termo \"([^\"]*)\" e pressiono Enter$")
	public void digito_o_termo_e_pressiono_Enter(String termo) throws Throwable {
		//Clica, limpa e escreve soletrando no campo de pesquisa e pressiona Enter
		driver.findElement(By.id("h_search-input")).click();
		driver.findElement(By.id("h_search-input")).clear();
		driver.findElement(By.id("h_search-input")).sendKeys(Keys.chord(termo));
		Print("Passo 2 - Termo pesquisado"); //tira o print nesse passo
		driver.findElement(By.id("h_search-input")).sendKeys(Keys.ENTER);
		
		System.out.println("Passo 2 - Digitei o termo " + termo + " e pressionei Enter"); //Comando para escrever no console
	}

	@Quando("^visualizo a lista de produtos e clico no box$")
	public void visualizo_a_lista_de_produtos_e_clico_no_box() throws Throwable {
		Thread.sleep(3000);
		assertEquals("Livro Harry Potter em Promoção nas Lojas Americanas.com", driver.getTitle());
		Print("Passo 3 - Lista de produtos");
		driver.findElement(By.cssSelector(".product-grid-item:nth-child(1) .ImageUI-sc-9rtsvr-0:nth-child(2)")).click(); // clico no produto
		
		System.out.println("Passo 3 - Visualizei a lista de produtos e cliquei no box de livros"); //Comando para escrever no console
	}

	@Quando("^visualizo os dados do produto e clico em comprar$")
	public void visualizo_os_dados_do_produto_e_clico_em_comprar() throws Throwable {
		Thread.sleep(1000);
		Print("Passo 4 - Dados do produto");
		driver.findElement(By.cssSelector(".kmswup")).click(); // cliquei no botão comprar
		
		System.out.println("Passo 4 - Visualizei os dados do produto e cliquei em comprar"); //Comando para escrever no console
	}

	@Entao("^adiciono o item no carrinho$")
	public void adiciono_o_item_no_carrinho() throws Throwable {
		Thread.sleep(1000);
		assertEquals("minha cesta", driver.findElement(By.cssSelector("h2.page-title.col-xs-7")).getText()); // confirmo que está no carrinho
		// confirmo que o item no carrinho é o box clicado anteriormente
		assertEquals("Coleção Harry Potter - 1ª Ed.", driver.findElement(By.cssSelector("a.link-default.clearfix")).getText()); 
		Print("Passo 5 - Carrinho após inclusão do item");		
		System.out.println("Passo 5 - Adicionei o produto no carrinho");

	}

}