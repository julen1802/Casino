package casino;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
public class Baraja {

		// atributos
		private ArrayList<Carta> barajaCartas;
		private static Baraja miBaraja = null;
		// constructora

		public Baraja()
		{
			barajaCartas= new ArrayList<Carta>();
		}
		// otros métodos
		public static Baraja getBaraja()
		{
			if(miBaraja == null){
				miBaraja = new Baraja();
	  		}
	  		return miBaraja;
		}
		public int obtenerNumCartas()
		{ 
			return barajaCartas.size();

		}
		public Iterator<Carta> getIterador()
		{
	 		return barajaCartas.iterator(); 

		}		
		public boolean esta(Carta pCarta)
		{
			if (pCarta!=null) {
				return barajaCartas.contains(pCarta);
			}
			else {
				return false;
			}
		}		
		public void crearBaraja()
		{
			Carta unaCarta = null;
			unaCarta = new Carta(1,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(2,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(3,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(4,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(5,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(6,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(7,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(8,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(9,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(10,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(11,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(12,"♠");
			anadirCarta(unaCarta);
			unaCarta = new Carta(13,"♠");
			anadirCarta(unaCarta);
			
			
			unaCarta = new Carta(1,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(2,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(3,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(4,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(5,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(6,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(7,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(8,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(9,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(10,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(11,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(12,"♣");
			anadirCarta(unaCarta);
			unaCarta = new Carta(13,"♣");
			anadirCarta(unaCarta);
			
			
			
			unaCarta = new Carta(1,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(2,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(3,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(4,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(5,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(6,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(7,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(8,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(9,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(10,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(11,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(12,"♥");
			anadirCarta(unaCarta);
			unaCarta = new Carta(13,"♥");
			anadirCarta(unaCarta);
			
			
			unaCarta = new Carta(1,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(2,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(3,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(4,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(5,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(6,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(7,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(8,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(9,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(10,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(11,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(12,"♦");
			anadirCarta(unaCarta);
			unaCarta = new Carta(13,"♦");
			anadirCarta(unaCarta);
			
		}	
		public void anadirCarta(Carta pCarta)
		{
			if (pCarta != null){
				barajaCartas.add(pCarta);
			}
		}
		public void eliminarCarta(Carta pCarta)
		{
			barajaCartas.remove(pCarta);
		}
		public Carta repartirCarta(Carta pCarta)
		{
			miBaraja.eliminarCarta(pCarta);
			return pCarta;
		}
		public void quemarCarta(Carta pCarta){
			miBaraja.eliminarCarta(pCarta);
		}
		public Carta cartaAlAzar(){
			Random randomGenerator = new Random();
	        int ind = randomGenerator.nextInt(barajaCartas.size());
	        Carta pCarta = barajaCartas.get(ind);
	        return pCarta;
			
			
		}
		public void resetear(){
			miBaraja = null;
			miBaraja = new Baraja();
			miBaraja.crearBaraja();
		}
}
