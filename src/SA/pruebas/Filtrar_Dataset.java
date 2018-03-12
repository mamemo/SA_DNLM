package SA.pruebas;

import java.io.File;
import java.util.ArrayList;
import org.opencv.core.Mat;
import SA.utilities.filter.DnlmFilter;
import SA.utilities.images.ImageHandler;

public class Filtrar_Dataset{
	
	public void correrPrueba(int w, int w_n, int sigma_r, double lambda){
		String absdir = "src/SA/utilities/images/original/";
	    ArrayList<String> Dataset = nombreImagenes(absdir);
	    ImageHandler imageHandler = new ImageHandler();
	    
	    for(String imagen : Dataset){
	    	Mat original = imageHandler.leerImagenGrises(absdir + imagen);
		    // filter the image with DNLM-IDFT
		    Mat imagenFiltrada = filtrar(original, w, w_n, sigma_r, lambda);
		    imageHandler.guardarImagen("C:/Users/mauro/Desktop/Imagenes Preprocesadas", 
		    		imagen.substring(0, imagen.length() - 4)+"_DNLM_SA", "png", imagenFiltrada);
		    System.out.println("Listo "+imagen);
	    }
	    
	}
	
	public void correrUna(int w, int w_n, int sigma_r, double lambda, String nombre){
		String absdir = "src/SA/utilities/images/original/";
	    ImageHandler imageHandler = new ImageHandler();

	    Mat original = imageHandler.leerImagenGrises(absdir + nombre);
		    // filter the image with DNLM-IDFT
	    Mat imagenFiltrada = filtrar(original, w, w_n, sigma_r, lambda);
		imageHandler.guardarImagen("C:/Users/mauro/Desktop/Imagenes Preprocesadas", 
		 		nombre.substring(0, nombre.length() - 4)+"_DNLM", "png", imagenFiltrada);
		System.out.println("Listo "+nombre);
	    
	}
	
	public void transformar(String nombre){
		String absdir = "src/SA/utilities/images/original/";
	    ImageHandler imageHandler = new ImageHandler();

	    Mat original = imageHandler.leerImagenGrises(absdir + nombre);
	    imageHandler.guardarImagen("C:/Users/mauro/Desktop/Imagenes Preprocesadas", 
		 		nombre.substring(0, nombre.length() - 4)+"_original", "png",original);
		System.out.println("Listo "+nombre);
	    
	}
	
	
	private ArrayList<String> nombreImagenes(String ruta){
		File folder = new File(ruta);
	    File[] listOfFiles = folder.listFiles();
	    ArrayList<String> archivos = new ArrayList<String>();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    		archivos.add(listOfFiles[i].getName());
	    	} 
	    }
	    return archivos;
	}
	
	private Mat filtrar(Mat original,int  w, int w_n, int sigma_r, double lambda){
		DnlmFilter filter = new DnlmFilter();
		Mat filteredImage = filter.filter(original, w, w_n, sigma_r, lambda);
		return filteredImage;
	}
}
