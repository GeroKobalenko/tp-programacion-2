package tpProgra2;

import java.util.*;

public class MesaGeneral extends Mesa {

	private final int franjaInicial = 8;
	private final int franjaFinal = 18;
	private final int cupoMesa = 30;

	MesaGeneral(Votante presDeMesa) {
		super(presDeMesa);
	}

	@Override
	boolean asignarTurno(Votante votante) {
		boolean result = false;
		int keyFranja = buscarFranjaDisponible();

		if (keyFranja != 0 && (esVotanteApto(votante) || votante.esPresDeMesa())) {

			darVotantesEnFranjaHoraria(keyFranja).add(votante);
			votante.asignarTurno(this.darCodigoDeMesa(), keyFranja);
			result = true;
		}
		return result;
	}

	@Override
	boolean esVotanteApto(Votante votante) {
		if (!votante.esMayorEdad() && !votante.esTrabajador() && !votante.tieneEnfPrevia()) {
			return true;
		}
		return false;
	}

	@Override
	public void confirmarVoto(Votante votante) {
		Set<Integer> franjasHorarias = this.franjasHorarias.keySet();
		for (int franjas : franjasHorarias) {
			if (darVotantesEnFranjaHoraria(franjas).contains(votante)) {
				votante.votar();
			}
		}
	}

	@Override
	public ArrayList<Votante> darVotantesEnFranjaHoraria(int franja) {
		return franjasHorarias.get(franja);
	}

	public int darCupoDeMesa(int franja) {
		return 30 - darVotantesEnFranjaHoraria(franja).size();
	}

	@Override
	public void inicializarFranjas() {
		for (int i = franjaInicial; i < franjaFinal; i++)
			this.franjasHorarias.put(i, new ArrayList<>());
	}

	@Override
	public int buscarFranjaDisponible() {
		for (int i = franjaInicial; i <= franjaFinal; i++) {
			if (darVotantesEnFranjaHoraria(i).size() < cupoMesa) {
				return i;
			}
		}
		return 0;
	}
}