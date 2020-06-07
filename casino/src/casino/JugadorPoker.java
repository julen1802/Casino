package casino;

public abstract class JugadorPoker extends Usuario{
	private ListaCartas lista = new ListaCartas();
	private boolean activo = true;
	public JugadorPoker(String pNombre, int pIdUsuario, int pDinero) {
		super(pNombre, pIdUsuario, pDinero);
		lista = new ListaCartas();
	}
	public ListaCartas getListaCartas(){
		return lista;
	}
	public boolean getActivo(){
		return activo;
	}
	public void setActivo(boolean newActivo){
		activo = newActivo;
	}
	
	public abstract int decidirJugada(boolean rondaIgualacion, int apuestaAIgualar);
	public abstract int apostar();
	public abstract int decidirJugadaBlackjack();
	public boolean igualar(int apuesta){
		System.out.println("-------------------------------------------------");

		if(getDinero() >= apuesta){
			restarDinero(apuesta);
			System.out.println("El jugador " +getNombre() +" ha igualado la apuesta.");
			System.out.println("-------------------------------------------------");
			System.out.println("                                                 ");
			return true;
		}
		else {
			System.out.println("El jugador " +getNombre() +" se ha retirado.");
			System.out.println("-------------------------------------------------");
			System.out.println("                                                 ");
			return false;
		}
	}
	public void pasar(){
		System.out.println("-------------------------------------------------");
		System.out.println("El jugador " +getNombre() +" ha pasado.");
		System.out.println("-------------------------------------------------");
		System.out.println("                                                 ");
	}
	public void resetear() {
		lista = null;
		lista = new ListaCartas();		
	}
	public void imprimirCartas(){
		System.out.println("--------------------------");
		System.out.println("Estas son sus cartas: " + getNombre());
		lista.imprimir();
		System.out.println("--------------------------");
		System.out.println("                          ");
		
	}
	
}
