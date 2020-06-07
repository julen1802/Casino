package casino;

import java.util.Iterator;

public class Blackjack {
	
	private static Blackjack miBlackjack = null;
	private ListaCartas croupier = new ListaCartas();
	private Humanoide jugador1 = null;
	private static int blackjackPago = 3/2; 
	
	public Blackjack(){
		croupier = new ListaCartas();
		blackjackPago = 3/2; 
	}
	public void iniciarJugador(Usuario usu){
		jugador1 = (Humanoide) usu;
		jugarBlackjack(jugador1);
	}
	public static Blackjack getBlackjack(){
		if(miBlackjack == null){
			miBlackjack = new Blackjack();
	  	}
	  	return miBlackjack;
	}
	public int calcularCuenta(ListaCartas lista){
		int cuenta = 0;
		int ases = 0;
		Carta unaCarta = null;
		Iterator<Carta> iter = lista.getIterador();
		while(iter.hasNext()){
			unaCarta = iter.next();
			if (unaCarta.getNumero()!=1){
				if (unaCarta.getNumero()>=10){
					cuenta = cuenta + 10;
				}
				else {
					cuenta = cuenta + unaCarta.getNumero();
				}
			}
			else{
				ases = ases + 100;
				if (cuenta>10){
					cuenta = cuenta + 1;
				}
				else{
					cuenta = cuenta + 11;
				}
			}
		}
		cuenta = ases + cuenta;
		return cuenta;
			
	}
	public int calcularCuentaAlta(ListaCartas lista){
		int cuenta = 0;
		Carta unaCarta = null;
		Iterator<Carta> iter = lista.getIterador();
		while(iter.hasNext()){
			unaCarta = iter.next();
			if (unaCarta.getNumero()!=1){
				if (unaCarta.getNumero()>=10){
					cuenta = cuenta + 10;
				}
				else {
					cuenta = cuenta + unaCarta.getNumero();
				}
			}
			else{
				cuenta = cuenta + 11;
			}
		}
		return cuenta;		
	}
	public int calcularCuentaBaja(ListaCartas lista){
		int cuenta = 0;
		Carta unaCarta = null;
		Iterator<Carta> iter = lista.getIterador();
		while(iter.hasNext()){
			unaCarta = iter.next();
			if (unaCarta.getNumero()!=1){
				if (unaCarta.getNumero()>=10){
					cuenta = cuenta + 10;
				}
				else {
					cuenta = cuenta + unaCarta.getNumero();
				}
			}
			else{
				cuenta = cuenta + 1;
			}
		}
		return cuenta;		
	}
	public void jugadaPasada(int pCuenta){
		System.out.println("-------------------------------" );
		System.out.println("----Te has pasado: " + pCuenta +"--");
	}
	public void jugadaBlackjack(int pCuenta, int apuesta){
		System.out.println("-------------------------------" );
		System.out.println("---¡¡BlackJack!!:--- 21 -------");
		System.out.println("---El pago se realiza a 3/2----" );
		System.out.println("-------------------------------" );
		jugador1.sumarDinero(blackjackPago * apuesta);
	}
	public void imprimirCuentas(int pCuenta, int pCuentaCroupier){
		int asesMios = 0;
		int asesSuyos = 0;
		System.out.println("-------------------------------" );
		System.out.println("----Tus cartas son estas: -----");
		jugador1.getListaCartas().imprimir();
		while (pCuenta>100){
			pCuenta = pCuenta-100;
			asesMios = asesMios + 1;
		}
		if (asesMios == 2){
			System.out.println("----Y la cuenta es de: 2 / 12");
		}
		else if (asesMios == 1){
			System.out.println("----Y la cuenta es de: "+(pCuenta)+" / "+(pCuenta+10));
		}
		else{
			System.out.println("----Y la cuenta es de: " + (pCuenta));
		}
		System.out.println("-------------------------------" );
		
		System.out.println("----Cartas del Croupier: ------" );
		croupier.imprimir();
		while (pCuentaCroupier>100){
			pCuentaCroupier = pCuentaCroupier-100;
			asesSuyos = asesSuyos + 1;
		}
		if (asesSuyos == 1){
			System.out.println("--Y la cuenta es de: 1 + x / 11 + x");
		}
		else{
			System.out.println("--Y la cuenta es de:   x + " + (pCuentaCroupier));
		}
		System.out.println("-------------------------------" );
		System.out.println("                               " );
	}
	public boolean doblarApuesta(int pApuesta){
		boolean doblado1;
		if (jugador1.getDinero()>pApuesta){
			System.out.println("--------------------------------" );
			System.out.println("--- Su apuesta se ha doblada ---");
			System.out.println("--------------------------------" );
			System.out.println("                                " );
			doblado1 = true;
			jugador1.restarDinero(pApuesta);
		}
		else {
			System.out.println("--------------------------------" );
			System.out.println("- No tiene suficientes fondos --");
			System.out.println("--------------------------------" );
			System.out.println("                               " );
			doblado1 = false;
		}
		return doblado1;
	}
	public void pedirCarta(ListaCartas lista,int pCuenta, int pCuentaCroupier){
		
		lista.anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
		System.out.println("--------------------------------");
		System.out.println("------- Has pedido carta -------");
		System.out.println("--------------------------------");
		System.out.println("                                ");
		imprimirCuentas(pCuenta, pCuentaCroupier);
	}
	public void plantarse(int cuenta){
		System.out.println("--------------------------------");
		System.out.println("-- Te has plantado con: " + (cuenta) + " --");
		System.out.println("--------------------------------");
		System.out.println("                                ");
	}
	public void decidirJugada(){
		System.out.println("---------------------------------");
		System.out.println("------ ¿ Que desea hacer ? ------");
		System.out.println(" 0: Plantarse   1: Pedir Carta   2: Doblar");
		System.out.println("---------------------------------");
		System.out.println("                                 "); 
	}
	public void jugarBlackjack(Usuario jugador){
		int apuesta = 0;
		int cuentaLocal = 0;
		int cuentaCroupier = 0;
		int accion = 0;
		boolean doblado = false;
		boolean cRetirado = false;
		boolean jRetirado = false;
		boolean seguirJugando = true;
		jugador1= new Humanoide(jugador.getNombre(), jugador.getIdUsuario(), jugador.getDinero());
		Baraja.getBaraja().crearBaraja();
		while (seguirJugando){
			Baraja.getBaraja().crearBaraja();
			croupier.restaurar();
			jugador1.getListaCartas().restaurar();
			doblado = false;
			cRetirado = false;
			jRetirado = false;
			seguirJugando = true;
			System.out.println("-------------------------------" );
			System.out.println(" Buenas " + (jugador1.getNombre()));
			System.out.println(" Tienes: " + (jugador1.getDinero()));
			System.out.println(" Introduce lo que desee apostar ");
			System.out.println(" 1: 4€    2: 8€   3: 12€  4:  16€ ");
			System.out.println("-------------------------------" );
			System.out.println("                               " );
			apuesta = Escaner.getEscaner().leerEscaner4Opciones() * 4;
			jugador1.restarDinero(apuesta);
			jugador1.getListaCartas().anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
			jugador1.getListaCartas().anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
			cuentaLocal = calcularCuenta(jugador1.getListaCartas());
			croupier.anadirCarta(Baraja.getBaraja().repartirCarta(Baraja.getBaraja().cartaAlAzar()));
			cuentaCroupier = calcularCuentaBaja(croupier);
			cuentaLocal = calcularCuentaBaja(jugador1.getListaCartas());
			while (!jRetirado){
				cuentaLocal = calcularCuenta(jugador1.getListaCartas());
				imprimirCuentas(cuentaLocal, cuentaCroupier);
				if (cuentaLocal>21){
					jugadaPasada(cuentaLocal);
					jRetirado = true;
				}
				else if (cuentaLocal == 21){
					jugadaBlackjack(cuentaLocal, apuesta);
					jRetirado = true;
					
				}
				else if (cuentaLocal != 21){
					decidirJugada();
					accion = jugador1.decidirJugadaBlackjack();
					if (accion == 0){
						plantarse(cuentaLocal);
						jRetirado = true;
					}
					else if (accion == 1){
						pedirCarta(jugador1.getListaCartas(), cuentaLocal, cuentaCroupier);

					}
					else {
						doblado = doblarApuesta(apuesta);
						if (doblado){
							jugador1.restarDinero(apuesta);
							apuesta = apuesta + apuesta;
							pedirCarta(jugador1.getListaCartas(), cuentaLocal, cuentaCroupier);
							plantarse(cuentaLocal);
							jRetirado = true;
						}

					}
					// 0 Plantarse
					// 1 Pedir Carta
					// 2 Doblar Apuesta
				}
				cuentaLocal = calcularCuentaBaja(jugador1.getListaCartas());
				if(cuentaLocal>21){
					jRetirado = true;
				}
			}
			// Turno de Juego del Croupier
			
			while(!cRetirado){
				cuentaCroupier = calcularCuentaAlta(croupier);
				if (cuentaCroupier > 21){
					cuentaCroupier = calcularCuentaBaja(croupier);
					if (cuentaCroupier >= 16){
						cRetirado = true;	
					}
					else{
						pedirCarta(croupier, cuentaLocal, cuentaCroupier);
					}
				}
				else{
					if (cuentaCroupier >= 16){
						cRetirado = true;	
					}
					else{
						pedirCarta(croupier, cuentaLocal, cuentaCroupier);
					}
				}
			}
			
			
			cuentaCroupier = calcularCuentaAlta(croupier);
			
			if (cuentaCroupier > 21){
				cuentaCroupier = calcularCuentaBaja(croupier);
				if (cuentaCroupier < 16){
					cRetirado = false;
				}
			}
			else{
				cRetirado = false;
			}
			
			
			cuentaLocal = calcularCuentaAlta(jugador1.getListaCartas());
			if (cuentaLocal > 21){
				cuentaLocal = calcularCuentaBaja(jugador1.getListaCartas());
				if (cuentaLocal <= 21){
					jRetirado = false;
				}
			}
			else{
				jRetirado = false;
			}
			if (cuentaLocal<=21){
				if(cuentaCroupier<=21){
					if(cuentaCroupier > cuentaLocal){
						System.out.println("--------------------------------" );
						System.out.println("--- El Croupier te ha ganado con -" +(cuentaCroupier));
						System.out.println("--- Has perdido " + (apuesta) + " € --");
						System.out.println("-------------------------------" );
						System.out.println("                               " );
						apuesta = 0;
					}
					else if (cuentaCroupier == cuentaLocal){
						jugador1.sumarDinero(apuesta);
						System.out.println("--------------------------------" );
						System.out.println("----- Ufssss!! Empatasteis,-----");
						System.out.println("-- Se te devolvera la apuesta --");
						System.out.println("--------------------------------" );
						System.out.println("                                " );
					}
					else{
						jugador1.sumarDinero(2 * apuesta);
						System.out.println("-------------------------------" );
						System.out.println("--- Has ganado al Croupier! ---");
						System.out.println("--- Has ganado " + (apuesta) + " € --");
						System.out.println("-------------------------------" );
						System.out.println("                               " );
					}
				}
				else{
					jugador1.sumarDinero(2 * apuesta);
					System.out.println("-------------------------------" );
					System.out.println("- El Croupier se ha pasado con -" +(cuentaCroupier));
					System.out.println("--- Has ganado " + (apuesta) + " € --");
					System.out.println("-------------------------------" );
					System.out.println("                               " );
				}
				
			}
			else{
				System.out.println("-------------------------------" );
				System.out.println("----- Te has pasado de 21 -----");
				System.out.println("--- Has perdido " + (apuesta) + " € --");
				System.out.println("-------------------------------" );
				System.out.println("                               " );
				apuesta = 0;
			}
			apuesta = 0;
			accion = Escaner.getEscaner().leerSalirJuego();
			if (accion == 1){
				
			}
		}
	}
}