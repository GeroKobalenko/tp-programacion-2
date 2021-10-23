package tpProgra2;

import java.util.*;

public class MesaPersonaEnfermedad extends Mesa{
	
	MesaPersonaEnfermedad(Votante presDeMesa){
		super(presDeMesa);
		}
		
	@Override
	void asignarTurno(Votante votante) {
		for(int i=8; i<=18 ; i++) {
			if(this.franjasHorarias.get(i).size()<19) {
				if(!this.franjasHorarias.get(i).contains(votante))
					this.franjasHorarias.get(i).add(votante);
				votante.asignarTurno(this.codDeMesa, i);
			}	
		}		
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
	public Votante[] darVotantesEnFranjaHoraria(int franja) {
		return (Votante[]) this.franjasHorarias.get(franja).toArray();
	}
	
	@Override	
	public int darCupoDeMesa(int franja) {
		return 20-this.franjasHorarias.get(franja).size();
	}
	
	@Override	
	public void inicializarFranjas() {
		for(int i=8; i<18 ; i++)
			this.franjasHorarias.put(i,new HashSet<>());
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
        return Objects.equals(this.codDeMesa, other.codDeMesa);
	}
}