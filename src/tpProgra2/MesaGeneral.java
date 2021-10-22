package tpProgra2;

import java.util.*;

public class MesaGeneral extends Mesa{
	private  Integer codDeMesa=0;

	MesaGeneral(Votante presDeMesa){
		this.presDeMesa=presDeMesa;
		codDeMesa=++Mesa.codigoDeMesa;
		this.franjasHorarias= new HashMap<Integer,Set<Votante>>(); //DNI MEJOR ???
		this.inicializarFranjas();
		this.franjasHorarias.get(8).add(presDeMesa);
		}
		
	@Override
	void asignarTurno(Votante votante) {
		
		for(int i=8; i<=18 ; i++) {
			if(this.franjasHorarias.get(i).size()<29) {
				this.franjasHorarias.get(i).add(votante);
				votante.asignarTurno(this.codigoDeMesa, i);
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

	@Override
	public Integer darCodigoDeMesa() {
		return this.codDeMesa;
	}

}