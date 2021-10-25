package tpProgra2;

import java.util.*;

public abstract class Mesa {
	
	 private static Integer contadorCodigo=0;
	 private int codDeMesa;
	 protected Votante presDeMesa;
	 protected HashMap<Integer, ArrayList<Votante>> franjasHorarias;
	 private StringBuilder toString= new StringBuilder();
	 
	 Mesa(Votante presidenteDeMesa){
		 presidenteDeMesa.asignarPresDeMesa(true);
		 presDeMesa = presidenteDeMesa;
		 this.franjasHorarias= new HashMap<Integer,ArrayList<Votante>>();
		 Mesa.contadorCodigo++;
		 codDeMesa = contadorCodigo;
		 inicializarFranjas();
		 asignarTurno(presDeMesa);
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
	
	abstract boolean asignarTurno(Votante votante);
	
	public abstract int darCupoDeMesa(int franja);
	
	abstract void confirmarVoto(Votante votante);

	public abstract boolean equals(Object obj);


	@Override
	public String toString() {
		return new StringBuilder("Tipo: ").append(getClass().getName()).
		append("Presidente de mesa: ").append(presDeMesa.conocerNombre()).append("\n").toString();
	}

}
