package SA;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import SA.utilities.segmentation.Thresholder;

/**
 * Clase para setear valores del algoritmo. Fecha de creacion: 31/1/2018
 * 
 * @author Mauro Mendez.
 *
 */

public class SASettings {

	/** Initial parameter ranges **/

	private int lowerSigmaR;
	private int upperSigmaR;
	private int cambioSigmaR;

	private int lowerW;
	private int upperW;
	private int cambioW;

	private int lowerWn;
	private int upperWn;
	private int cambioWn;
	
	private float lowerLambda;
    private float upperLambda;
    private float cambioLambda;
	
	private double temperatura;
	private int repeticionesSinEnfriar;
	private double enfriamiento;
	private int corridas;
	private String nombreArchivo;

	// images to filter and check against the ground truth
	private List<Mat> originalImages;

	// the ground truth
	private List<Mat> groundtruthImages;

	public SASettings() {
		originalImages = new ArrayList<Mat>();
		groundtruthImages = new ArrayList<Mat>();
	}

	public int getSampleCount() {
		return originalImages.size();
	}

	public Mat getOriginalImage(int index) {
		return originalImages.get(index);
	}

	public void setOriginalImages(List<Mat> oList) {
		this.originalImages = oList;
	}

	public void addToOriginalImages(Mat imagen) {
		originalImages.add(imagen);
	}

	public Mat getGroundtruthImage(int index) {
		return groundtruthImages.get(index);
	}

	public void setGroundtruthImages(List<Mat> gList) {
		this.groundtruthImages = gList;
	}

	public void addToGroundtruthImages(Mat imagen) {
		Thresholder.applyThreshold(imagen, 1);
		groundtruthImages.add(imagen);
	}

	public int getLowerSigmaR() {
		return lowerSigmaR;
	}

	public void setLowerSigmaR(int lowerSigmaR) {
		this.lowerSigmaR = lowerSigmaR;
	}

	public int getUpperSigmaR() {
		return upperSigmaR;
	}

	public void setUpperSigmaR(int upperSigmaR) {
		this.upperSigmaR = upperSigmaR;
	}

	public int getLowerW() {
		return lowerW;
	}

	public void setLowerW(int lowerW) {
		this.lowerW = lowerW;
	}

	public int getUpperW() {
		return upperW;
	}

	public void setUpperW(int upperW) {
		this.upperW = upperW;
	}

	public int getLowerWn() {
		return lowerWn;
	}

	public void setLowerWn(int lowerWn) {
		this.lowerWn = lowerWn;
	}

	public int getUpperWn() {
		return upperWn;
	}

	public void setUpperWn(int upperWn) {
		this.upperWn = upperWn;
	}

	public float getLowerLambda() {
		return lowerLambda;
	}

	public void setLowerLambda(float lowerLambda) {
		this.lowerLambda = lowerLambda;
	}

	public float getUpperLambda() {
		return upperLambda;
	}

	public void setUpperLambda(float upperLambda) {
		this.upperLambda = upperLambda;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public int getRepeticionesSinEnfriar() {
		return repeticionesSinEnfriar;
	}

	public void setRepeticionesSinEnfriar(int repeticionesSinEnfriar) {
		this.repeticionesSinEnfriar = repeticionesSinEnfriar;
	}

	public double getEnfriamiento() {
		return enfriamiento;
	}

	public void setEnfriamiento(double enfriamiento) {
		this.enfriamiento = enfriamiento;
	}

	public int getCambioSigmaR() {
		return cambioSigmaR;
	}

	public void setCambioSigmaR(int cambioSigmaR) {
		this.cambioSigmaR = cambioSigmaR;
	}

	public int getCambioW() {
		return cambioW;
	}

	public void setCambioW(int cambioW) {
		this.cambioW = cambioW;
	}

	public int getCambioWn() {
		return cambioWn;
	}

	public void setCambioWn(int cambioWn) {
		this.cambioWn = cambioWn;
	}

	public float getCambioLambda() {
		return cambioLambda;
	}

	public void setCambioLambda(float cambioLambda) {
		this.cambioLambda = cambioLambda;
	}

	public int getCorridas() {
		return corridas;
	}

	public void setCorridas(int corridas) {
		this.corridas = corridas;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}	
	
	
}