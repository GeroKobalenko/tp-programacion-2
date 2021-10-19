package tpProgra2;

import java.util.Hashtable;

public abstract class Mesa {
	private String codigoDeMesa;
	private Votante presDeMesa;
	private Hashtable <Integer, Votante[]> franjasHorarias;
	
	String darCodigoDeMesa(){
		return codigoDeMesa;
		}
	
	Votante darPresDeMesa(){
		return presDeMesa;
		}
	
	Votante[] darVotantesEnFranjaHoraria(int franja) {
		return this.franjasHorarias.get(franja);
	}
	
	abstract void asignarTurno(Votante votante);
	
	int darCupoDeMesa(int franja) {
		return franja;
	}
	
	abstract void confirmarVoto(int dni);
	
}
