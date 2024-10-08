package Gestores;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import música.Grupo;

public class GestorGrupos {

	public static ArrayList<Grupo> leerGrupos(String fich) {
		
		File fichero = new File(fich);
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
				
				System.out.println(nomS + " " + fecha);
				
				posicion += 28;
			}
			
			ranFile.close();
			return grupos;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
	public static void crearXML() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
		    Document document = implementation.createDocument(null, "Grupos", null);
		    document.setXmlVersion("1.0"); 
		     
		     
		     
			
		} catch (ParserConfigurationException e) {
			System.out.println(e);
		}
			
			
	}
}
