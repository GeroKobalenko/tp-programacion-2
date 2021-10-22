package tpProgra2;

import java.util.*;

public abstract class Mesa{
	 protected static Integer codigoDeMesa;
	 protected Votante presDeMesa;
	 protected HashMap<Integer, Set<Votante>> franjasHorarias;
	
	 public abstract Integer darCodigoDeMesa();
	
	Votante darPresDeMesa(){
		return presDeMesa;
		}
	
	public abstract Votante[] darVotantesEnFranjaHoraria(int franja);
	
	abstract void asignarTurno(Votante votante);
	
	int darCupoDeMesa(int franja) {
		return franja;
	}
	
	abstract void confirmarVoto(Votante votante);

	@Override
	public
	abstract boolean equals(Object obj);
	
}
