package tpProgra2;

import java.io.PrintStream;
import java.util.*;

import javax.swing.JOptionPane;

public class SistemaDeTurnos {
	private String nombreSistema;
	private HashMap<Integer,Votante> votantes;
	private LinkedList<Mesa> mesas;
	
	public SistemaDeTurnos(String nombreSistema) {
		if(nombreSistema.equals(null))
			throw new RuntimeException();
		this.nombreSistema=nombreSistema;
	}
	
	public void registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean trabaja) {
		if(edad<16)
			throw new RuntimeException();
		if(trabaja) {
			String certificado= JOptionPane.showInputDialog("Tiene certificado de trabajo? Si o No");
			if(certificado.compareToIgnoreCase("si")==0) {
				this.votantes.put(dni, new Votante(nombre, dni, edad, enfPrevia, trabaja));
				this.votantes.get(dni).agregarCertificado(true);
			}
			else {
				this.votantes.put(dni, new Votante(nombre, dni, edad, enfPrevia, trabaja));
				this.votantes.get(dni).agregarCertificado(false);
			}
		}
		
	}
	
	public int agregarMesa(String tipoMesa,int dni) {
		
		Mesa mesa= new MesaGeneral(this.votantes.get(dni));
		this.mesas.add(mesa);
		return mesa.codigoDeMesa;
		
	}
	
	public Tupla<Integer,Integer> asignarTurno(int dni){
		if(!this.votantes.containsKey(dni))
			throw new RuntimeException();
		if(!this.votantes.get(dni).consultarTurno().getX().equals(null)){
			return this.votantes.get(dni).consultarTurno();
		}
		if(this.votantes.get(dni).tieneEnfPrevia()) {
			Mesa mesaPersonaEnferma= new MesaPersonaEnfermedad(null);
			for(Mesa mesaPersEnferma: this.mesas ) {
				if(mesaPersEnferma.equals(mesaPersonaEnferma)) {
					mesaPersEnferma.asignarTurno(this.votantes.get(dni));
				}		
			}
		}
		else if(this.votantes.get(dni).esTrabajor()){
			Mesa mesaTrabajadores= new MesaPersonaTrabaja(null);
			for(Mesa mesaPersTrabaja: this.mesas ) {
				mesaPersTrabaja.getClass().equals(mesaTrabajadores.getClass());
				if(mesaPersTrabaja.equals(mesaTrabajadores)) {
					mesaPersTrabaja.asignarTurno(this.votantes.get(dni));
					
				}		
			}
		}
		return null;
	}
	
	public int asignarTurno() {
		/* Hace efectivo el voto del votante determinado por su DNI.
		* Si el DNI no está registrado entre los votantes debe generar una excepción
		* Si ya había votado devuelve false.
		* Si no, efectúa el voto y devuelve true.
		*/
	}
	
	public int votantesConTurno(String tipoMesa) {
		/* Asigna turnos automáticamente a los votantes sin turno.
		* El sistema busca si hay alguna mesa y franja horaria factible en la que haya disponibilidad.
		* Devuelve la cantidad de turnos que pudo asignar.
		*/
	}
	
	
	public boolean votar(int dni) {
		/* Hace efectivo el voto del votante determinado por su DNI.
		* Si el DNI no está registrado entre los votantes debe generar una excepción
		* Si ya había votado devuelve false.
		* Si no, efectúa el voto y devuelve true.
		*/
	}
	
	
	public int votantesConTurno(String tipoMesa) {
		/*
		* Cantidad de votantes con Turno asignados al tipo de mesa que se pide.
		* - Permite conocer cuántos votantes se asignaron hasta el momento a alguno
		* de los tipos de mesa que componen el sistema de votación.
		* - Si la clase de mesa solicitada no es válida debe generar una excepción
		*/
	}
	
	
	public Tupla<Integer, Integer> consultaTurno(int dni){
		return this.votantes.get(dni).consultarTurno();
	}
	
	
	public Map<Integer,List< Integer>> asignadosAMesa(int numMesa){
		/* Dado un número de mesa, devuelve una Map cuya clave es la franja horaria y
		* el valor es una lista con los DNI de los votantes asignados a esa franja.
		* Sin importar si se presentaron o no a votar.
		* - Si el número de mesa no es válido genera una excepción.
		* - Si no hay asignados devuelve null.
		*/
	}

	public List<Tupla<String, Integer>> sinTurnoSegunTipoMesa(){
		/*
		* Consultar la cantidad de votantes sin turno asignados a cada tipo de mesa.
		* Devuelve una Lista de Tuplas donde se vincula el tipo de mesa con la cantidad
		* de votantes sin turno que esperan ser asignados a ese tipo de mesa.
		* La lista no puede tener 2 elementos para el mismo tipo de mesa.
		*/
	}	
	
}
