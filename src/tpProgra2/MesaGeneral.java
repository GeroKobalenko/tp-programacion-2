package tpProgra2;

import java.util.Hashtable;

public class MesaGeneral extends Mesa{
	private static Integer codDeMesa=0;
		
	private int cupoDeMesa;

	MesaGeneral(Votante presDeMesa){
		this.presDeMesa=presDeMesa;
		MesaGeneral.codDeMesa++;
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
	
	public String conocerHorario() {
		
	}
	
	public int darCupoDeMesa(int franja) {
		
	}
		
	private void inicializarFranjas() {
		
	}
		
}