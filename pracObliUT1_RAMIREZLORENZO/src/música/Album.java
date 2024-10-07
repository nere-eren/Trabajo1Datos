package música;

public class Album {

	// atributos

	private String nombreAlbum;
	private int numCanciones;
	private String fechaPublicacion;
	private int duracionMin;
	
	// getters y setters

	public String getNombreAlbum() {
		return nombreAlbum;
	}

	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}

	public int getNumCanciones() {
		return numCanciones;
	}

	public void setNumCanciones(int numCanciones) {
		this.numCanciones = numCanciones;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	// constructor
	public Album(String nombreAlbum, int numCanciones, String fechaPublicacion, int duracionMin) {
		this.nombreAlbum = nombreAlbum;
		this.numCanciones = numCanciones;
		this.fechaPublicacion = fechaPublicacion;
		this.duracionMin = duracionMin;
	}
	

}
