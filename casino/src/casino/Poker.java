package casino;

import java.util.Random;

public class Poker {
	private static Poker miPoker = null;
	private ListaCartas cartasMesa = null;
	private int bote = 0;
	public Poker(){
		cartasMesa = new ListaCartas();
	}
	//metodos
	
	public static Poker getPoker(){
		if(miPoker == null){
			miPoker = new Poker();
	  	}
	  	return miPoker;
	}
	public int getBote(){
		return bote;
	}
	public void aumentarBote(int aumento){
		bote = bote + aumento;
	}
	public void resetearBote(){
		bote = 0;
	}
	public ListaCartas getCartasMesa(){
		return cartasMesa;
	}
	public String compararJugadas(String mano1, String mano2){
		
		int manoAlta1 = Integer.parseInt(mano1.substring(0, 1));
		int cAlta1 = Integer.parseInt(mano1.substring(2));
		int manoAlta2 = Integer.parseInt(mano2.substring(0, 1));
		int cAlta2 = Integer.parseInt(mano2.substring(2));
		if (esMejorMano(mano1, mano2)){
			manoAlta1 = manoAlta2;
			cAlta1 = cAlta2;
		}
		mano1 = (manoAlta1 + " " + cAlta1);
		return mano1;
	}
	public boolean esMejorMano(String mano1, String mano2){
		boolean cierto = false;
		int manoAlta1 = Integer.parseInt(mano1.substring(0, 1));
		int cAlta1 = Integer.parseInt(mano1.substring(2));
		int manoAlta2 = Integer.parseInt(mano2.substring(0, 1));
		int cAlta2 = Integer.parseInt(mano2.substring(2));
		if (manoAlta1 < manoAlta2){
			cierto = true;
		}
		else if (manoAlta1 == manoAlta2){
			if(cAlta1 != 1){
				if(cAlta2 != 1){
					if (cAlta1 < cAlta2){
						cierto = true;
					}
				}
				else{
					cierto = true;
				}
			}
			
		}
		return cierto;
	}
	public void anadirIaPoker(Usuario usu){
		Random randomGenerator = new Random();
        int ind = randomGenerator.nextInt(4);
        int din = usu.getDinero();
		IaPoker ia1 = new IaPoker("Mario", 23, (din+350));
		IaPoker ia2 = new IaPoker("Victoria", 55, (din+55));
		
		if(din>150.0){
			din = (din-70);
		}
		else {
			din = (din+190);
		}
		IaPoker ia3 = new IaPoker("David", 17, din);
		
        if (ind==1){
        	anadirhumanoide(usu);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia1);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia2);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia3);
        }
        else if (ind==2){
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia1);
        	anadirhumanoide(usu);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia2);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia3);
        }
        else if (ind==3){
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia1);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia2);
        	anadirhumanoide(usu);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia3);
        }
        else{
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia1);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia2);
        	ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(ia3);
        	anadirhumanoide(usu);
        }
 
	}
	public void anadirhumanoide(Usuario usu){
		
		Humanoide human= new Humanoide(usu.getNombre(), usu.getIdUsuario(), usu.getDinero());
		ListaJugadoresPoker.getListaJugadoresPoker().annadirJugadorPoker(human);
	}
	
	public void imprimirMesa(){
		System.out.println("-----------Mesa-----------");
		System.out.println("Num Jugadores: " + ListaJugadoresPoker.getListaJugadoresPoker().cuantosActivos());
		System.out.println("Bote :         " + (bote));
		System.out.println("--------------------------");
		System.out.println("                          ");
		
	}
	public void repartirMano(JugadorPoker jugador){
		jugador.getListaCartas().anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
		jugador.getListaCartas().anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
	}
	public void jugarPoker(Usuario usu){
		anadirIaPoker(usu);
		int turno = 1; //= Poker.getPoker().getListaActivosPorRonda().getIterador();
		boolean enJuego = true;
		JugadorPoker usuario = null;
		Baraja.getBaraja().crearBaraja();
		while (enJuego){
			if (turno==0){
				Poker.getPoker().cartasMesa.getListaCartas().clear();
				Baraja.getBaraja().resetear();	
				ListaJugadoresPoker.getListaJugadoresPoker().reiniciarJugadores();
			}
			else if (turno==1) {
				bote = 0;
				JugadorPoker jugador = null;
				jugador = ListaJugadoresPoker.getListaJugadoresPoker().getListaJugadores().get(0);
				System.out.println((jugador.getNombre() + ": " + (jugador.getDinero())));
				jugador = ListaJugadoresPoker.getListaJugadoresPoker().getListaJugadores().get(1);
				System.out.println((jugador.getNombre() + ": " + (jugador.getDinero())));
				jugador = ListaJugadoresPoker.getListaJugadoresPoker().getListaJugadores().get(2);
				System.out.println((jugador.getNombre() + ": " + (jugador.getDinero())));
				jugador = ListaJugadoresPoker.getListaJugadoresPoker().getListaJugadores().get(3);
				System.out.println((jugador.getNombre() + ": " + (jugador.getDinero())));
				System.out.println("                                                    ");
				usuario = ListaJugadoresPoker.getListaJugadoresPoker().repartirCartasJugadores();
				ListaJugadoresPoker.getListaJugadoresPoker().turno();
			}
			else if (turno==2) {
				Baraja.getBaraja().quemarCarta(Baraja.getBaraja().cartaAlAzar());
				cartasMesa.anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
				cartasMesa.anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
				cartasMesa.anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
				System.out.println("--------------------------");
				imprimirMesa();
				System.out.println("Las cartas situadas en la mesa son estas: ");
				cartasMesa.imprimir();
				usuario.imprimirCartas();
				System.out.println("--------------------------");
				System.out.println("                          ");
				ListaJugadoresPoker.getListaJugadoresPoker().turno();
				System.out.println(" Todas las jugadas realizadas ");
			}
			else if (turno==3) {
				cartasMesa.anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
				System.out.println("--------------------------");	
				imprimirMesa();
				System.out.println("Las cartas situadas en la mesa son estas: ");
				cartasMesa.imprimir();
				usuario.imprimirCartas();
				System.out.println("--------------------------");
				System.out.println("                          ");
				ListaJugadoresPoker.getListaJugadoresPoker().turno();
				System.out.println(" Todas las jugadas realizadas ");
			}
			else if (turno==4) {
				Baraja.getBaraja().quemarCarta(Baraja.getBaraja().cartaAlAzar());
				cartasMesa.anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
				System.out.println("--------------------------");	
				imprimirMesa();
				System.out.println("Las cartas situadas en la mesa son estas: ");
				cartasMesa.imprimir();
				usuario.imprimirCartas();
				System.out.println("--------------------------");
				System.out.println("                          ");
				ListaJugadoresPoker.getListaJugadoresPoker().turno();
				System.out.println(" Todas las jugadas realizadas ");
				imprimirMesa();

			}
			else if (turno==5) {
				JugadorPoker jugadorGanador = ListaJugadoresPoker.getListaJugadoresPoker().resolverPartida();
				if (jugadorGanador!=null){
					jugadorGanador.sumarDinero(bote);
					System.out.println("--------------------------");	
					System.out.println("Ha ganado el jugador : " + (jugadorGanador.getNombre()));
					System.out.println("Ha ganado un total de : " + (bote));
					System.out.println("--------------------------");	
					System.out.println("--    PARTIDA NUEVA   ---");
					System.out.println("                                                    ");

				}
				System.out.println("--    ¿Que deseas hacer?  ---");
				System.out.println("--  0: Salir    1: Seguir ---");
				int accion = Escaner.getEscaner().leerSalirJuego();
				if (accion == 0){
				//Aqui no se como hay que cerrar el programa. La ejecucion se cierra.
				}
				turno = -1;
			}
			if (turno < 5){
				turno = turno +1;
			}
		}
	}
}
