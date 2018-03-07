package SA;

import java.util.Random;

/**
 * Clase que contiene el individuo a crearse para calibrar los parametros. Fecha
 * de creacion: 30/1/2018
 * 
 * @author Mauro Mendez.
 *
 */

public class ParamIndividual implements Comparable<ParamIndividual> {

	private int w;
	private int w_n;
	private int sigma_r;
	private float lambda;
	private double fitness;
	private final Random random = new Random();

	// Crea un Parametros Aleatorios
	public ParamIndividual(SASettings settings) {
		this.setFitness(0);
		this.setW(getRandomIntegerBetween(settings.getLowerW(), settings.getUpperW()));
		this.w = (this.getW() % 2 == 0) ? this.getW() + 1 : this.getW();
		this.setW_n(getRandomIntegerBetween(settings.getLowerWn(), settings.getUpperWn()));
		this.w_n = (this.getW_n() % 2 == 0) ? this.getW_n() + 1 : this.getW_n();
		this.setSigma_r(getRandomIntegerBetween(settings.getLowerSigmaR(), settings.getUpperSigmaR()));
		this.setLambda(getRandomFloatBetween(settings.getLowerLambda(), settings.getUpperLambda()));
	}

	public ParamIndividual(SASettings settings, ParamIndividual p) {
		this.setFitness(0);
		this.setW_n(p.getW_n());
		int opcion = getRandomIntegerBetween(1, 3);
		switch (opcion) {
		case 1:
			this.setW(valorCercanoPorc(settings.getCambioW(), p.getW(), settings.getLowerW(), settings.getUpperW()));
			this.w = (this.getW() % 2 == 0) ? this.getW() + 1 : this.getW();
			this.setSigma_r(p.getSigma_r());
			this.setLambda(p.getLambda());
			break;
		case 2:
			this.setSigma_r(valorCercanoPorc(settings.getCambioSigmaR(), p.getSigma_r(), settings.getLowerSigmaR(),
					settings.getUpperSigmaR()));
			this.setW(p.getW());
			this.setLambda(p.getLambda());
			break;
		case 3:
			this.setLambda(valorCercanoPorc(settings.getCambioLambda(), p.getLambda(), settings.getLowerLambda(),
					settings.getUpperLambda()));
			this.setW(p.getW());
			this.setSigma_r(p.getSigma_r());
			break;
		}
	}

	private int valorCercanoPorc(int PorcCambio, int valor, int minimo, int maximo) {
		int signo = getRandomIntegerBetween(-1, 1);
		while (signo == 0) {
			signo = getRandomIntegerBetween(-1, 1);
		}
		signo = (valor==minimo)? 1 : signo;
		signo = (valor==maximo)? -1 : signo;
		float normalizado =(float) (valor - minimo) / (maximo - minimo);
		float cambio = getRandomIntegerBetween(1, PorcCambio)/(float)100;
		normalizado = normalizado + signo * cambio;
		int nuevo_v = (int) ((maximo - minimo) * normalizado + minimo);
		nuevo_v = (nuevo_v < minimo) ? minimo : nuevo_v;
		nuevo_v = (nuevo_v > maximo) ? maximo : nuevo_v;
		return nuevo_v;
	}
	
	private float valorCercanoPorc(float PorcCambio, float valor, float minimo, float maximo) {
		int signo = getRandomIntegerBetween(-1, 1);
		while (signo == 0) {
			signo = getRandomIntegerBetween(-1, 1);
		}
		signo = (valor==minimo)? 1 : signo;
		signo = (valor==maximo)? -1 : signo;
		float normalizado = (valor - minimo) / (maximo - minimo);
		normalizado = normalizado + signo * (getRandomFloatBetween(1, PorcCambio)/100);
		float nuevo_v = (maximo - minimo) * normalizado + minimo;
		nuevo_v = (nuevo_v < minimo) ? minimo : nuevo_v;
		nuevo_v = (nuevo_v > maximo) ? maximo : nuevo_v;
		return nuevo_v;
	}
	
	
	private int valorCercano(int cambio, int valor, int minimo, int maximo) {
		int alteracion = getRandomIntegerBetween(-cambio, cambio);
		while (alteracion == 0) {
			alteracion = getRandomIntegerBetween(-cambio, cambio);
		}
		int nuevo_v = alteracion + valor;
		nuevo_v = (nuevo_v < minimo) ? minimo : nuevo_v;
		nuevo_v = (nuevo_v > maximo) ? maximo : nuevo_v;
		return nuevo_v;
	}

	private float valorCercano(float cambio, float valor, float minimo, float maximo) {
		float alteracion = getRandomFloatBetween(-cambio, cambio);
		while (alteracion == 0.0) {
			alteracion = getRandomFloatBetween(-cambio, cambio);
		}
		float nuevo_v = alteracion + valor;
		nuevo_v = (nuevo_v < minimo) ? minimo : nuevo_v;
		nuevo_v = (nuevo_v > maximo) ? maximo : nuevo_v;
		return nuevo_v;
	}

	private int getRandomIntegerBetween(int lower, int upper) {
		return random.nextInt(upper - lower + 1) + lower;
	}

	private float getRandomFloatBetween(float lower, float upper) {
		return random.nextFloat() * (upper - lower + 1) + lower;
	}
	
	public void copiar(ParamIndividual p){
		this.setW(p.getW());
		this.setW_n(p.getW_n());
		this.setSigma_r(p.getSigma_r());
		this.setLambda(p.getLambda());
		this.setFitness(p.getFitness());
	}

	/**
	 * Implement Comparator. Individuals with a higher fitness will be ordered
	 * higher.
	 */

	@Override
	public int compareTo(ParamIndividual other) {
		return Double.compare(this.fitness, other.fitness);
	}

	@Override
	public String toString() {
		return String.valueOf(this.getW()) + "," + String.valueOf(this.getW_n()) + ","
				+ String.valueOf(this.getSigma_r()) + "," + String.valueOf(this.getLambda()) + " | "
				+ String.valueOf(this.getFitness());

	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getW_n() {
		return w_n;
	}

	public void setW_n(int w_n) {
		this.w_n = w_n;
	}

	public int getSigma_r() {
		return sigma_r;
	}

	public void setSigma_r(int sigma_r) {
		this.sigma_r = sigma_r;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public float getLambda() {
		return lambda;
	}

	public void setLambda(float lambda) {
		this.lambda = lambda;
	}

}
