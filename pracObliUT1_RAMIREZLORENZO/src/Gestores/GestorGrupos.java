package Gestores;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import música.Grupo;

public class GestorGrupos {

	public static ArrayList<Grupo> leerGrupos() {
		
		File fichero = new File("Grupos.dat");
		try {
			RandomAccessFile ranFile = new RandomAccessFile(fichero, "r");
			
			char[] nomC = new char[12];
			int fecha;
			int posicion = 0;
			ArrayList<Grupo> grupos = new ArrayList<Grupo>();
			
			while(posicion < ranFile.length()) {
				
				ranFile.seek(posicion);
				
				for (int i = 0; i < nomC.length ;i++ ) {
					nomC[i] = ranFile.readChar(); 
				}
				
				String nomS = new String(nomC);
				nomS = nomS.trim();
				
				fecha = ranFile.readInt();
				
				Grupo grupo = new Grupo(nomS, fecha, null);
				grupos.add(grupo);
				
				posicion += 28;
			}
			
			ranFile.close();
			return grupos;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
}
