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

	public String conocerNombre(){
		return this.nombre;
	}


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Persona)) {
			return false;
		}
		Persona persona = (Persona) o;
		return Persona.equals(nombre, persona.nombre) && Persona.equals(dni, persona.dni) && Objects.equals(edad, persona.edad) && Objects.equals(info, persona.info);
	}
}
