package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_RegisterView extends PO_NavView {
	static public void fillForm(WebDriver driver, String dnip, String namep, String lastnamep, String passwordp,
			String passwordconfp) {
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("name"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastname = driver.findElement(By.name("lastName"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		WebElement passwordConfirm = driver.findElement(By.name("passwordConfirm"));
		passwordConfirm.click();
		passwordConfirm.clear();
		passwordConfirm.sendKeys(passwordconfp);
//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	
	static public void fillFormAddTeacher(WebDriver driver, String dnit, String nombret, String apellidost, String categoriat) {
		// Rellenemos formulario
		WebElement dni = driver.findElement(By.name("dni"));
		dni.clear();
		dni.sendKeys(dnit);
		WebElement nombre = driver.findElement(By.name("nombre"));
		nombre.clear();
		nombre.sendKeys(nombret);
		WebElement apellidos = driver.findElement(By.name("apellidos"));
		apellidos.clear();
		apellidos.sendKeys(apellidost);
		WebElement categoria = driver.findElement(By.name("categoria"));
		categoria.clear();
		categoria.sendKeys(categoriat);

		// Enviamos el formulario
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	
}
