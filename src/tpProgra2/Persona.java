package tpProgra2;

import javax.management.RuntimeErrorException;

public class Persona {
	
	private String nombre;
	private Integer dni;
	private Integer edad;
	
	Persona(String nombre, int dni, int edad){
		
		//Valido los parametros
		if (nombre == null || edad < 16 || dni <= 0) throw new RuntimeErrorException(null,"Algun parametro proporcionado es incorrecto.");
		
		this.nombre=nombre;
		this.dni=dni;
		this.edad= edad;
		
	}
	
	public String darInfo() {
		return new StringBuilder("El nombre es: ").append(nombre).append(", DNI: ").append(dni).append(", Edad: ").append(edad).toString();
	}
	
	public int conocerEdad() {
		return this.edad;
	}
	
	public boolean esMayorEdad() {
		return this.edad>64;
	}
	
	public int conocerDNI() {
		return this.dni;
	}

	public String conocerNombre(){
		return this.nombre;
	}
}
