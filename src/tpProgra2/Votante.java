package tpProgra2;

public class Votante extends Persona {
	
	private boolean enfPrevia;
	private boolean trabaja;
	private boolean tieneCertificado;
	private boolean esPresDeMesa;
	private boolean yaVoto;
	private String turno;
	
	

	Votante(String nombre, int dni, int edad, boolean enfPrevia, boolean trabaja) {
		super(nombre, dni, edad);
		this.enfPrevia=enfPrevia;
		this.trabaja=trabaja;
	}
	
	public void votar(){
		
	}
	
	public void agregarCertificado(boolean certi) {
		this.tieneCertificado=certi;
	}
	
	public boolean tieneCertificado() {
		return this.tieneCertificado;
	}
	
	public boolean esPresDeMesa() {
		return this.esPresDeMesa;
	}
	
	public void asignarTurno() {
		
	}
	
	public String consultarTurno() {
		return this.turno;
	}
	
}