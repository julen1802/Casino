package casino;
import java.util.ArrayList;
import java.util.Iterator;
public class ListaCartas {

		// atributos
		private ArrayList<Carta> lista1;
		// constructora

		public ListaCartas()
		{
			lista1= new ArrayList<Carta>();
		}
		// otros métodos
		public ArrayList<Carta> getListaCartas()
		{
	  		return lista1;
		}
		public int obtenerNumCartas()
		{ 
			return lista1.size();

		}
		public Iterator<Carta> getIterador()
		{
	 		return lista1.iterator(); 

		}		
		public boolean esta(Carta pCarta)
		{
			if (pCarta!=null) {
				return lista1.contains(pCarta);
			}
			else {
				return false;
			}

		}
		public boolean escalera(){
			boolean escalera = false;
			int numeroBase = lista1.get(0).getNumero();
			if ((numeroBase + 1) == lista1.get(1).getNumero() ){
				if ((numeroBase + 2) == lista1.get(2).getNumero() ){
					if ((numeroBase + 3) == lista1.get(3).getNumero() ){
						if ((numeroBase + 4) == lista1.get(4).getNumero() ){
							escalera = true;
						}	
					}
				}
			}
			return escalera;
		}
		public void anadirCarta(Carta pCarta)
		{
			if (pCarta!=null){
				lista1.add(pCarta);
			}
		}
		public void eliminarCarta(Carta pCarta)
		{
			lista1.remove(pCarta);
		}
		public void imprimir()
		{
			Carta unaCarta = null;
			Iterator <Carta> iter = getIterador();
			while(iter.hasNext()) {
				unaCarta = iter.next();
				unaCarta.imprimir();
	  		}
		}
		public Carta devolverCartaMasAlta()
		{
			Carta cartaAlta = null;
			Carta unaCarta = null;
			Iterator <Carta> iter = getIterador();
			if (iter.hasNext()){
				cartaAlta = iter.next();
			}
			while(iter.hasNext()) {
				unaCarta = iter.next();
				if (cartaAlta.getNumero()<unaCarta.getNumero()) {
					cartaAlta = unaCarta;
				}
			}
			return cartaAlta;
		}
		public Carta devolverCartaMasBaja()
		{
			Carta cartaBaja = null;
			Carta unaCarta = null;
			Iterator <Carta> iter = getIterador();
			if (iter.hasNext()){
				cartaBaja = iter.next();
			}
			while(iter.hasNext()) {
				unaCarta = iter.next();
				if (cartaBaja.getNumero()>unaCarta.getNumero()) {
					cartaBaja = unaCarta;
				}
	  		}
			return cartaBaja;
		}
		public void restaurar(){
			lista1.clear();
		}
		public boolean tieneAs(){
			boolean cierto = false;
			Carta unaCarta = devolverCartaMasBaja();
			if (unaCarta.getNumero()==1){
				cierto = true;
			}
			return cierto;
		}
		public ListaCartas ordenarCartas(){
			ListaCartas listaOrdenada = new ListaCartas();
			Carta unaCarta = devolverCartaMasBaja();
			listaOrdenada.anadirCarta(unaCarta);
			eliminarCarta(unaCarta);
			// Una carta en lista
			unaCarta = devolverCartaMasBaja();
			listaOrdenada.anadirCarta(unaCarta);
			eliminarCarta(unaCarta);
			// Dos carta en lista
			unaCarta = devolverCartaMasBaja();
			listaOrdenada.anadirCarta(unaCarta);
			eliminarCarta(unaCarta);
			// Tres carta en lista
			unaCarta = devolverCartaMasBaja();
			listaOrdenada.anadirCarta(unaCarta);
			eliminarCarta(unaCarta);
			// Cuatro carta en lista
			unaCarta = devolverCartaMasBaja();
			listaOrdenada.anadirCarta(unaCarta);
			eliminarCarta(unaCarta);
			// Cinco carta en lista
			return listaOrdenada;
			
		}
		// 1 Escalera Real
		// 2 Escalera de Color
		// 3 Poker
		// 4 Full
		// 5 Color
		// 6 Escalera
		// 7 Trio
		// 8 Doble Pareja
		// 9 Pareja
		// 10 Carta Mas Alta
		// En caso de empate se mirara la carta mas alta
		
		
		public String mJugada(){
			// Devuelve la mejor jugada con las 5 cartas
			String jugada = null;
			int mano = 0;
			String simbolo=null;
			int cartaAlta = 0;
			Carta unaCarta = null;
			ListaCartas lista = ordenarCartas();
			int trioDe = 0;
			int parejaDe1 = 0;
			int parejaDe2 = 0;
			boolean cierto = true;
			boolean escalera = false;
			boolean color = false;
			boolean trio = false;
			boolean pareja1 = false;
			boolean pareja2 = false;
			boolean poker = false;
			int pos = 0;
			if (lista.getListaCartas().size()==5){
				unaCarta = lista.getListaCartas().get(pos);
				simbolo = unaCarta.getSimbolo();
				cierto = true;
				pos = pos + 1;
				while((lista.getListaCartas().size()>pos) && (cierto)) {
					unaCarta = lista.getListaCartas().get(pos);
					if (simbolo != unaCarta.getSimbolo()){
						cierto = false;
					}
					pos = pos + 1;
				}
				color=cierto;
				
				
				if (cierto){
					if ( 1 == lista.getListaCartas().get(0).getNumero() ){
						if ( 10 == lista.getListaCartas().get(1).getNumero() ){							
							// Escalera Real
							mano=9;
							cartaAlta=1;
						}
					}
					if (mano == 0){
						escalera = lista.escalera();
							if (escalera && color){
								// Escalera De Color
								mano=8;
								cartaAlta = lista.devolverCartaMasAlta().getNumero();
							}
					}
				}
				if (mano == 0){
					int cartasIguales=1;
					int carta = 0;
					pos = 0;
					
					if (lista.getListaCartas().size()==5){
						unaCarta = lista.getListaCartas().get(pos);
						pos = pos + 1;
						carta = unaCarta.getNumero();
						while((pos<lista.getListaCartas().size()) && (!poker)) {
							unaCarta = lista.getListaCartas().get(pos);
							if (carta==unaCarta.getNumero()){
								cartasIguales = cartasIguales + 1;
								if(cartasIguales == 4){
									poker = true;
								}
							}
							else{
								carta = unaCarta.getNumero();
								cartasIguales = 1;
							}
							pos = pos + 1;
						}
						if(poker){
							// Poker
							mano = 7;
							cartaAlta = carta;
						}
						else{
							pos = 0;
							ListaCartas cLista1 = new ListaCartas();
							ListaCartas cLista2 = new ListaCartas();
							ListaCartas cLista3 = new ListaCartas();
							ListaCartas cLista4 = new ListaCartas();
							ListaCartas cLista5 = new ListaCartas();
							while (pos < lista.getListaCartas().size()) {
								unaCarta = lista.getListaCartas().get(pos);	
								if (cLista1.getListaCartas().size()==0){
									cLista1.anadirCarta(unaCarta);
								}
								else{
									if(cLista1.getListaCartas().get(0).getNumero() == unaCarta.getNumero()){
										cLista1.anadirCarta(unaCarta);								
									}
									else{
										if (cLista2.getListaCartas().size()==0){
											cLista2.anadirCarta(unaCarta);	
										}
										else{
											if(cLista2.getListaCartas().get(0).getNumero() == unaCarta.getNumero()){
												cLista2.anadirCarta(unaCarta);
											}
											else{
												if (cLista3.getListaCartas().size()==0){
													cLista3.anadirCarta(unaCarta);	
												}
												else{
													if(cLista3.getListaCartas().get(0).getNumero() == unaCarta.getNumero()){
														cLista3.anadirCarta(unaCarta);
													}
													else{
														if (cLista4.getListaCartas().size()==0){
															cLista4.anadirCarta(unaCarta);	
														}
														else{
															if(cLista4.getListaCartas().get(0).getNumero() == unaCarta.getNumero()){
																cLista4.anadirCarta(unaCarta);	
															}
															else{
																if (cLista5.getListaCartas().size()==0){
																	cLista5.anadirCarta(unaCarta);	
																}
																else{
																	if(cLista5.getListaCartas().get(0).getNumero() == unaCarta.getNumero()){
																		cLista5.anadirCarta(unaCarta);	
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								pos = pos + 1;
							}
							int cantidadCartas = cLista1.getListaCartas().size();
							if (cantidadCartas == 3){
								trio = true;
								trioDe = cLista1.getListaCartas().get(0).getNumero();
								
							}
							else if (cantidadCartas == 2){
								pareja1 = true;
								parejaDe1 = cLista1.getListaCartas().get(0).getNumero();
							}
							
							cantidadCartas = cLista2.getListaCartas().size();
							
							if (cantidadCartas == 3){
								trio = true;
								trioDe = cLista2.getListaCartas().get(0).getNumero();
								
							}
							else if (cantidadCartas == 2){
								if (!pareja1){
									pareja1 = true;
									parejaDe1 = cLista2.getListaCartas().get(0).getNumero();
								}
								else{
									pareja2 = true;
									parejaDe2 = cLista1.getListaCartas().get(0).getNumero();
								}
								
							}
							
							
							cantidadCartas = cLista3.getListaCartas().size();
							
							if (cantidadCartas == 3){
								trio = true;
								trioDe = cLista3.getListaCartas().get(0).getNumero();
								
							}
							else if (cantidadCartas == 2){
								if (!pareja1){
									pareja1 = true;
									parejaDe1 = cLista3.getListaCartas().get(0).getNumero();
								}
								else{
									pareja2 = true;
									parejaDe2 = cLista3.getListaCartas().get(0).getNumero();
								}
								
							}
							
							cantidadCartas = cLista4.getListaCartas().size();
							
							if (cantidadCartas == 2){
								if (!pareja1){
									pareja1 = true;
									parejaDe1 = cLista4.getListaCartas().get(0).getNumero();
								}
								else{
									pareja2 = true;
									parejaDe2 = cLista4.getListaCartas().get(0).getNumero();
								}
								
							}
							
							if (trio && pareja1){
								// Full
								mano = 6;
								cartaAlta = trioDe;
							}
							else if (color){
								// Color
								mano = 5;
								if(lista.tieneAs()){
									cartaAlta = 1;
								}
								else {
									cartaAlta = lista.devolverCartaMasAlta().getNumero();
								}
							}
							else if (escalera){
							// Escalera
								mano = 4;
								if (lista.tieneAs() && (13 == lista.devolverCartaMasAlta().getNumero())){
									cartaAlta = 1;
								}
								else{
									cartaAlta = lista.devolverCartaMasAlta().getNumero();
								}
							}
							else if (trio){
								// Trio
								mano = 3;
								cartaAlta = trioDe;				
							}
							else{
								if(pareja1 && pareja2){
									// Dos parejas
									mano = 2;
									cartaAlta = parejaDe1;
									if (cartaAlta < parejaDe2){
										cartaAlta = parejaDe2;
									}
								}
								else if (pareja1){
									// Una pareja
									mano = 1;
									cartaAlta = parejaDe1;
								}
								else{
									mano = 0;
									if(lista.tieneAs()){
										cartaAlta = 1;
									}
									else {
										cartaAlta = lista.devolverCartaMasAlta().getNumero();
									}
								}
							}
						}
					}
				}
			}
			String manoS = String.valueOf(mano);
			String cartaAltaS=String.valueOf(cartaAlta);
	        jugada = (manoS) + " " + (cartaAltaS);
	        // Util para sacar un string de un string         String subStr=str.substring(3, 11);
			return jugada;
		}
			// 9 Escalera Real
			// 8 Escalera de Color
			// 7 Poker
			// 6 Full
			// 5 Color
			// 4 Escalera
			// 3 Trio
			// 2 Doble Pareja
			// 1 Pareja
			// 0 Carta Mas Alta
			// En caso de empate se mirara la carta mas alta. La carta mas alta es el As, luego la K,Q,J,10,9,8,7,6,5,4,3,2.
		
		public String mejorJugada(){
			String jugada = null;
			String probabilidad1 = "0 0";
			String probabilidad2 = null;
			ListaCartas mesa = Poker.getPoker().getCartasMesa();
			int nCartas = Poker.getPoker().getCartasMesa().obtenerNumCartas();
			ListaCartas lCartas = new ListaCartas();
			Carta unaCarta = null;
			if (nCartas == 3){
				mesa = Poker.getPoker().getCartasMesa();
				Iterator<Carta> iterMano = getIterador();
				Iterator<Carta> iterMesa = mesa.getIterador();
				lCartas.restaurar();
				while(iterMano.hasNext()) {
					unaCarta = iterMano.next();
					lCartas.anadirCarta(unaCarta);
				}
				while(iterMesa.hasNext()) {
					unaCarta = iterMesa.next();
					lCartas.anadirCarta(unaCarta);
				}
				jugada = lCartas.mJugada();
			}
			else if (nCartas == 4){
				int pos = 0;
				int ind = 0;
				while (pos < mesa.getListaCartas().size()){
					mesa = Poker.getPoker().getCartasMesa();
					Iterator<Carta> iterMano = getIterador();
					lCartas.restaurar();
					ind = 0;
					while(iterMano.hasNext()) {
						unaCarta = iterMano.next();
						lCartas.anadirCarta(unaCarta);
					}
					while (ind < mesa.getListaCartas().size()){
						if (ind!=pos){
							lCartas.anadirCarta(mesa.getListaCartas().get(ind));
						}
						ind = ind + 1;
					}
					pos= pos + 1;
					probabilidad2 = lCartas.mJugada();
					probabilidad1 = Poker.getPoker().compararJugadas(probabilidad1, probabilidad2);
				}
				jugada = probabilidad1;
			}
			else if (nCartas == 5){
				int ind = 0;
				int pos1 = 1;
				int pos2 = 0;
				boolean finalizado = false;
				while (!finalizado){
					mesa = Poker.getPoker().getCartasMesa();
					Iterator<Carta> iterMano = getIterador();
					lCartas.restaurar();
					while(iterMano.hasNext()) {
						unaCarta = iterMano.next();
						lCartas.anadirCarta(unaCarta);
					}
					ind = 0;
					while (ind < mesa.getListaCartas().size()){
						if ((ind!=pos1) && (ind!=pos2)){
							lCartas.anadirCarta(mesa.getListaCartas().get(ind));
						}
						ind = ind + 1;
					}
					
					if (pos1 == mesa.getListaCartas().size()){
						pos2 = pos2 + 1;
						if (pos2==mesa.getListaCartas().size()){
							finalizado = true;
						}
						else {
							pos1 = pos2 + 1;
						}
					}
					else{
						pos1 = pos1 + 1;
					}
						
					probabilidad2 = lCartas.mJugada();
					probabilidad1 = Poker.getPoker().compararJugadas(probabilidad1, probabilidad2);
				}
				jugada = probabilidad1;
			}
			System.out.println(jugada);
			return jugada;
		}
}
