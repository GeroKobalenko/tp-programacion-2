package tpProgra2;

import java.util.*;

public abstract class Mesa {

	private static Integer contadorCodigo = 0;
	private int codDeMesa;
	protected Votante presDeMesa;
	protected HashMap<Integer, ArrayList<Votante>> franjasHorarias;

	Mesa(Votante presidenteDeMesa) {
		
		presidenteDeMesa.asignarPresDeMesa(true);
		presDeMesa = presidenteDeMesa;
		
		this.franjasHorarias = new HashMap<Integer, ArrayList<Votante>>();
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

	public Votante darPresDeMesa() {
		return presDeMesa;
	}

	public abstract ArrayList<Votante> darVotantesEnFranjaHoraria(int franja);

	abstract boolean asignarTurno(Votante votante);

	public abstract int darCupoDeMesa(int franja);

	abstract void confirmarVoto(Votante votante);

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

	@Override
	public String toString() {
		return new StringBuilder("Tipo: ").append(getClass().getName()).append(", Presidente de mesa: ")
				.append(presDeMesa.toString()).append("\n").toString();
	}

}
