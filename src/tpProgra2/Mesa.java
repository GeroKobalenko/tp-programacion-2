package tpProgra2;

import java.util.*;

public abstract class Mesa {
	 protected String codigoDeMesa;
	 protected Votante presDeMesa;
	 protected HashMap<Integer, Set<Votante>> franjasHorarias;
	
	String darCodigoDeMesa(){
		return codigoDeMesa;
		}
	
	Votante darPresDeMesa(){
		return presDeMesa;
		}
	
	public abstract Votante[] darVotantesEnFranjaHoraria(int franja);
	
	abstract void asignarTurno(Votante votante);
	
	int darCupoDeMesa(int franja) {
		return franja;
	}
	
	abstract void confirmarVoto(int dni);

	@Override
	abstract public boolean equals(Object obj);
	
}
