package SA;

//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Random;
import SA.utilities.fitness.FitnessEval;

public class SA {

	private final SASettings settings;
	private final Random random;
	private Hashtable<String, Double> params;
	private final FitnessEval fitEval;

	public SA(SASettings settings) {
		this.settings = settings;
		this.random = new Random();
		this.params = new Hashtable<String, Double>();
		this.fitEval = new FitnessEval();
	}

	public void runOptimization() {

		reporte();

		for (int corr = 1; corr <= settings.getCorridas(); corr++) {
			System.out.println("\n###########################################\r\n" + "Corrida: " + String.valueOf(corr) + " Empieza: " + LocalDateTime.now());

			ParamIndividual mejorGlobal = new ParamIndividual(this.settings);
			ParamIndividual mejor = new ParamIndividual(this.settings);
			ParamIndividual nuevaSolucion;
			mejor.setFitness(0.0);
			double T = settings.getTemperatura();

			while (T > 0.01) {
				for (int i = 0; i < settings.getRepeticionesSinEnfriar(); i++) {
					nuevaSolucion = new ParamIndividual(settings, mejor);
					fitEval.calcularFitness(settings, nuevaSolucion, params);
					if (acceptanceProbability(mejor.getFitness(), nuevaSolucion.getFitness(), T) >= random
							.nextFloat()) {
						mejor.copiar(nuevaSolucion);
						if (mejor.getFitness() > mejorGlobal.getFitness())
							mejorGlobal.copiar(mejor);
					}
					nuevaSolucion = null;
				}

				System.out.println("T: " + String.valueOf(T) + " Mejor: " + mejor.toString() + " Tiempo: "
							+ LocalDateTime.now());
				
				T *= settings.getEnfriamiento();
			}

			System.out.println("Mejor Global: " + mejorGlobal.toString());
		}

	}

	// Calculate the acceptance probability
	private double acceptanceProbability(double fitness, double newfitness, double temperature) {
		// If the new solution is better, accept it
		if (newfitness > fitness)
			return 1.0;
		// If the new solution is worse, calculate an acceptance probability
		return Math.exp((newfitness - fitness) / temperature);
	}

	private void reporte() {
//		String r = "\r\n\r\nMinimo W: " + String.valueOf(settings.getLowerW());
//		r += "\r\n" + "Maximo W: " + String.valueOf(settings.getUpperW());
//		r += "\r\n" + "Cambio W (Porc): " + String.valueOf(settings.getCambioW());
//		r += "\r\n" + "Minimo W_n: " + String.valueOf(settings.getLowerWn());
//		r += "\r\n" + "Maximo W_n: " + String.valueOf(settings.getUpperWn());
//		r += "\r\n" + "Cambio W_n (Porc): " + String.valueOf(settings.getCambioWn());
//		r += "\r\n" + "Minimo Sigma_R: " + String.valueOf(settings.getLowerSigmaR());
//		r += "\r\n" + "Maximo Sigma_R: " + String.valueOf(settings.getUpperSigmaR());
//		r += "\r\n" + "Cambio Sigma_R (Porc): " + String.valueOf(settings.getCambioSigmaR());
//		r += "\r\n" + "Minimo Lambda: " + String.valueOf(settings.getLowerLambda());
//		r += "\r\n" + "Maximo Lambda: " + String.valueOf(settings.getUpperLambda());
//		r += "\r\n" + "Cambio Lambda (Porc): " + String.valueOf(settings.getCambioLambda());
//		r += "\r\n" + "\nTemperatura Inicial: " + String.valueOf(settings.getTemperatura());
//		r += "\r\n" + "Repeticiones Sin Enfriar: " + String.valueOf(settings.getRepeticionesSinEnfriar());
//		r += "\r\n" + "Enfriamiento: " + String.valueOf(settings.getEnfriamiento());
//		r += "\r\n" + "Corridas: " + String.valueOf(settings.getCorridas());
//		return r;
		System.out.println("\n\nMinimo W: " + String.valueOf(settings.getLowerW()) +
			"\n" + "Maximo W: " + String.valueOf(settings.getUpperW()) +
		"\n" + "Cambio W (Porc): " + String.valueOf(settings.getCambioW()) +
		"\n" + "Minimo W_n: " + String.valueOf(settings.getLowerWn()) +
		"\n" + "Maximo W_n: " + String.valueOf(settings.getUpperWn()) +
		"\n" + "Cambio W_n (Porc): " + String.valueOf(settings.getCambioWn()) +
		"\n" + "Minimo Sigma_R: " + String.valueOf(settings.getLowerSigmaR()) +
		"\n" + "Maximo Sigma_R: " + String.valueOf(settings.getUpperSigmaR()) +
		"\n" + "Cambio Sigma_R (Porc): " + String.valueOf(settings.getCambioSigmaR()) +
		"\n" + "Minimo Lambda: " + String.valueOf(settings.getLowerLambda()) +
		"\n" +  "Maximo Lambda: " + String.valueOf(settings.getUpperLambda()) +
		"\n" + "Cambio Lambda (Porc): " + String.valueOf(settings.getCambioLambda())+
		"\n" + "\nTemperatura Inicial: " + String.valueOf(settings.getTemperatura())+
		"\n" + "Repeticiones Sin Enfriar: " + String.valueOf(settings.getRepeticionesSinEnfriar())+
		"\n" + "Enfriamiento: " + String.valueOf(settings.getEnfriamiento())+
		"\n" + "Corridas: " + String.valueOf(settings.getCorridas()));
	}
	
//	private void archivar (String mensaje){
//		try (FileWriter fw = new FileWriter(settings.getNombreArchivo(), true);
//				BufferedWriter bw = new BufferedWriter(fw);
//				PrintWriter out = new PrintWriter(bw)) {
//			out.println(mensaje);
//		} catch (IOException e) {
//			// exception handling left as an exercise for the reader
//		}
//	}
}
