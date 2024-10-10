package música;

public class Album {

	// atributos

	private String nombreGrupo;
	private String nombreAlbum;
	private int numCanciones;
	private int fechaPublicacion;
	private int duracionMin;
	
	// getters y setters
	
	public String getNombreGrupo() {
		return nombreGrupo;
	}

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

	public int getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(int fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}
	
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	// constructor
	public Album(String nombreGrupo, String nombreAlbum, int numCanciones, int fechaPublicacion, int duracionMin) {
		this.nombreGrupo = nombreGrupo;
		this.nombreAlbum = nombreAlbum;
		this.numCanciones = numCanciones;
		this.fechaPublicacion = fechaPublicacion;
		this.duracionMin = duracionMin;
	}
	
	public String toString() {
		String str = "Nombre del grupo: " + nombreGrupo + ", nombre del album: "+ nombreAlbum
				+ ", numero de canciones: " + numCanciones + ", fecha de publicacion: " + fechaPublicacion 
				+ ", duracion (min): " + duracionMin;
		return str;
	}

}
