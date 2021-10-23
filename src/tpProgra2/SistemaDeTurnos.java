package tpProgra2;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

public class SistemaDeTurnos {
	private String nombreSistema;
	private HashMap<Integer,Votante> votantes;
//	private LinkedList<Mesa> mesas;
	private ArrayList<Mesa> mesas;
	
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
		
		if(tipoMesa.compareTo("Mayor65")==0) {
			Mesa mesaMayorAxu= new MesaPersonaMayor(this.votantes.get(dni));
			this.mesas.add(mesaMayorAxu);
			return mesaMayorAxu.darCodigoDeMesa();
		}
		else if(tipoMesa.compareTo("Enf_Preex")==0) {
			Mesa mesaEnfermaAxu= new MesaPersonaEnfermedad(this.votantes.get(dni));
			this.mesas.add(mesaEnfermaAxu);
			return mesaEnfermaAxu.darCodigoDeMesa();
		}
		else if(tipoMesa.compareTo("General")==0) {
			Mesa mesaGeneralAxu= new MesaGeneral(this.votantes.get(dni));
			this.mesas.add(mesaGeneralAxu);
			return mesaGeneralAxu.darCodigoDeMesa();
		}
		else {
			Mesa mesaTrabajadorAxu= new MesaPersonaTrabaja(this.votantes.get(dni));
			this.mesas.add(mesaTrabajadorAxu);
			return mesaTrabajadorAxu.darCodigoDeMesa();
		}
		
	}
	
	public Tupla<Integer,Integer> asignarTurno(int dni){
		if(!this.votantes.containsKey(dni))
			throw new RuntimeException();
		
		if(!this.votantes.get(dni).consultarTurno().getX().equals(null))
			return this.votantes.get(dni).consultarTurno();
		
		if(this.votantes.get(dni).tieneEnfPrevia()) {
			for(Mesa mesaPersEnferma: this.mesas ) {
				if(mesaPersEnferma instanceof MesaPersonaEnfermedad) {
					mesaPersEnferma.asignarTurno(this.votantes.get(dni));
					return this.votantes.get(dni).consultarTurno();
				}		
			}
		}
		
		else if(this.votantes.get(dni).conocerEdad()>64) {
			for(Mesa mesaPersMayor: this.mesas ) {
				if(mesaPersMayor instanceof MesaPersonaMayor) {
					mesaPersMayor.asignarTurno(this.votantes.get(dni));
					return this.votantes.get(dni).consultarTurno();
				}
			}
		}
		
		else if(this.votantes.get(dni).esTrabajor()){
			if(this.votantes.get(dni).tieneCertificado()) {
				for(Mesa mesaPersTrabaja: this.mesas ) {
					if(mesaPersTrabaja instanceof MesaPersonaTrabaja) {
						mesaPersTrabaja.asignarTurno(this.votantes.get(dni));
						return this.votantes.get(dni).consultarTurno();
					}
				}		
			}
			else if(!this.votantes.get(dni).tieneCertificado()) {
				for(Mesa mesaGeneral: this.mesas ) {
					if(mesaGeneral instanceof MesaGeneral) {
						mesaGeneral.asignarTurno(this.votantes.get(dni));
						return this.votantes.get(dni).consultarTurno();
					}
				}
			}
		}
		
		return null;
	}
	
	public int asignarTurno() {
		int turnosAsignados=0;
		Set<Integer> votantes= this.votantes.keySet();
		for(Integer dni: votantes) {
			if(!this.asignarTurno(dni).equals(null)) {
				turnosAsignados++;
			}
		}
		return turnosAsignados;
	}
	
	public boolean votar(int dni) {
		if(!this.votantes.containsKey(dni))
			throw new RuntimeException();
			
		else if(this.votantes.get(dni).saberSiVoto())
			return false;
		
		else {
			this.votantes.get(dni).votar();
			return true;
		}
	}
	
	
	public int votantesConTurno(String tipoMesa) {
		
		int result = 0;
		
		for (int i=0; i <mesas.size(); i++) {
			Mesa mesaActual = mesas.get(i);
			
			if (tipoMesa == "Mayor65" && mesaActual instanceof MesaPersonaMayor) {
				
				Set<Integer> keys = mesaActual.franjasHorarias.keySet();
				
				for (Integer key : keys) {
					result = result + mesaActual.franjasHorarias.get(key).size();
				}
			}
			
			if (tipoMesa == "Enf_Preex" && mesaActual instanceof MesaPersonaEnfermedad) {
				
				Set<Integer> keys = mesaActual.franjasHorarias.keySet();
				
				for (Integer key : keys) {
					result = result + mesaActual.franjasHorarias.get(key).size();
				}
			}
			if (tipoMesa == "General" && mesaActual instanceof MesaPersonaEnfermedad) {
				
				Set<Integer> keys = mesaActual.franjasHorarias.keySet();
				
				for (Integer key : keys) {
					result = result + mesaActual.franjasHorarias.get(key).size();
				}
			}
			else {
				Set<Integer> keys = mesaActual.franjasHorarias.keySet();
				
				for (Integer key : keys) {
					result = result + mesaActual.franjasHorarias.get(key).size();
				}
			}
			
		}
		return result;
	}
	
	
	public Tupla<Integer, Integer> consultaTurno(int dni){
		return this.votantes.get(dni).consultarTurno();
	}
	
	
	public Map<Integer,List< Integer>> asignadosAMesa(int numMesa){
		
		Map<Integer, List<Integer>> franjaHorariaConVotantes = new HashMap<Integer,List<Integer>>();
		Set<Integer> franjas= this.mesas.get(numMesa).franjasHorarias.keySet();
		
		for(Integer hora: franjas) {
			Votante[] votanteAux= this.mesas.get(numMesa).darVotantesEnFranjaHoraria(hora);
			List<Integer> dnis= new ArrayList<>();
			for(int i = 0; i<votanteAux.length;i++) {
				dnis.add(votanteAux[i].conocerDNI());
			}
			franjaHorariaConVotantes.put(hora, dnis);
		}
		
		return franjaHorariaConVotantes;
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
