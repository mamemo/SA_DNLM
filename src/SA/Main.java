package SA;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;

import org.opencv.core.Mat;

import SA.utilities.images.ImageHandler;

/**
 * Main.java Clase encargada de ejecutar el algoritmo de Simulated Annealing.
 * Fecha de creacion: 31/1/2018
 * 
 * @author Mauro Mendez
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		SASettings settings = new SASettings();
		ImageHandler imageHandler = new ImageHandler();
		SA sa = new SA(settings);

		settings.setLowerW(1);
		settings.setUpperW(21);
		settings.setCambioW(30);

		settings.setLowerWn(3);
		settings.setUpperWn(3);
		settings.setCambioWn(0);

		settings.setLowerSigmaR(1);
		settings.setUpperSigmaR(500);
		settings.setCambioSigmaR(30);

		settings.setLowerLambda(1);
		settings.setUpperLambda(30);
		settings.setCambioLambda(30);

		String dirOriginal = "src/SA/utilities/images/original/";
		String dirGt = "src/SA/utilities/images/groundtruth/";

		File folder = new File(dirOriginal);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				
				Mat imagen = imageHandler.leerImagenGrises(dirOriginal + file.getName());
				settings.addToOriginalImages(imagen);
			}
		}

		File folderGT = new File(dirGt);
		File[] listOfFilesGT = folderGT.listFiles();

		for (File file : listOfFilesGT) {
			if (file.isFile()) {
				Mat imagen = imageHandler.leerImagenGrises(dirGt + file.getName());
				settings.addToGroundtruthImages(imagen);
			}
		}

		String absdir = "";
		if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
			absdir = "/home/jdnlm/DnlmTests/SA_DNLM/";
		}

		settings.setTemperatura(1);
		settings.setRepeticionesSinEnfriar(3);
		int cantidad_repeticiones = 50;
		//settings.setEnfriamiento(Enfriamiento(settings.getTemperatura(), cantidad_repeticiones));
		settings.setEnfriamiento(0.9099999999999999);
		settings.setCorridas(10);
		String nombre = "SA_" + String.valueOf(cantidad_repeticiones) + ".txt";
		File f = new File(nombre);
		f.createNewFile();
		settings.setNombreArchivo(nombre);

		sa.runOptimization();
	}

	private static double Enfriamiento(double T, int cantidad_deseada) {
		int cont = 0;
		double alpha = 0.99;
		double temp;
		while (cont != cantidad_deseada) {
			temp = T;
			cont = 0;
			while (temp > 0.01) {
				cont++;
				temp *= alpha;
			}
			alpha -= 0.01;
		}
		return alpha + 0.01;
	}
}
