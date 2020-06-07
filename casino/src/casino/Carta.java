package casino;

public class Carta {
	//atributos
	private int numero;
	private String simbolo;
	//constructora
	 public Carta (int pNumero, String pSimbolo) {
		 numero=pNumero;
		 simbolo=pSimbolo;
	 }
	 
		// otros métodos
public int getNumero(){
	return numero;
}
public String getSimbolo(){
	return simbolo;
}
public boolean tieneMismoSimbolo(Carta pCarta) {
	if (pCarta.simbolo==simbolo) {
		return true;
	}
	else {
		return false;
	}
}
public boolean tieneMismoNumero(Carta pCarta) {
	if (pCarta.numero==numero) {
		return true;
	}
	else {
		return false;
	}
}
public boolean estanEnEscalera(Carta pCarta) {
	if ((pCarta.numero-1==numero)&&(pCarta.numero==numero-1)) {
		return true;
	}
	else {
		return false;
	}
}
public int distanciaEntreCartas(Carta pCarta){
	int distancia = 0;
	if (numero>pCarta.getNumero()){
		distancia = numero - pCarta.getNumero();
	}
	else{
		distancia = pCarta.getNumero() - numero;
	}
	return distancia;
	
}
public void imprimir(){
	if (this.numero==13){
		System.out.println("K de " +(this.simbolo));
	}
	else if (this.numero==12){
		System.out.println("Q de " +(this.simbolo));
	}
	else if (this.numero==11){
		System.out.println("J de " +(this.simbolo));
	}
	else if (this.numero==1){
		System.out.println("A de " +(this.simbolo));
	}
	else{
		System.out.println((this.numero)+" de "+(this.simbolo));
	}
	}
}
