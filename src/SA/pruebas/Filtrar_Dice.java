package SA.pruebas;

import java.io.File;
import java.util.ArrayList;

import org.opencv.core.Mat;

import SA.utilities.filter.DnlmFilter;
import SA.utilities.images.ImageHandler;
import SA.utilities.scoring.Dice;
import SA.utilities.segmentation.Thresholder;

public class Filtrar_Dice {

	public void correrPrueba(int w, int w_n, int sigma_r, double lambda) {
		String absdirO = "src/SA/utilities/images/original/";
	    ArrayList<String> DatasetO = nombreImagenes(absdirO);
	    String absdirG = "src/SA/utilities/images/groundtruth/";
	    ArrayList<String> DatasetG = nombreImagenes(absdirG);
	    
	    ImageHandler imageHandler = new ImageHandler();
	    
	    for(int i =0; i<DatasetO.size(); i++){
	    	Mat original = imageHandler.leerImagenGrises(absdirO + DatasetO.get(i));
	    	Mat gt = imageHandler.leerImagenGrises(absdirG + DatasetG.get(i));
		    // filter the image with DNLM-IDFT
		    Mat filteredImage = filtrar(original, w, w_n, sigma_r, lambda);
		 // cut black borders and apply same transformation to groundtruth
			int snipping = w + w_n;
			filteredImage = filteredImage.submat(snipping, filteredImage.rows() - snipping - 2, snipping,
					filteredImage.cols() - snipping - 2);
			gt = gt.submat(snipping, gt.rows() - snipping - 2, snipping,
					gt.cols() - snipping - 2);
		    Thresholder.applyOtsuThreshold(filteredImage);
		    double calculo = Dice.calculateDice(filteredImage, gt);
		    System.out.println(Integer.toString(i) + ": " + Double.toString(calculo));
		    //imageHandler.guardarImagen("C:/Users/mauro/Desktop/Imagenes Preprocesadas", 
		    //		imagen.substring(0, imagen.length() - 4)+"_DNLM_SA_OTSU", "png", imagenFiltrada);
		    //System.out.println("Listo "+imagen);
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
