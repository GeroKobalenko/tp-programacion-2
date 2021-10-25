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
		if(nombreSistema == null) throw new RuntimeException("No ha proporcionado un nombre.");
		this.nombreSistema=nombreSistema;
		this.votantes= new HashMap<>();
		this.mesas=new ArrayList<>();
	}
	
	public void registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean trabaja) {
		
		if(edad<16) throw new RuntimeException("La edad no es valida para votar.");
	
		this.votantes.put(dni, new Votante(nombre, dni, edad, enfPrevia, trabaja));
	}
	
	public int agregarMesa(String tipoMesa,int dni) {
		
		if(!this.votantes.containsKey(dni)) throw new RuntimeErrorException(null,"El presidente de mesa no esta registrado en el sistema.");
		
		if (this.votantes.get(dni).tieneTurnoAsignado()) throw new RuntimeErrorException(null,"El presidente de mesa ya tiene un turno asignado.");
		
		if(tipoMesa.compareTo("Mayor65")==0) {
			Mesa mesaMayorAux= new MesaPersonaMayor(this.votantes.get(dni));
			this.mesas.add(mesaMayorAux);
			return mesaMayorAux.darCodigoDeMesa();
		}
		else if(tipoMesa.compareTo("Enf_Preex")==0) {
			Mesa mesaEnfermaAux= new MesaPersonaEnfermedad(this.votantes.get(dni));
			this.mesas.add(mesaEnfermaAux);
			return mesaEnfermaAux.darCodigoDeMesa();
		}
		else if(tipoMesa.compareTo("General")==0) {
			Mesa mesaGeneralAux= new MesaGeneral(this.votantes.get(dni));
			this.mesas.add(mesaGeneralAux);
			return mesaGeneralAux.darCodigoDeMesa();
		}
		else if (tipoMesa.compareTo("Trabajador")==0) {
			Mesa mesaTrabajadorAux= new MesaPersonaTrabaja(this.votantes.get(dni));
			this.mesas.add(mesaTrabajadorAux);
			return mesaTrabajadorAux.darCodigoDeMesa();
		}
		else{
			throw new RuntimeErrorException(null,"Tipo de mesa no valido");
		}
		
	}
	
	public Tupla<Integer,Integer> asignarTurno(int dni){
		
		//La uso solo en caso de las personas mayores y enfermas.
		boolean flag = false;
		
		if(!this.votantes.containsKey(dni)) throw new RuntimeException("El DNI no pertenece a ningun votante registrado.");
		
		//Preguntar a los profes, descomentar para que funquen todos los test!
//		if(this.votantes.get(dni).tieneTurnoAsignado()) return null;
		if(this.votantes.get(dni).tieneTurnoAsignado()) return this.votantes.get(dni).consultarTurno();
		
		for (Mesa mesa : this.mesas) {
			if (this.votantes.get(dni).esTrabajador() && mesa instanceof MesaPersonaTrabaja) {
				flag = mesa.asignarTurno(this.votantes.get(dni));
			}
			//Trato en especifico el caso que sea mayor y tenga enfermedad.
			else if (this.votantes.get(dni).conocerEdad()>64 && this.votantes.get(dni).tieneEnfPrevia()) {
				if (mesa instanceof MesaPersonaEnfermedad) flag = mesa.asignarTurno(this.votantes.get(dni));
				if (!flag && mesa instanceof MesaPersonaMayor) flag = mesa.asignarTurno(this.votantes.get(dni));
			}
			
			else if (!flag && this.votantes.get(dni).conocerEdad()>64 && !this.votantes.get(dni).esTrabajador() && mesa instanceof MesaPersonaMayor) {
				flag = mesa.asignarTurno(this.votantes.get(dni));
			}
			else if (!flag && this.votantes.get(dni).tieneEnfPrevia() && !this.votantes.get(dni).esTrabajador() && mesa instanceof MesaPersonaEnfermedad) {
				flag = mesa.asignarTurno(this.votantes.get(dni));
			}	
			
			else  {
					if (!flag && this.votantes.get(dni).conocerEdad()<64 && 
						!this.votantes.get(dni).esTrabajador() && 
						!this.votantes.get(dni).tieneEnfPrevia() && 
						mesa instanceof MesaGeneral) {
						
						mesa.asignarTurno(this.votantes.get(dni));
					}	
			}
		}
		return this.votantes.get(dni).consultarTurno();
		
		//ANTERIOR IMPLEMENTACION
		//Ahora solo hago un foreach, en vez de en cada condicion. Agrego lo de mesas generales que no estaba
		
//		if(this.votantes.get(dni).tieneEnfPrevia()) {
//			for(Mesa mesaPersEnferma: this.mesas ) {
//				if(mesaPersEnferma instanceof MesaPersonaEnfermedad) {
//					mesaPersEnferma.asignarTurno(this.votantes.get(dni));
//					return this.votantes.get(dni).consultarTurno();
//				}		
//			}
//		}
//		
//		else if(this.votantes.get(dni).conocerEdad()>64) {
//			for(Mesa mesaPersMayor: this.mesas ) {
//				if(mesaPersMayor instanceof MesaPersonaMayor) {
//					mesaPersMayor.asignarTurno(this.votantes.get(dni));
//					return this.votantes.get(dni).consultarTurno();
//				}
//			}
//		}
//		
//		else if(this.votantes.get(dni).esTrabajador()){
//				for(Mesa mesaPersTrabaja: this.mesas ) {
//					if(mesaPersTrabaja instanceof MesaPersonaTrabaja) {
//						mesaPersTrabaja.asignarTurno(this.votantes.get(dni));
//						return this.votantes.get(dni).consultarTurno();
//					}
//				}		
//			}
	}
	
	public int asignarTurno() {
		return asignarTurnos();
	}
	
	public int asignarTurnos() {
		int turnosAsignados=0;
		Set<Integer> votantes= this.votantes.keySet();
		for(Integer dni: votantes) {
			Tupla<Integer,Integer> tupla = this.asignarTurno(dni);
			if(tupla != null) turnosAsignados++;
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
			
			Set<Integer> keys = mesaActual.franjasHorarias.keySet();
			
			for (Integer key : keys) {
				if (tipoMesa == "Mayor65" && mesaActual instanceof MesaPersonaMayor) result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				if (tipoMesa == "Enf_Preex" && mesaActual instanceof MesaPersonaEnfermedad) result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				if (tipoMesa == "General" && mesaActual instanceof MesaGeneral) result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				if (tipoMesa == "Trabajador" && mesaActual instanceof MesaPersonaTrabaja) result = result + mesaActual.darVotantesEnFranjaHoraria(key).size();
				else{
					throw new RuntimeErrorException(null, "Tipo de mesa no valido");
				}
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
				List<Integer> dnis= new ArrayList<>();
				for (int i = 0; i < mesaAux.darVotantesEnFranjaHoraria(franja).size(); i++) {
					dnis.add(mesaAux.darVotantesEnFranjaHoraria(franja).get(i).conocerDNI());
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
				if(this.votantes.get(dni).esTrabajador())
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
