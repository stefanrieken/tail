package tail;

public class Recurse extends RuntimeException {
	private static final long serialVersionUID = 5179594963711915023L;

	public Object[] args;
	public Recurse (Object ... args) {
		this.args = args;
	}
}
