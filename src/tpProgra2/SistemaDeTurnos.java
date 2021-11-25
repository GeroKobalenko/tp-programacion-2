package tpProgra2;

import java.util.*;
import javax.management.RuntimeErrorException;

public class SistemaDeTurnos {
	private String nombreSistema;
	private HashMap<Integer, Votante> votantes;
	private ArrayList<Mesa> mesas;

	public SistemaDeTurnos(String nombreSistema) {
		if (nombreSistema == null)
			throw new RuntimeException("No ha proporcionado un nombre.");
		this.nombreSistema = nombreSistema;
		this.votantes = new HashMap<>();
		this.mesas = new ArrayList<>();
	}

	public void registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean trabaja) {

		Votante votante = new Votante(nombre, dni, edad, enfPrevia, trabaja);
		this.votantes.put(dni, votante);
	}

	public int agregarMesa(String tipoMesa, int dni) {

		if (!estaRegistrado(dni))
			throw new RuntimeErrorException(null, "El presidente de mesa no esta registrado en el sistema.");

		if (tieneTurno(dni))
			throw new RuntimeErrorException(null, "El presidente de mesa ya tiene un turno asignado.");

		Votante votante = obtenerVotante(dni);

		if (tipoMesa.compareTo("Mayor65") == 0) {
			Mesa mesaMayorAux = new MesaPersonaMayor(votante);
			return aniadirMesa(mesaMayorAux);
			
		} else if (tipoMesa.compareTo("Enf_Preex") == 0) {
			Mesa mesaEnfermaAux = new MesaPersonaEnfermedad(votante);
			return aniadirMesa(mesaEnfermaAux);
			
		} else if (tipoMesa.compareTo("General") == 0) {
			Mesa mesaGeneralAux = new MesaGeneral(votante);
			return aniadirMesa(mesaGeneralAux);
			
		} else if (tipoMesa.compareTo("Trabajador") == 0) {
			Mesa mesaTrabajadorAux = new MesaPersonaTrabaja(votante);
			return aniadirMesa(mesaTrabajadorAux);
			
		} else {
			throw new RuntimeErrorException(null, "Tipo de mesa no valido");
		}

	}

	public Tupla<Integer, Integer> asignarTurno(int dni) {

		// La uso solo en caso de las personas mayores y enfermas.
		boolean flag = false;

		if (!estaRegistrado(dni))
			throw new RuntimeException("El DNI no pertenece a ningun votante registrado.");

		if (tieneTurno(dni))
			return obtenerVotante(dni).consultarTurno();

		Votante votante = obtenerVotante(dni);

		for (Mesa mesa : this.mesas) {
			while (!flag) {
				flag = mesa.asignarTurno(votante);
			}
		}
		return votante.consultarTurno();
	}

	public int asignarTurnos() {

		int turnosAsignados = 0;
		Set<Integer> votantes = obtenerDnis();

		for (Integer dni : votantes) {
			Tupla<Integer, Integer> tupla = this.asignarTurno(dni);
			if (tupla != null && !obtenerVotante(dni).esPresDeMesa())
				turnosAsignados++;
		}

		return turnosAsignados;
	}

	public boolean votar(int dni) {
		if (!estaRegistrado(dni))
			throw new RuntimeException();

		else if (obtenerVotante(dni).saberSiVoto())
			return false;

		else {
			obtenerVotante(dni).votar();
			return true;
		}
	}

	public int votantesConTurno(String tipoMesa) {

		int result = 0;

		for (int i = 0; i < mesas.size(); i++) {

			Mesa mesaActual = obtenerMesa(i);

			Set<Integer> keys = mesaActual.franjasHorarias.keySet();

			for (Integer key : keys) {
				if (tipoMesa == "Mayor65" && mesaActual instanceof MesaPersonaMayor)
					result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				if (tipoMesa == "Enf_Preex" && mesaActual instanceof MesaPersonaEnfermedad)
					result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				if (tipoMesa == "General" && mesaActual instanceof MesaGeneral)
					result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				if (tipoMesa == "Trabajador" && mesaActual instanceof MesaPersonaTrabaja)
					result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				else {
					throw new RuntimeErrorException(null, "Tipo de mesa no valido");
				}
			}
		}
		return result;
	}

	public Tupla<Integer, Integer> consultaTurno(int dni) {
		if (!estaRegistrado(dni)) {
			throw new RuntimeErrorException(null, "El DNI no esta registrado en el sistema");
		}
		return obtenerVotante(dni).consultarTurno();
	}

	public Map<Integer, List<Integer>> asignadosAMesa(int numMesa) {

		Map<Integer, List<Integer>> votantesAsignadosAmesa = new HashMap<Integer, List<Integer>>();

		// Buscar mesa
		int numDeMesaBuscado = 0;
		boolean bandera = true;
		while (bandera && numDeMesaBuscado < this.mesas.size()) {
			if (obtenerMesa(numDeMesaBuscado).darCodigoDeMesa() == numMesa) {
				bandera = false;
			} else {
				numDeMesaBuscado++;
			}
		}

		Mesa mesaAux = obtenerMesa(numDeMesaBuscado);

		Set<Integer> franjasHorarias = mesaAux.franjasHorarias.keySet();
		// buscar votantes en mesa
		if (!bandera) {
			for (Integer franja : franjasHorarias) {
				List<Integer> dnis = new ArrayList<>();
				ArrayList<Votante> votantes = mesaAux.darVotantesEnFranjaHoraria(franja);
				
				for (int i = 0; i < votantes.size(); i++) {
					dnis.add(votantes.get(i).decirDNI());
				}
				votantesAsignadosAmesa.put(franja, dnis);
			}
		}

		return votantesAsignadosAmesa;
	}

	public List<Tupla<String, Integer>> sinTurnoSegunTipoMesa() {

		List<Tupla<String, Integer>> votantesSinTurno = new ArrayList<Tupla<String, Integer>>();

		Set<Integer> dnis = obtenerDnis();
		
		int contMesaEnfPrevia = 0;
		int contMesaTrabajadores = 0;
		int contMesaPersonaMayor = 0;
		int contMesaGeneral = 0;
		
		for (Integer dni : dnis) {
			Votante votanteAux = obtenerVotante(dni);

			if (!votanteAux.tieneTurnoAsignado()) {
				if (votanteAux.esTrabajador())
					contMesaTrabajadores++;
				else if (votanteAux.tieneEnfPrevia())
					contMesaEnfPrevia++;
				else if (votanteAux.esMayorEdad())
					contMesaPersonaMayor++;
				else {
					contMesaGeneral++;
				}
			}
		}

		Tupla<String, Integer> mesaEnfermedades = new Tupla<String, Integer>("MesaEnfermedades", contMesaEnfPrevia);

		Tupla<String, Integer> mesaPersonaMayor = new Tupla<String, Integer>("MesaPersonaMayor", contMesaPersonaMayor);

		Tupla<String, Integer> mesaTrabajadores = new Tupla<String, Integer>("MesaTrabajadores", contMesaTrabajadores);

		Tupla<String, Integer> mesaGeneral = new Tupla<String, Integer>("MesaGeneral", contMesaGeneral);

		votantesSinTurno.add(mesaEnfermedades);
		votantesSinTurno.add(mesaPersonaMayor);
		votantesSinTurno.add(mesaTrabajadores);
		votantesSinTurno.add(mesaGeneral);

		return votantesSinTurno;
	}

	private boolean estaRegistrado(int dni) {
		return this.votantes.containsKey(dni);
	}

	private boolean tieneTurno(int dni) {
		return obtenerVotante(dni).tieneTurnoAsignado();
	}

	private Votante obtenerVotante(int dni) {
		return this.votantes.get(dni);
	}
	
	private Set<Integer> obtenerDnis() {
		
		return this.votantes.keySet();
	}

	private Mesa obtenerMesa(int posicion) {

		if (this.mesas.size() >= posicion) {
			return mesas.get(posicion);
		}

		throw new RuntimeException();
	}

	private int aniadirMesa(Mesa mesa) {

		this.mesas.add(mesa);
		return mesa.darCodigoDeMesa();
	}
	
	private StringBuilder obtenerStringBuilderTurnoDeVotantes() {
		
		StringBuilder turnoDeVotantes = new StringBuilder();
		Collection<Votante> votantesAux = this.votantes.values();
		
		for (Votante votanteAux : votantesAux) {
			if (votanteAux.tieneTurnoAsignado()) {
				turnoDeVotantes.append("Votante: ").append(votanteAux.toString()).
				append("\n");
			}
		}
		
		return turnoDeVotantes;
	}
	
	private StringBuilder obtenerStringBuilderSinTurnoTipoMesa() {
		
		StringBuilder votantesSinTurnos= new StringBuilder();
		
		for (int i = 0; i < sinTurnoSegunTipoMesa().size(); i++) {
			votantesSinTurnos.append(this.sinTurnoSegunTipoMesa().get(i).getX()).
			append(",votantes sin turno: ").append(this.sinTurnoSegunTipoMesa().get(i).getY()).append("\n");
		}
		
		return votantesSinTurnos;
	} 

	@Override
	public String toString() {
		return new StringBuilder().append(this.nombreSistema).append("\n").append("\n").
		append("Cantidad de votantes en espera de  asignacion de turno: ").append(obtenerStringBuilderSinTurnoTipoMesa()).
		append("\n").append("\n").append("\n").append("Turnos de votantes: ").append("\n").append("\n").
		append(obtenerStringBuilderTurnoDeVotantes()).append("\n").append("\n").append("Mesas habilitadas en el sistema: ").
		append("\n").append("\n").append(mesas.toString()).toString();
	}
}
