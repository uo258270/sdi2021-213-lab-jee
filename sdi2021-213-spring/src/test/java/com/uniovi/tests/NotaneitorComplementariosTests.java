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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NotaneitorComplementariosTests {
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
		PO_RegisterView.fillFormAddTeacher(driver, "12374569857", "Pepe", "garcia", "cat1");
		
		PO_View.checkElement(driver, "text", "Pepe");
		
		PO_PrivateView.logout(driver,"Identifícate");

	}
	
	@Test
	public void PR02() {
		driver.navigate().to(URL+"/teacher/add");
        PO_LoginView.fillForm(driver, "99999988F", "123456");
       
        PO_RegisterView.fillFormAddTeacher(driver, "123456786666Z", "pEPITO", "gRILLO", "Aslepi");
        PO_RegisterView.checkKey(driver, "Error.teacher.add.dni.length", PO_Properties.getSPANISH());
       
        PO_RegisterView.fillFormAddTeacher(driver, "123", "pEPITO", "gRILLO", "Aslepi");
        PO_RegisterView.checkKey(driver, "Error.teacher.add.dni.length", PO_Properties.getSPANISH());
       
        PO_RegisterView.fillFormAddTeacher(driver, "12345678Z", "Pablo", "Lopez", "Aslopo");
        PO_RegisterView.checkKey(driver, "Error.teacher.add.dni.duplicate", PO_Properties.getSPANISH());
       
       
        PO_RegisterView.fillFormAddTeacher(driver, "123456786", "Paco", "Lopez", "Aslepia");
        PO_RegisterView.checkKey(driver, "Error.teacher.add.dni.letter", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR03() {
		PO_PrivateView.login(driver, "99999990A", "123456", "99999990A");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Agregar Profesor", 2);
		PO_PrivateView.logout(driver, "Identifícate");
		
		PO_PrivateView.login(driver, "99999993D", "123456", "99999993D");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Agregar Profesor", 2);
		PO_PrivateView.logout(driver, "Identifícate");
		
		PO_PrivateView.login(driver, "99999988F", "123456", "99999988F");
		PO_PrivateView.checkElementAndClick(driver, "free", "//li[contains(@id, 'teachers-menu')]/a", 0);
		List<WebElement> elements = PO_View.checkElement(driver, "free", "//a[contains(@href, 'teacher/add')]");
		assertTrue(!elements.isEmpty());
		PO_PrivateView.logout(driver, "Identifícate");
		
	}
} 