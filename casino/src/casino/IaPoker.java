package casino;
import java.util.Iterator;
import java.util.Random;
public class IaPoker extends JugadorPoker {

	
	public IaPoker(String pNombre, int pIdUsuario, int pDinero) {
		super(pNombre, pIdUsuario, pDinero);
		// TODO Auto-generated constructor stub
		
	}
	public int decidirJugadaBlackjack(){
		int cero = 0;
		return cero;
	}
	public int decidirJugada(boolean rondaIgualacion, int apuestaAIgualar){
		// Jugada: 0-> Retirarse
		//         1 -> Igualar
		//         2-> Pasar
		//         2-> Apostar
		int jugada=0;
		int nCartas = Poker.getPoker().getCartasMesa().obtenerNumCartas();
		int probabilidad = 0;
		int probabilidadEnContra = 0;
		int bote = Poker.getPoker().getBote();
		int jugadoresActivos = ListaJugadoresPoker.getListaJugadoresPoker().cuantosActivos();
		if (jugadoresActivos == 1){
			jugada = 2;
		}
		else{
			if (nCartas == 0){
				probabilidad = probabilidadDos();
			}
			else{
				
				String mJugada = getListaCartas().mejorJugada();
				probabilidad = calcularProbabilidad(mJugada);
				
				// Influencia del numero de jugadores en juego
				probabilidadEnContra = (nCartas*jugadoresActivos);
				if (probabilidadEnContra>12){
					probabilidad = probabilidad - 3;
				}
				else {
					probabilidad = probabilidad + 2;
				}
				if (bote<100){
					probabilidad = probabilidad + 1;
				}
				else if (bote<150){
					probabilidad = probabilidad + 5;
				}
				else if (bote>=150){
					probabilidad = probabilidad + 10;
				}
			}
			
			// Influencia del bote en el resultado

			if (rondaIgualacion){
				// Influencia de la apuesta que se debe igualar
				int apuesta = (int)apuestaAIgualar;
				probabilidad = probabilidad - 3;
				if (apuesta == 10){
					probabilidad = probabilidad - 1;
				}
				else if (apuesta == 20){
					probabilidad = probabilidad - 3;
				}
				else if (apuesta == 50){
					probabilidad = probabilidad - 6;
				}
				if (probabilidad<=0){
					jugada = 0;
				}
				else{
					jugada = 1;
				}
			}
			else{
				if (probabilidad<=0){
					jugada = 0;
				}
				else if (probabilidad<15){
					jugada = 2;
				}
				else if (probabilidad>=15){
					jugada = 3;
				}
			}
		}
		
		return jugada;
	}
	public int apostar(){
		int apuesta = 0;
		Random randomGenerator = new Random();
        int ind = randomGenerator.nextInt(3);
        if (ind == 1){
        	ind = 5;
        }
        else if (ind == 2){
        	ind = 10;
        }
        else if (ind == 3){
        	ind = 20;
        }
		while ((getDinero()<ind) && (ind>=5)){
			ind = ind/2;
		}
		restarDinero(ind);
		return apuesta;
	}
	public int probabilidadDos(){
		int probabilidad = -5;
		Carta unaCarta = null;
		Iterator<Carta> iterMano =  super.getListaCartas().getIterador();
		if (iterMano.hasNext()){
			unaCarta = iterMano.next();
			if (iterMano.hasNext()){
				Carta segundaCarta = iterMano.next();
				if (unaCarta.tieneMismoNumero(segundaCarta)){
					probabilidad = probabilidad + 12 ;					
				}
				if (unaCarta.tieneMismoSimbolo(segundaCarta)){
					probabilidad = probabilidad + 5 ;					
				}
				if (unaCarta.distanciaEntreCartas(segundaCarta) == 1){
					probabilidad = probabilidad + 6;
				}
				else if (unaCarta.distanciaEntreCartas(segundaCarta) == 2){
					probabilidad = probabilidad + 5;
				}
				else if (unaCarta.distanciaEntreCartas(segundaCarta) == 3){
					probabilidad = probabilidad + 4;
				}
				else if (unaCarta.distanciaEntreCartas(segundaCarta) == 4){
					probabilidad = probabilidad + 3;
				}
				if (1==unaCarta.getNumero() || unaCarta.getNumero() >= 8){
					probabilidad = probabilidad + 2;
					if (1 == unaCarta.getNumero()){
						if (segundaCarta.getNumero() != 2){
							probabilidad = probabilidad + 4;
						}
						else{
							probabilidad = probabilidad + 1;
						}
					}
					if ((11 == unaCarta.getNumero())||(12 == unaCarta.getNumero()) || (13 == unaCarta.getNumero())){
						probabilidad = probabilidad + 3;
					}
				}
				if (1 == segundaCarta.getNumero() || segundaCarta.getNumero() >= 8){
					probabilidad = probabilidad + 2;
					if (1 == segundaCarta.getNumero()){
						if (unaCarta.getNumero() != 2){
							probabilidad = probabilidad + 4;
						}
						else{
							probabilidad = probabilidad + 1;
						}
					}
					if ((11 == segundaCarta.getNumero())||(12 == segundaCarta.getNumero()) || (13 == segundaCarta.getNumero())){
						probabilidad = probabilidad + 3;
					}
				}
			}
		}
		
		return probabilidad;
	}
	
	public int calcularProbabilidad(String jugada){
		int prob = 0;
		int ind = 0;
		int nCartas = Poker.getPoker().getCartasMesa().obtenerNumCartas();
		if (nCartas == 3){
			ind = 2;
		}
		else if (nCartas == 4){
			ind = 0;
		}
		else if (nCartas == 5){
			ind = -3;
		}
		prob = prob + ind + (3 * (2 + Integer.valueOf(jugada.substring(0, 1)).intValue())) + (-3 +Integer.valueOf(jugada.substring(2)).intValue());
		return prob;
	}

	
}
