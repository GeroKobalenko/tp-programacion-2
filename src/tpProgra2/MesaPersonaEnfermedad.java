package tpProgra2;

import java.util.*;

public class MesaPersonaEnfermedad extends Mesa{
	private static Integer codDeMesa=0;

	MesaPersonaEnfermedad(Votante presDeMesa){
		this.presDeMesa=presDeMesa;
		MesaPersonaEnfermedad.codDeMesa++;
		this.codigoDeMesa=codDeMesa.toString();
		this.franjasHorarias= new HashMap<Integer,Set<Votante>>(); //DNI MEJOR ???
		this.inicializarFranjas();
		}
		
	@Override
	void asignarTurno(Votante votante) {
		for(int i=8; i<=18 ; i++) {
			if(this.franjasHorarias.get(i).size()<20)
				this.franjasHorarias.get(i).add(votante);
		}
				
	}

	@Override
	public void confirmarVoto(int dni) {
		//No se como hacerlo
	}
	
	@Override	
	public Votante[] darVotantesEnFranjaHoraria(int franja) {
		return (Votante[]) this.franjasHorarias.get(franja).toArray();
	}
	
	public int darCupoDeMesa(int franja) {
		return 20-this.franjasHorarias.get(franja).size();
	}
		
	private void inicializarFranjas() {
		for(int i=8; i<=18 ; i++)
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
		return Objects.equals(codigoDeMesa, other.codigoDeMesa);
	}

}