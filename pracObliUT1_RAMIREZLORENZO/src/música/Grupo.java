package música;

import java.util.ArrayList;
import java.util.HashMap;

public class Grupo {

	//atributos
	private String nombreGrupo;
	private int fechaCreacion;
	private HashMap<String,Album> albumes = new HashMap<String, Album>();

	
	//getters y setters
	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public int getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(int fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public HashMap<String, Album> getAlbumes() {
		return albumes;
	}
	
	public void setAlbumes(HashMap<String, Album> albumes) {
		this.albumes = albumes;
	}

	//constructor
	public Grupo(String nombreGrupo, int fechaCreacion, HashMap<String, Album> albumes) {
		this.nombreGrupo = nombreGrupo;
		this.fechaCreacion = fechaCreacion;
		this.albumes = albumes;
	}

	//metodos
	public void anadirAlbumes(ArrayList<Album> albumes) {
		for (Album album : albumes) {
			this.albumes.put(album.getNombreGrupo(), album);			
		}
	}
	
}
