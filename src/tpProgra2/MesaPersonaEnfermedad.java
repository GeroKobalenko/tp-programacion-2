package tpProgra2;

import java.util.Hashtable;

public class MesaPersonaEnfermedad extends Mesa{
	private static Integer codDeMesa=0;
		
	private int cupoDeMesa;

	MesaPersonaEnfermedad(Votante presDeMesa){
		this.presDeMesa=presDeMesa;
		MesaPersonaEnfermedad.codDeMesa++;
		this.codigoDeMesa=codDeMesa.toString();
		this.franjasHorarias= new Hashtable<Integer, Votante[]>();//DNI MEJOR ???
		this.inicializarFranjas();
		}
		
	@Override
	void asignarTurno(Votante votante) {
		for(int i=8; i<=18 ; i++) {
			if(this.franjasHorarias.get(i).length<10)
				this.franjasHorarias.get(i).
		}
				
	}

	@Override
	public void confirmarVoto(int dni) {
		// TODO Auto-generated method stub
			
	}
		
	public Votante[] darVotantesEnFrajaHoraria(int franjaHoraria) {
		return votantes;
	}
	
	public int darCupoDeMesa(int franja) {
		
	}
		
	private void inicializarFranjas() {
		for(int i=8; i<=18 ; i++)
			this.franjasHorarias.put(i, new Votante[8]);
	}
		
}