package Gestores;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import música.Album;
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
	
	
	public static void CrearElemento(String nomEtiqueta, String valor, Element raiz, Document document){
	    Element elem = document.createElement(nomEtiqueta); 
	    Text text = document.createTextNode(valor);
	    raiz.appendChild(elem);
	    elem.appendChild(text);	 	
	 }
	
	
	public static void crearXML(ArrayList<Grupo> grupos, ArrayList<Album> albumes) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
		    Document document = implementation.createDocument(null, "Grupos", null);
		    document.setXmlVersion("1.0"); 
		     
		    for (Grupo grupo : grupos) {
		    	
		    	Element raiz = document.createElement("grupo");
		    	document.getDocumentElement().appendChild(raiz); 
		    	
		    	CrearElemento("nombreGrupo", grupo.getNombreGrupo(), raiz, document);
		    	CrearElemento("fechaCreacion", Integer.toString(grupo.getFechaCreacion()), raiz, document);
		    	
		    	for (Album album : albumes) {
		    		
		    		if(album.getNombreGrupo().equals(grupo.getNombreGrupo())) {
		    			
		    			Element elem = document.createElement("album");
		    			document.getDocumentElement().appendChild(elem);
		    			
		    			CrearElemento("nombreAlbum", album.getNombreAlbum(), elem, document);
		    			CrearElemento("numCanciones", Integer.toString(album.getNumCanciones()), elem, document);
		    			CrearElemento("fechaPublicacion", Integer.toString(album.getFechaPublicacion()), elem, document);
		    			CrearElemento("duracionMin", Integer.toString(album.getDuracionMin()), elem, document);
		    			
		    		}
		    		
		    	}
		    	
		    	Source source = new DOMSource(document);
		        Result result = new StreamResult(new java.io.File("Grupos.xml"));        
		        Transformer transformer = TransformerFactory.newInstance().newTransformer();
		        transformer.transform(source, result);
		        
		    }
		     
			
		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			System.out.println(e);
		}
			
			
	}
}
