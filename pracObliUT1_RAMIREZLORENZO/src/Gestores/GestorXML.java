package Gestores;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import música.Album;
import música.Grupo;

public class GestorXML {
	
	
	public static final String GRUPOS = "Grupos.xml";
	public static final String ALBUMES = "Albumes.xml";
	
	//funciones genericas
	public static void crearElemento(String nomEtiqueta, String valor, Element raiz, Document document){
	    Element elem = document.createElement(nomEtiqueta); 
	    Text text = document.createTextNode(valor);
	    raiz.appendChild(elem);
	    elem.appendChild(text);	 	
	 }
	
	public static Document getDocumento(String doc) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(doc);
			document.getDocumentElement().normalize();
			return document;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static void saveDocument(Document documento, String nomDoc) {
		
		try {
			Source source = new DOMSource(documento);
			Result result = new StreamResult(new File(nomDoc));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	//funciones especificas
	
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
		    	
		    	crearElemento("nombreGrupo", grupo.getNombreGrupo(), grupoElem, document);
		    	crearElemento("fechaCreacion", Integer.toString(grupo.getFechaCreacion()), grupoElem, document);
		    	
		    	for (Album album : albumes) {
		    		
		    		if(album.getNombreGrupo().equals(grupo.getNombreGrupo())) {
		    			
		    			Element albumElem = document.createElement("album");
		    			grupoElem.appendChild(albumElem);
		    			
		    			crearElemento("nombreAlbum", album.getNombreAlbum(), albumElem, document);
		    			crearElemento("numCanciones", Integer.toString(album.getNumCanciones()), albumElem, document);
		    			crearElemento("fechaPublicacion", Integer.toString(album.getFechaPublicacion()), albumElem, document);
		    			crearElemento("duracionMin", Integer.toString(album.getDuracionMin()), albumElem, document);
		    			
		    		}
		    		
		    	}
		    	
		    	GestorXML.saveDocument(document, GRUPOS);
		    	
		        
		    }
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		     
			
			
	}
	
	//CRUD
	public static Album consultaAlbum(String nomAlbum) {
		
		Album albumConsultado = null;
		
			Document doc = getDocumento(GRUPOS);
			
			NodeList albumes = doc.getElementsByTagName("album");
			
			for (int i = 0; i<albumes.getLength();i++) {
				Node al = albumes.item(i);
				if (al.getNodeType()==Node.ELEMENT_NODE) {
					Element album = (Element) al;
					if(album.getElementsByTagName("nombreAlbum").item(0).getTextContent().equals(nomAlbum)) {
								
						Element padre = (Element) album.getParentNode();
						
						
						String nombreGrupo = padre.getElementsByTagName("nombreGrupo").item(0).getTextContent();
						String nombreAlbum = album.getElementsByTagName("nombreAlbum").item(0).getTextContent();
						int numCanciones = Integer.valueOf( album.getElementsByTagName("numCanciones").item(0).getTextContent());
						int fechaPublicacion = Integer.valueOf( album.getElementsByTagName("fechaPublicacion").item(0).getTextContent());
						int duracionMin = Integer.valueOf( album.getElementsByTagName("duracionMin").item(0).getTextContent());
						
						albumConsultado = new Album(nombreGrupo, nombreAlbum, numCanciones, fechaPublicacion, duracionMin);
						return albumConsultado;
	
					}
				}
			}
			
		return null;
		
	}
	
	public static void insertAlbum(Album album) {
		
			Document doc = getDocumento(GRUPOS);
			
			NodeList grupos = doc.getElementsByTagName("grupo");
			
			for (int i = 0; i<grupos.getLength();i++) {
				Node gru = grupos.item(i);
				if (gru.getNodeType()==Node.ELEMENT_NODE) {
					Element grupo = (Element) gru;
					if(grupo.getElementsByTagName("nombreGrupo").item(0).getTextContent().equals(album.getNombreGrupo())) {
						
						Element albumElem = doc.createElement("album");
		    			grupo.appendChild(albumElem);
		    			
		    			crearElemento("nombreAlbum", album.getNombreAlbum(), albumElem, doc);
		    			crearElemento("numCanciones", Integer.toString(album.getNumCanciones()), albumElem, doc);
		    			crearElemento("fechaPublicacion", Integer.toString(album.getFechaPublicacion()), albumElem, doc);
		    			crearElemento("duracionMin", Integer.toString(album.getDuracionMin()), albumElem, doc);
					}
					
				}
				
				GestorXML.saveDocument(doc, GRUPOS);
			}
		
	}
	
	public static void deleteAlbumes(String nAlbumABorrar) {
		
		nAlbumABorrar = nAlbumABorrar.toLowerCase();
			
			Document document = getDocumento(GRUPOS);
			NodeList albumes = document.getElementsByTagName("album");

			for (int i = 0; i < albumes.getLength(); i++) {

				Node album = albumes.item(i);

				if (album.getNodeType() == Node.ELEMENT_NODE) {
					Element albumElement = (Element) album;
					if (albumElement.getElementsByTagName("nombreAlbum").item(0).getTextContent().toLowerCase().equals(nAlbumABorrar)) {
						album.getParentNode().removeChild(album);
					}
				}
			}
			
			GestorXML.saveDocument(document, GRUPOS);
		
	}

	public static void updateAlbumes(String nAlbum, String nAlbumUpdate, int nCancionesUpdate, int fechaPubliUpdate, int durMinUpdate) {
	
		
		nAlbum = nAlbum.toLowerCase();

		try {
			
			Document document = getDocumento(GRUPOS);
			NodeList albumes = document.getElementsByTagName("album");

			for (int i = 0; i < albumes.getLength(); i++) {

				Node album = albumes.item(i);

				if (album.getNodeType() == Node.ELEMENT_NODE) {
					Element albumElement = (Element) album;
					if (albumElement.getElementsByTagName("nombreAlbum").item(0).getTextContent().toLowerCase().equals(nAlbum)) {
						NodeList atribAlbum = album.getChildNodes();
						
						atribAlbum.item(0).setTextContent(nAlbumUpdate);
						atribAlbum.item(1).setTextContent(String.valueOf(nCancionesUpdate));
						atribAlbum.item(2).setTextContent(String.valueOf(fechaPubliUpdate));
						atribAlbum.item(3).setTextContent(String.valueOf(durMinUpdate));
					}
				}
			}
			
			GestorXML.saveDocument(document, GRUPOS);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	
	public static ArrayList<Album> consultaAlbumPorDuracion(int duracionMinMinima) {
		
		Album albumConsultado = null;
		ArrayList<Album> albumesConsultados = new ArrayList<Album>();
		
			Document doc = getDocumento(GRUPOS);
			
			NodeList albumes = doc.getElementsByTagName("album");
			
			for (int i = 0; i<albumes.getLength();i++) {
				Node al = albumes.item(i);
				if (al.getNodeType()==Node.ELEMENT_NODE) {
					Element album = (Element) al;
					if(Integer.valueOf(album.getElementsByTagName("duracionMin").item(0).getTextContent()) > duracionMinMinima) {
						
						Element padre = (Element) album.getParentNode();
						
						String nombreGrupo = padre.getElementsByTagName("nombreGrupo").item(0).getTextContent();
						String nombreAlbum = album.getElementsByTagName("nombreAlbum").item(0).getTextContent();
						int numCanciones = Integer.valueOf( album.getElementsByTagName("numCanciones").item(0).getTextContent());
						int fechaPublicacion = Integer.valueOf( album.getElementsByTagName("fechaPublicacion").item(0).getTextContent());
						int duracionMin = Integer.valueOf( album.getElementsByTagName("duracionMin").item(0).getTextContent());
						
						albumConsultado = new Album(nombreGrupo, nombreAlbum, numCanciones, fechaPublicacion, duracionMin);
						
						albumesConsultados.add(albumConsultado);
	
					}
				}
			}
		
		return albumesConsultados;
		
	}
	
	public static void crearXMLalbumes(ArrayList<Album> albumes) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
		    DOMImplementation implementation = builder.getDOMImplementation();
		    Document document = implementation.createDocument(null, "Albumes", null);
		    document.setXmlVersion("1.0");
		     
		    for (Album album : albumes) {
		    	
		    	Element albumElem = document.createElement("album");
		    	document.getDocumentElement().appendChild(albumElem); 
		    	
		    	crearElemento("nombreAlbum", album.getNombreAlbum(), albumElem, document);
		    	crearElemento("numCanciones", Integer.toString(album.getNumCanciones()), albumElem, document);
		    	crearElemento("fechaPublicacion", Integer.toString(album.getFechaPublicacion()), albumElem, document);
		    	crearElemento("duracionMin", Integer.toString(album.getDuracionMin()), albumElem, document);
		    	
		    		
		    }
		    	
		    	GestorXML.saveDocument(document, ALBUMES);
		        
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			

}
