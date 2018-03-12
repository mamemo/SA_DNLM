package SA.pruebas;

public class Pruebas{
	
	public static void main(String[] args){
		Filtrar_Dataset prueba1 = new Filtrar_Dataset();
		//prueba1.correrPrueba(21, 3, 206, 8.500809);
		//prueba1.correrUna(21,3,1,1.0,"mcf-z-stacks-03212011_b02_s2_original.tif","mcf-z-stacks-03212_b02_s2.png" );
		//prueba1.correrUna(5,3,23,25.448198,"mcf-z-stacks-03212011_b06_s1_original.tif");
		//Filtrar_Otsu prueba = new Filtrar_Otsu();
		//prueba.correrPrueba(9,3,14,26.874018);
		//Comparar_Dice prueba2 = new Comparar_Dice();
		//prueba2.correrPrueba();
		//Filtrar_Dice prueba = new Filtrar_Dice();
		//prueba.correrPrueba(15, 3, 112, 4.7291594);
		
		prueba1.transformar("mcf-z-stacks-03212011_a13_s2_original.tif");
	}
	
}
