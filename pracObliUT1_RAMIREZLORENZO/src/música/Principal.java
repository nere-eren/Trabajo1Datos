package música;

import java.util.ArrayList;

import Gestores.GestorAlbumes;
import Gestores.GestorGrupos;

public class Principal {

	public static void main(String[] args) {
		
		//leemos los albumes
		ArrayList<Grupo> grupos = GestorGrupos.leerGrupos();
		//leemos los grupos
		ArrayList<Album> albumes = GestorAlbumes.leerFichTXT();
		
		GestorGrupos.crearXML(grupos, albumes);
	}

}
