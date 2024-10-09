package Gestores;

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

public class GestorXML {
	
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
		    	
		    	Element grupoElem = document.createElement("grupo");
		    	document.getDocumentElement().appendChild(grupoElem); 
		    	
		    	CrearElemento("nombreGrupo", grupo.getNombreGrupo(), grupoElem, document);
		    	CrearElemento("fechaCreacion", Integer.toString(grupo.getFechaCreacion()), grupoElem, document);
		    	
		    	for (Album album : albumes) {
		    		
		    		if(album.getNombreGrupo().equals(grupo.getNombreGrupo())) {
		    			
		    			Element albumElem = document.createElement("album");
		    			grupoElem.appendChild(albumElem);
		    			
		    			CrearElemento("nombreAlbum", album.getNombreAlbum(), albumElem, document);
		    			CrearElemento("numCanciones", Integer.toString(album.getNumCanciones()), albumElem, document);
		    			CrearElemento("fechaPublicacion", Integer.toString(album.getFechaPublicacion()), albumElem, document);
		    			CrearElemento("duracionMin", Integer.toString(album.getDuracionMin()), albumElem, document);
		    			
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
