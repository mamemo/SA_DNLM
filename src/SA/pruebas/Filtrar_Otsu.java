package SA.pruebas;

import java.io.File;
import java.util.ArrayList;
import org.opencv.core.Mat;
import SA.utilities.filter.DnlmFilter;
import SA.utilities.images.ImageHandler;
import SA.utilities.segmentation.Thresholder;

public class Filtrar_Otsu{
	
	public void correrPrueba(int w, int w_n, int sigma_r, double lambda){
		String absdir = "src/SA/utilities/images/original/";
	    ArrayList<String> Dataset = nombreImagenes(absdir);
	    ImageHandler imageHandler = new ImageHandler();
	    
	    for(String imagen : Dataset){
	    	Mat original = imageHandler.leerImagenGrises(absdir + imagen);
		    // filter the image with DNLM-IDFT
		    Mat imagenFiltrada = filtrar(original, w, w_n, sigma_r, lambda);
		    Thresholder.applyOtsuThreshold(imagenFiltrada);
		    imageHandler.guardarImagen("C:/Users/mauro/Desktop/Imagenes Preprocesadas", 
		    		imagen.substring(0, imagen.length() - 4)+"_DNLM_SA_OTSU", "png", imagenFiltrada);
		    System.out.println("Listo "+imagen);
	    }
	    
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
