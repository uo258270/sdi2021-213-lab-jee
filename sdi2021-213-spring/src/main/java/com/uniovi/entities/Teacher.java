package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher {

	@Id
	@GeneratedValue
	private Long dni;
	private String nombre;
	private String apellidos;
	private String categoria;
	
	public Teacher() {
		super();
		
	}
	
	public Teacher(Long dni, String nombre, String apellidos, String categoria) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoria = categoria;
	}


	/**
	 * @return the dni
	 */
	public Long getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(Long dni) {
		this.dni = dni;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Teacher [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", categoria=" + categoria
				+ "]";
	}
	
	
}
