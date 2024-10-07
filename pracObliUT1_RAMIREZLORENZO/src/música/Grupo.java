package música;

import java.util.ArrayList;

public class Grupo {

	//atributos
	private String nombreGrupo;
	private String fechaCreacion;
	private ArrayList<Integrante> integrantes = new ArrayList<>();
	private ArrayList<Album> albumes = new ArrayList<>();

	
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

	public ArrayList<Integrante> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(ArrayList<Integrante> integrantes) {
		this.integrantes = integrantes;
	}

	public ArrayList<Album> getAlbumes() {
		return albumes;
	}

	public void setAlbumes(ArrayList<Album> albumes) {
		this.albumes = albumes;
	}

	//constructor
	public Grupo(String nombreGrupo, String fechaCreacion, ArrayList<Integrante> integrantes,
			ArrayList<Album> albumes) {
		this.nombreGrupo = nombreGrupo;
		this.fechaCreacion = fechaCreacion;
		this.integrantes = integrantes;
		this.albumes = albumes;
	}

}
