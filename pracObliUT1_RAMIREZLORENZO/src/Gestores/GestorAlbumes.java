package Gestores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import música.Album;

public class GestorAlbumes {

	public static ArrayList<Album> leerFichTXT() {
		try {
			File fichAlbum = new File("Albumes.txt");
			ArrayList<Album> albumes = new ArrayList<Album>();
			
			BufferedReader fichBuf = new BufferedReader(new FileReader(fichAlbum));
			String fila; String[] filaSpliteada = null;
			
			String nomGrupo;
			String nomAlb;
			int numCan;
			int fechaPubli;
			int duracion;
			
			while ((fila = fichBuf.readLine()) != null) {
				filaSpliteada = fila.split(":");
				
				nomGrupo = filaSpliteada[0];
				
				nomAlb = filaSpliteada[1];
				
				numCan =  Integer.parseInt(filaSpliteada[2]);
				fechaPubli =  Integer.parseInt(filaSpliteada[3]);
				duracion =  Integer.parseInt(filaSpliteada[4]);
					
				Album newAlbum = new Album(nomGrupo, nomAlb,numCan,fechaPubli,duracion);
				albumes.add(newAlbum);
			}
				
			
			
			fichBuf.close();
			return albumes;
			
		} catch (FileNotFoundException fn) {
			System.out.println("Fichero imposible de encontrar");
		} catch (IOException io) {
			System.out.println("Error de E/S ");
		}return null;
	}
}
