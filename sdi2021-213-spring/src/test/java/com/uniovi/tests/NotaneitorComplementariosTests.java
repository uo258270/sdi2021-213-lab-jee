package com.uniovi.tests;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.repositories.TeachersRepository;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NotaneitorComplementariosTests {
	
	
	@Autowired
	private TeachersRepository teachersRepository;
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Nuria San Emeterio\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Test
	public void PR01() {
		
		PO_PrivateView.login(driver, "99999988F", "123456", "Bienvenidos a la pagina principal");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		PO_PrivateView.checkElementAndClick(driver, "free", "//a[contains(@href, 'teacher/add')]", 0);
		
		//PO_LoginView.fillForm(driver, "99999988F", "123456");
		PO_RegisterView.fillFormAddTeacher(driver, "99999999R", "Pepito", "garciaa", "categoria");
		
		
		PO_View.checkElement(driver, "text", "Pepito");
		
		PO_PrivateView.logout(driver,"DNI");

	}
	
	@Test
	public void PR02() {
		PO_PrivateView.login(driver, "99999988F", "123456", "Bienvenidos a la pagina principal");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		PO_PrivateView.checkElementAndClick(driver, "free", "//a[contains(@href, 'teacher/add')]", 0);
		
		
       
        PO_RegisterView.fillFormAddTeacher(driver, "99999999999Z", "Profesor1", "Apellido1", "Informatica");
        //PO_RegisterView.checkKey(driver, "Error.teacher.add.dni.length", PO_Properties.getSPANISH());
        PO_View.checkElement(driver, "text", "El DNI debe tener 9 caracteres (8 dígitos y 1 letra).");
        
        PO_RegisterView.fillFormAddTeacher(driver, "99999", "Profesor2", "Apellido2", "Matematicas");
        //PO_RegisterView.checkKey(driver, "Error.teacher.add.dni.length", PO_Properties.getSPANISH());
        PO_View.checkElement(driver, "text", "El DNI debe tener 9 caracteres (8 dígitos y 1 letra).");
       
        
        PO_RegisterView.fillFormAddTeacher(driver, "99999999R", "Pepito", "garciaa", "categoria");
        //PO_RegisterView.checkKey(driver, "Error.teacher.add.dni.duplicate", PO_Properties.getSPANISH());
        PO_View.checkElement(driver, "text", "Ese DNI ya existe.");
        
       
        PO_PrivateView.logout(driver, "DNI");
        
       }
	
	@Test
	public void PR03() {
		PO_PrivateView.login(driver, "99999990A", "123456", "Bienvenidos a la pagina principal");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Agregar Profesor", 2);
		PO_PrivateView.logout(driver, "DNI");
		
		PO_PrivateView.login(driver, "99999993D", "123456", "Bienvenidos a la pagina principal");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Agregar Profesor", 2);
		PO_PrivateView.logout(driver, "DNI");
		
		PO_PrivateView.login(driver, "99999988F", "123456", "Bienvenidos a la pagina principal");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		List<WebElement> elements = PO_View.checkElement(driver, "free", "//a[contains(@href, 'teacher/add')]");
		assertTrue(!elements.isEmpty());
		PO_PrivateView.logout(driver, "DNI");
		
	}
	
	@Test
	public void PR04() {
		PO_PrivateView.login(driver, "99999988F", "123456", "Bienvenidos a la pagina principal");
		driver.navigate().to(URL+ "/teacher/list");
		PO_View.checkElement(driver, "text", "Profesores");
		
		//lista de la primera pagina
		List<WebElement> elements = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elements.size() == 3);
		
		
		 List<WebElement> paginas = PO_View.checkElement(driver, "free",
		 "//a[contains(@class, 'page-link')]"); paginas.get(2).click(); //ultima pagina
		  
		 elements = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
		 PO_View.getTimeout()); assertTrue(elements.size() == 1);
		 
	}
	
	
} 