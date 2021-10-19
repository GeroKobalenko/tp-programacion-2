package tpProgra2;

public class SistemaDeTurnos {
	private String nombreSistema;
	
	public SistemaDeTurnos(String nombreSistema) {
		/* Constructor del sistema de asignaci�n de turnos.
		* Si el par�metro nombre fuera null, debe generar una excepci�n.
		*/
	}
	
	public void registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean trabaja) {
		/* Registrar a los votantes. Antes de asignar un turno el votante debe estar registrado
		* en el sistema.
		* Si no lo est�, se genera una excepci�n.
		* Si la edad es menor a 16, se genera una excepci�n
		*/
	}
	
	public int agregarMesa(String tipoMesa,int dni) {
		/* Agregar una nueva mesa del tipo dado en el par�metro y asignar el presidente
		* de cada una, el cual deber� estar entre los votantes registrados y sin turno asignado.
		* - Devuelve el n�mero de mesa creada.
		* si el president es un votante que no est� registrado debe generar una excepci�n
		* si el tipo de mesa no es v�lido debe generar una excepci�n
		* Los tipos v�lidos son: �Enf_Preex�, �Mayor65�, �General� y �Trabajador�
		*/
	}
	
	public Tupla<Integer,Integer> asignarTurno(int dni){
		/* Asigna un turno a un votante determinado.
		* - Si el DNI no pertenece a un votante registrado debe generar una excepci�n.
		* - Si el votante ya tiene turno asignado se devuelve el turno como: N�mero de
		* Mesa y Franja Horaria.
		* - Si a�n no tiene turno asignado se busca una franja horaria disponible en una
		* mesa del tipo correspondiente al votante y se devuelve el turno asignado, como
		* N�mero de Mesa y Franja Horaria.
		* - Si no hay mesas con horarios disponibles no modifica nada y devuelve null.
		* (Se supone que el turno permitir� conocer la mesa y la franja horaria asignada)
		*/
	}
	
	public int asignarTurno() {
		/* Hace efectivo el voto del votante determinado por su DNI.
		* Si el DNI no est� registrado entre los votantes debe generar una excepci�n
		* Si ya hab�a votado devuelve false.
		* Si no, efect�a el voto y devuelve true.
		*/
	}
	
	public int votantesConTurno(String tipoMesa) {
		/* Asigna turnos autom�ticamente a los votantes sin turno.
		* El sistema busca si hay alguna mesa y franja horaria factible en la que haya disponibilidad.
		* Devuelve la cantidad de turnos que pudo asignar.
		*/
	}
	
	
	public boolean votar(int dni) {
		/* Hace efectivo el voto del votante determinado por su DNI.
		* Si el DNI no est� registrado entre los votantes debe generar una excepci�n
		* Si ya hab�a votado devuelve false.
		* Si no, efect�a el voto y devuelve true.
		*/
	}
	
	
	public int votantesConTurno(String tipoMesa) {
		/*
		* Cantidad de votantes con Turno asignados al tipo de mesa que se pide.
		* - Permite conocer cu�ntos votantes se asignaron hasta el momento a alguno
		* de los tipos de mesa que componen el sistema de votaci�n.
		* - Si la clase de mesa solicitada no es v�lida debe generar una excepci�n
		*/
	}
	
	
	public Tupla<Integer, Integer> consultaTurno(int dni){
		/* Consulta el turno de un votante dado su DNI. Devuelve Mesa y franja horaria.
		* - Si el DNI no pertenece a un votante genera una excepci�n.
		* - Si el votante no tiene turno devuelve null.
		*/
	}
	
	
	public Map<Integer,List< Integer>> asignadosAMesa(int numMesa){
		/* Dado un n�mero de mesa, devuelve una Map cuya clave es la franja horaria y
		* el valor es una lista con los DNI de los votantes asignados a esa franja.
		* Sin importar si se presentaron o no a votar.
		* - Si el n�mero de mesa no es v�lido genera una excepci�n.
		* - Si no hay asignados devuelve null.
		*/
	}

	public List<Tupla<String, Integer>> sinTurnoSegunTipoMesa(){
		/*
		* Consultar la cantidad de votantes sin turno asignados a cada tipo de mesa.
		* Devuelve una Lista de Tuplas donde se vincula el tipo de mesa con la cantidad
		* de votantes sin turno que esperan ser asignados a ese tipo de mesa.
		* La lista no puede tener 2 elementos para el mismo tipo de mesa.
		*/
	}	
	
}
