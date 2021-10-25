package tpProgra2;

public class Tupla<X, Y> {
	final X x;
	final Y y;

	public Tupla(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X getX() {
		return x;
	}

	public Y getY() {
		return y;
	}

	@Override
	public String toString() {
		return new StringBuilder("Codigo de mesa=").append(getX()).
		append(", Franja Horaria=").append(getY()).toString();
	}

}
