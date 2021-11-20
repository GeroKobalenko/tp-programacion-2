package tpProgra2;

import java.util.*;

public class MesaPersonaTrabaja extends Mesa {

	MesaPersonaTrabaja(Votante presDeMesa) {
		
		super(presDeMesa);
	}

	@Override
	boolean asignarTurno(Votante votante) {
		this.franjasHorarias.get(8).add(votante);
		votante.asignarTurno(this.darCodigoDeMesa(), 8);
		return true;
	}

	@Override
	public void confirmarVoto(Votante votante) {
		if (this.franjasHorarias.get(8).contains(votante))
			votante.votar();
	}

	@Override
	public ArrayList<Votante> darVotantesEnFranjaHoraria(int franja) {
		return this.franjasHorarias.get(8);
	}

	@Override
	public int darCupoDeMesa(int franja) {
		return 999;
	}

	@Override
	public void inicializarFranjas() {
		this.franjasHorarias.put(8, new ArrayList<>());
	}

	@Override
	public int buscarFranjaDisponible() {
		return this.franjasHorarias.keySet().iterator().next();
	}
}
