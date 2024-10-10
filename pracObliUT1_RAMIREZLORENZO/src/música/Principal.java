package música;

import java.util.ArrayList;

import Gestores.GestorAlbumes;
import Gestores.GestorGrupos;
import Gestores.GestorXML;

public class Principal {

	public static void main(String[] args) {
		
		//leemos los albumes
		ArrayList<Album> albumes = GestorAlbumes.leerFichTXT();
		//leemos los grupos
		ArrayList<Grupo> grupos = GestorGrupos.leerGrupos();
		
		//Creamos XML
		GestorXML.crearXML(grupos, albumes);
		
		//CRUD
		GestorXML.insertAlbum(new Album("The cure", "Wish", 12, 1992, 65));
		System.out.println(GestorXML.consultaAlbum("Dipsomania"));
		GestorXML.updateAlbumes("Wish", "A fragile thing", 2, 2024, 11);
		GestorXML.deleteAlbumes("seventeen seconds");
		
		//Select
		ArrayList<Album> albumesSelect = GestorXML.consultaAlbumPorDuracion(60);
		System.out.println("Albumes que duran mas de una hora:");
		for(Album album: albumesSelect) {
			System.out.println(album);
		}
		//Crear XML a partir del select
		GestorXML.crearXMLalbumes(GestorXML.consultaAlbumPorDuracion(60));
	}

}
