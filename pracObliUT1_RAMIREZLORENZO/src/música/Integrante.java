package música;

public class Integrante {

	// atributos
	private String nombre;
	private String instrumento;
	private int edad;

	// getters y setters
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	//Constructor
	
	public Integrante(String nombre, String instrumento, int edad) {
		this.nombre = nombre;
		this.instrumento = instrumento;
		this.edad = edad;
	}
}
