package tpProgra2;

import java.util.*;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

public class SistemaDeTurnos {
	private String nombreSistema;
	private HashMap<Integer,Votante> votantes;
//	private LinkedList<Mesa> mesas;
	private ArrayList<Mesa> mesas;
	
	public SistemaDeTurnos(String nombreSistema) {
		if(nombreSistema.equals(null))
			throw new RuntimeException();
		this.nombreSistema=nombreSistema;
		this.votantes= new HashMap<>();
		this.mesas=new ArrayList<>();
	}
	
	public void registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean trabaja) {
		if(edad<16)
			throw new RuntimeException();
	
		this.votantes.put(dni, new Votante(nombre, dni, edad, enfPrevia, trabaja));
	}
	
	public int agregarMesa(String tipoMesa,int dni) {
		if(!this.votantes.containsKey(dni))
			throw new RuntimeErrorException(null,"El presidente de mesa no esta registrado en el sistema");
		
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
		else if (tipoMesa.compareTo("Trabajador")==0) {
			Mesa mesaTrabajadorAxu= new MesaPersonaTrabaja(this.votantes.get(dni));
			this.mesas.add(mesaTrabajadorAxu);
			return mesaTrabajadorAxu.darCodigoDeMesa();
		}
		else{
			throw new RuntimeErrorException(null,"TIpo de mesa no valido");
		}
		
	}
	
	public Tupla<Integer,Integer> asignarTurno(int dni){
		if(!this.votantes.containsKey(dni))
			throw new RuntimeException();
		
		if(!this.votantes.get(dni).tieneTurnoAsignado())
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
				for(Mesa mesaPersTrabaja: this.mesas ) {
					if(mesaPersTrabaja instanceof MesaPersonaTrabaja) {
						mesaPersTrabaja.asignarTurno(this.votantes.get(dni));
						return this.votantes.get(dni).consultarTurno();
					}
				}		
			}
		return null;
	}
	
	public int asignarTurno() {
		return asignarTurnos();
	}
	
	public int asignarTurnos() {
		int turnosAsignados=0;
		Set<Integer> votantes= this.votantes.keySet();
		for(Integer dni: votantes) {
			this.asignarTurno(dni);
			if(this.votantes.get(dni).tieneTurnoAsignado()) 
				turnosAsignados++;
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
			if (tipoMesa == "Trabajador" && mesaActual instanceof MesaPersonaTrabaja) {
				Set<Integer> keys = mesaActual.franjasHorarias.keySet();
				
				for (Integer key : keys) {
					result = result + mesaActual.franjasHorarias.get(key).size();
				}
			}
			else{
				throw new RuntimeErrorException(null, "Tipo de mesa no valido");
			}
			
		}
		return result;
	}
	
	
	public Tupla<Integer, Integer> consultaTurno(int dni){
		if(!this.votantes.containsKey(dni)){
			throw new RuntimeErrorException(null,"El DNI no esta registrado en el sistema");
		}
		return this.votantes.get(dni).consultarTurno();
	}
	
	
	public Map<Integer,List< Integer>> asignadosAMesa(int numMesa){	
		Map<Integer,List<Integer>> votantesAsignadosAmesa = new HashMap<Integer, List<Integer>>();
		List<Integer> dnis= new ArrayList<>();

		//Buscar mesa
		int numDeMesaBuscado=0;
		boolean bandera= true;
		while(bandera && numDeMesaBuscado<this.mesas.size()){
			if(this.mesas.get(numDeMesaBuscado).darCodigoDeMesa()==numMesa){
				bandera=false;
			}
			else{
				numDeMesaBuscado++;
			}
		}

		Mesa mesaAux= this.mesas.get(numDeMesaBuscado);
		Set<Integer> franjasHorarias= mesaAux.franjasHorarias.keySet();
		//buscar votantes en mesa
		if(!bandera){
			for (Integer franja : franjasHorarias) {
				for (int i = 0; i < mesaAux.franjasHorarias.get(franja).size(); i++) {
					dnis.add(mesaAux.franjasHorarias.get(franja).get(i).conocerDNI());
				}
				votantesAsignadosAmesa.put(franja, dnis);
			}
		}

		return votantesAsignadosAmesa;
	}
	

	public List<Tupla<String, Integer>> sinTurnoSegunTipoMesa(){

		List<Tupla<String,Integer>> votantesSinTurno = new ArrayList<Tupla<String,Integer>>();
	
		Set<Integer> dnis= this.votantes.keySet();
		int contMesaEnfPrevia=0;
		int contMesaTrabajadores=0;
		int contMesaPersonaMayor=0;
		int contMesaGeneral=0;
		for (Integer dni : dnis) {
			if(!this.votantes.get(dni).tieneTurnoAsignado()){
				if(this.votantes.get(dni).esTrabajor())
					contMesaTrabajadores++;
				else if(this.votantes.get(dni).tieneEnfPrevia())
					contMesaEnfPrevia++;
				else if(this.votantes.get(dni).conocerEdad()>64)
					contMesaPersonaMayor++;
				else{
					contMesaGeneral++;
				}
			}
		}
		
		Tupla<String,Integer> mesaEnfermedades= new Tupla<String,Integer>("MesaEnfermedades", contMesaEnfPrevia);
		
		Tupla<String,Integer> mesaPersonaMayor= new Tupla<String,Integer>("MesaPersonaMayor", contMesaPersonaMayor);
		
		Tupla<String,Integer> mesaTrabajadores= new Tupla<String,Integer>("MesaTrabajadores", contMesaTrabajadores);
		
		Tupla<String,Integer> mesaGeneral= new Tupla<String,Integer>("MesaGeneral", contMesaGeneral);
		
		
		
		votantesSinTurno.add(mesaEnfermedades); votantesSinTurno.add(mesaPersonaMayor);
		votantesSinTurno.add(mesaTrabajadores); votantesSinTurno.add(mesaGeneral);

		return votantesSinTurno;
	}	
	
}
