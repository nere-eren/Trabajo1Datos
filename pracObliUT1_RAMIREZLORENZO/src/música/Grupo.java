package música;

import java.util.HashMap;

public class Grupo {

	//atributos
	private String nombreGrupo;
	private String fechaCreacion;
	private HashMap<String,Album> albumes = new HashMap<String, Album>();

	
	//getters y setters
	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public HashMap<String, Album> getAlbumes() {
		return albumes;
	}
	
	public void setAlbumes(HashMap<String, Album> albumes) {
		this.albumes = albumes;
	}

	//constructor
	public Grupo(String nombreGrupo, String fechaCreacion, HashMap<String, Album> albumes) {
		this.nombreGrupo = nombreGrupo;
		this.fechaCreacion = fechaCreacion;
		this.albumes = albumes;
	}

}
