package tpProgra2;

import java.util.*;

public abstract class Mesa{
	
	 protected static Integer contadorCodigo=0;
	 
	 protected int codDeMesa;
	 protected Votante presDeMesa;
	 protected HashMap<Integer, Set<Votante>> franjasHorarias;
	 
	 //Ver si quitar
//	 Mesa(){
//		 Mesa.contadorCodigo++;
//	 }
	 
	 Mesa(Votante presidenteDeMesa){
		 presDeMesa = presidenteDeMesa;
		 this.franjasHorarias= new HashMap<Integer,Set<Votante>>();
		 contadorCodigo++;
		 codDeMesa = contadorCodigo;
		 inicializarFranjas();
	 }
	 
	 public abstract void inicializarFranjas();
	
	public Integer darCodigoDeMesa() {
		return codDeMesa;
	};
	
	public Votante darPresDeMesa(){
		return presDeMesa;
		}
	
	public abstract Votante[] darVotantesEnFranjaHoraria(int franja);
	
	abstract void asignarTurno(Votante votante);
	
	public abstract int darCupoDeMesa(int franja);
	
	abstract void confirmarVoto(Votante votante);

	public abstract boolean equals(Object obj);
	
}
