package casino;
import java.util.ArrayList;
import java.util.Iterator;
public class ListaJugadoresPoker {
    
    private ArrayList<JugadorPoker> listaJugadores;
    private static ListaJugadoresPoker miListaJugadoresPoker = null;
    
    private ListaJugadoresPoker() {
        listaJugadores = new ArrayList<JugadorPoker>();   
    }
    public static ListaJugadoresPoker getListaJugadoresPoker(){
    	if (miListaJugadoresPoker == null){
    		miListaJugadoresPoker = new ListaJugadoresPoker();
    	}
    	return miListaJugadoresPoker;
    }
    public ArrayList<JugadorPoker> getListaJugadores(){
    	return listaJugadores;
    }
    public void imprimir()
    {    
        Usuario unUsuario = null;
        Iterator <JugadorPoker> iter = ListaJugadoresPoker.getListaJugadoresPoker().getIterador();
        while(iter.hasNext()) {
            unUsuario = iter.next();
            unUsuario.imprimir();
          }
    }
    public int obtenerNumJugadoresPoker()
    {
        return ListaJugadoresPoker.getListaJugadoresPoker().listaJugadores.size();
    }

    private Iterator<JugadorPoker> getIterador()
    {
         return listaJugadores.iterator();  
    }
    public boolean esta(JugadorPoker pJugadorPoker)
    {
        return listaJugadores.contains(pJugadorPoker);

    }
    public void eliminarJugadorPoker(JugadorPoker pJugadorPoker)
    {
    	listaJugadores.remove(pJugadorPoker);

    }
    
    public JugadorPoker buscarJugadorPokerPorID(int pID){
        
            Iterator<JugadorPoker> itr=this.getIterador();
            JugadorPoker jugadorbuscado=null;
            while (itr.hasNext()){
                JugadorPoker jugadoractual=itr.next();
                if(jugadoractual.tieneEsteId(pID)){
                    jugadorbuscado=jugadoractual;
                }
            }
            return jugadorbuscado;
    }
    public JugadorPoker buscarJugadorPokerPorPos(int pos){
        
        Iterator<JugadorPoker> iter = getIterador();
        JugadorPoker jugadorbuscado = null;
        int ind = -1;
        JugadorPoker jugadoractual = null;
        while (ind!=pos){
            jugadoractual=iter.next();
            ind = ind +1;
        }
        jugadorbuscado = jugadoractual;
        return jugadorbuscado;
}
    public void annadirJugadorPoker(JugadorPoker pJugadorPoker){
        
        this.listaJugadores.add(pJugadorPoker);
        
    }
    
    public void resetear(){
        this.listaJugadores.clear();
        
    }

    public int cuantosActivos(){
    	Iterator<JugadorPoker> itr= getIterador();
    	int cuantos=0;
    	while (itr.hasNext()){
    		JugadorPoker jugadorActual = itr.next();
    		if (jugadorActual.getActivo()){
    			cuantos = cuantos + 1;
    		}
    	}
    	return cuantos;
    }
	public JugadorPoker compararManos(){
		Iterator<JugadorPoker> manosFinales = getIterador();
		JugadorPoker mejorJugador = null;;
		String mejorMano = "0 0";
		String mejorJugadaJugador = "0 0";
		JugadorPoker jugador = null;
		while (manosFinales.hasNext()){
			jugador = manosFinales.next();
			if (jugador.getActivo()){
				mejorJugadaJugador = jugador.getListaCartas().mejorJugada();
				if (Poker.getPoker().esMejorMano(mejorMano, mejorJugadaJugador)){
					mejorMano = mejorJugadaJugador;
					mejorJugador = jugador;
				}
			}
		}
		return mejorJugador;
	}
	public void reiniciarJugadores(){
		Iterator<JugadorPoker> iterJugadores = getIterador();
		JugadorPoker jugador = null;
		while(iterJugadores.hasNext()){
			jugador = iterJugadores.next();
			jugador.getListaCartas().restaurar();
			jugador.setActivo(true);
		}
	}
	public void turno(){
		int apuesta = 0;
		int accion = 0;
		boolean igualado = false;
		boolean rondaIgualacion = false;
		int pos = 0;
		Iterator<JugadorPoker> iter = getIterador();
		JugadorPoker jugador = null;
		while(iter.hasNext()){
			// Error corregido System.out.println("** Error **");
			jugador = iter.next();
			if (jugador.getActivo()){
				accion = jugador.decidirJugada(rondaIgualacion, 0);
				if (accion == 0){
					jugador.setActivo(false);
					// El jugador se retira
					}
				else if (accion == 2){
						// El jugador pasa el turno
				}
				else if (accion == 3){
					apuesta = jugador.apostar();
					if (apuesta>0){
						JugadorPoker jugadorApostante = jugador;
						Poker.getPoker().aumentarBote(apuesta);
						rondaIgualacion = true;
						pos = 0;
						while(pos < ListaJugadoresPoker.getListaJugadoresPoker().getListaJugadores().size()){	
							jugador = ListaJugadoresPoker.getListaJugadoresPoker().getListaJugadores().get(pos);
							if (jugadorApostante!=jugador){
								if (jugador.getActivo()){
									accion = jugador.decidirJugada(rondaIgualacion, apuesta);
									if (accion == 0){
										jugador.setActivo(false);
									}
									else if (accion == 1){
										igualado = jugador.igualar(apuesta);
										if (igualado){
											Poker.getPoker().aumentarBote(apuesta);
										}
										else{
											jugador.setActivo(false);
										}
									}
								}	
							}
							pos = pos + 1;
						}
						rondaIgualacion = false;
						apuesta = 0;
						
					}
				}
				
			}
		}
		
	}
	public JugadorPoker resolverPartida(){
		JugadorPoker jugadorGanador = null;
		JugadorPoker jugador = null;
		Iterator<JugadorPoker> iterJugadores = ListaJugadoresPoker.getListaJugadoresPoker().getIterador();
		Poker.getPoker().getCartasMesa().imprimir();
		while(iterJugadores.hasNext()){
			jugador = iterJugadores.next();
			if (jugador.getActivo()){
				jugador.imprimirCartas();
			}		
		}
		if (1 == ListaJugadoresPoker.getListaJugadoresPoker().cuantosActivos()){
			iterJugadores = ListaJugadoresPoker.getListaJugadoresPoker().getIterador();
			while(iterJugadores.hasNext()){
				jugador = iterJugadores.next();
				if (jugador.getActivo()){
					jugadorGanador = jugador;
				}		
			}
		}
		else{
			jugadorGanador = ListaJugadoresPoker.getListaJugadoresPoker().compararManos();
		}
		return jugadorGanador;
		
	}
	public JugadorPoker repartirCartasJugadores(){
		Iterator<JugadorPoker> iterJugadores = ListaJugadoresPoker.getListaJugadoresPoker().getIterador();
		JugadorPoker jugador = null;
		JugadorPoker usuario = null;
		int pos = 0;
		while (iterJugadores.hasNext()){
			jugador = iterJugadores.next();
			if(jugador.getDinero()>=5){
				Poker.getPoker().repartirMano(jugador);
				jugador.restarDinero(5);
				Poker.getPoker().aumentarBote(5);
				pos = pos + 1;
				if (jugador instanceof Humanoide){
					jugador.imprimirCartas();
					usuario = jugador;
				}
			}
			else{
				jugador.setActivo(false);
			}
		}
		return usuario;
	}
}
