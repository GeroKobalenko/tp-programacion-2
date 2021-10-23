package tpProgra2;

public class Persona {
	private String nombre;
	private Integer dni;
	private Integer edad;
	private StringBuilder info= new StringBuilder("El nombre es: ").append(nombre)
			.append(", DNI: ").append(dni).append(", Edad: ").append(edad);
	
	Persona(String nombre, int dni, int edad){
		this.nombre=nombre;
		this.dni=dni;
		this.edad= edad;
	}
	
	public String darInfo() {
		return info.toString();
	}
	
	public int conocerEdad() {
		return this.edad;
	}
	
	public int conocerDNI() {
		return this.dni;
	}
}
