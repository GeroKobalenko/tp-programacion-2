package tpProgra2;

import java.util.Hashtable;
import java.util.Objects;

public class MesaPersonaMayor extends Mesa{

	MesaPersonaMayor(Votante presDeMesa){
		super(presDeMesa);
		
//		this.presDeMesa=presDeMesa;
//		MesaPersonaMayor.codDeMesa++;
//		this.codigoDeMesa=codDeMesa.toString();
//		this.franjasHorarias= new Hashtable<Integer, Votante[]>();//DNI MEJOR ???
//		this.inicializarFranjas();
		}
	
	@Override
	void asignarTurno(Votante votante) {
		for(int i=8; i<=18 ; i++) {
			this.franjasHorarias.get(i);
			if(franjasHorarias.get(i).size() < 10) {}
//				this.franjasHorarias.get(i).
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		return Objects.equals(this.getClass(), other.getClass());
	}

	@Override
	public void inicializarFranjas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer darCodigoDeMesa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Votante[] darVotantesEnFranjaHoraria(int franja) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void confirmarVoto(Votante votante) {
		// TODO Auto-generated method stub
		
	}
	
}
