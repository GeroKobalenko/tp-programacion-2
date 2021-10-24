package tpProgra2;

import java.util.*;

public abstract class Mesa implements Iterable<Mesa>{
	
	 private static Integer contadorCodigo=0;
	 private int codDeMesa;
	 protected Votante presDeMesa;
	 protected HashMap<Integer, ArrayList<Votante>> franjasHorarias;
	 
	 //Ver si quitar
//	 Mesa(){
//		 Mesa.contadorCodigo++;
//	 }
	 
	 Mesa(Votante presidenteDeMesa){
		 presDeMesa = presidenteDeMesa;
		 this.franjasHorarias= new HashMap<Integer,ArrayList<Votante>>();
		 Mesa.contadorCodigo++;
		 codDeMesa = contadorCodigo;
		 inicializarFranjas();
	 }
	 
	public abstract void inicializarFranjas();
	
	public abstract int buscarFranjaDisponible();
	
	public Integer darCodigoDeMesa() {
		return codDeMesa;
	}
	
	public Votante darPresDeMesa(){
		return presDeMesa;
		}
	
	public abstract ArrayList<Votante> darVotantesEnFranjaHoraria(int franja);
	
	abstract void asignarTurno(Votante votante);
	
	public abstract int darCupoDeMesa(int franja);
	
	abstract void confirmarVoto(Votante votante);

	public abstract boolean equals(Object obj);
	
}
