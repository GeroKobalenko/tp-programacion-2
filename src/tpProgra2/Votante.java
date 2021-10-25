package tpProgra2;

public class Votante extends Persona {
	
	private boolean enfPrevia;
	private boolean trabaja;
	private boolean esPresDeMesa;
	private boolean yaVoto;
	private Tupla<Integer,Integer> turno;


	Votante(String nombre, int dni, int edad, boolean enfPrevia, boolean trabaja) {
		super(nombre, dni, edad);
		this.enfPrevia=enfPrevia;
		this.trabaja=trabaja;
	}
	
	public void votar(){
		this.yaVoto=true;
	}
	
	public boolean saberSiVoto() {
		return this.yaVoto;
	}
	
	public boolean esTrabajador() {
		return this.trabaja;
	}
	
	public boolean tieneEnfPrevia() {
		return this.enfPrevia;
	}
	
	public void asignarPresDeMesa(boolean siOno) {
		this.esPresDeMesa=siOno;
	}
	
	public boolean esPresDeMesa() {
		return this.esPresDeMesa;
	}
	
	public void asignarTurno(Integer codDeMesa, Integer franja) {
		this.turno= new Tupla<Integer, Integer>(codDeMesa,franja);
	}
	
	public Tupla<Integer, Integer> consultarTurno() {
		return this.turno;
	}
	
	public boolean tieneTurnoAsignado(){
		return this.turno!=null;
	}
}
