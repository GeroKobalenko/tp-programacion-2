package tpProgra2;

import java.util.*;

public class MesaPersonaEnfermedad extends Mesa{
	
	MesaPersonaEnfermedad(Votante presDeMesa){
		super(presDeMesa);
		}
		
	@Override
	boolean asignarTurno(Votante votante) {
		boolean result = false;
		int keyFranja = buscarFranjaDisponible();
		if(keyFranja!=0){
			this.franjasHorarias.get(keyFranja).add(votante);
			votante.asignarTurno(this.darCodigoDeMesa(), keyFranja);
			result = true;
		}	
		return result;
	}
	
	@Override
	public int buscarFranjaDisponible() {
		for(int i=8; i<18 ; i++) {
			if (this.franjasHorarias.get(i).size() < 20) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public void confirmarVoto(Votante votante) {
		Set<Integer> franjasHorarias=this.franjasHorarias.keySet();
		for(int franjas: franjasHorarias) {
			if(this.franjasHorarias.get(franjas).contains(votante)) {
				votante.votar();
			}
		}
	}
	
	@Override	
	public ArrayList<Votante> darVotantesEnFranjaHoraria(int franja) {
		return this.franjasHorarias.get(franja);
	}
	
	@Override	
	public int darCupoDeMesa(int franja) {
		return 20-this.franjasHorarias.get(franja).size();
	}
	
	@Override
	public void inicializarFranjas() {
		for(int i=8; i<18 ; i++)
			this.franjasHorarias.put(i,new ArrayList<>());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		return Objects.equals(this.darCodigoDeMesa(), other.darCodigoDeMesa());
	}
}