package casino;

public class Humanoide extends JugadorPoker {
	public Humanoide(String pNombre, int pIdUsuario, int pDinero) {
		super(pNombre, pIdUsuario, pDinero);
		// TODO Auto-generated constructor stub
	}
	public int decidirJugada(boolean rondaIgualacion, int apuestaAIgualar){
		int jugada = 0;
		if (!rondaIgualacion){
			System.out.println("Sus fondos son de: " + (getDinero()));
			System.out.println("Elige qué jugada vas a realizar:");
			System.out.println("0: retirarse  2: pasar    3: apostar ");
			jugada = Escaner.getEscaner().decidirJugada();
		}
		else{
			System.out.println("Sus fondos son de: " + (getDinero()));
			System.out.println("Se encuentra en una ronda de igualación. Decida si desea igualar la apuesta:");
			System.out.println("0: retirarse     1: igualar ");
			jugada = Escaner.getEscaner().decidirIgualar();
		}
		return jugada;
	}
	public int decidirJugadaBlackjack(){
		int accion = 0;
		accion = Escaner.getEscaner().leerEscaner3Opciones();
		// 0 Plantarse
		// 1 Pedir Carta
		// 2 Doblar Apuesta
		return accion;
	}
	
	public int apostar(){
		int apuesta = 0;
		if (getDinero()>0){
			System.out.println("Sus fondos son de: " + (getDinero()));
			System.out.println("Elige la cantidad que deseas apostar:");
			System.out.println("1: 5€    2: 10€   3: 20€  4: 50€");
			apuesta = Escaner.getEscaner().leerEscaner4Opciones();
			if (apuesta == 1){
				apuesta = 5;
			}
			else if (apuesta == 2){
				apuesta = 10;
			}
			else if (apuesta == 3){
				apuesta = 20;
			}
			else if (apuesta == 4){
				apuesta = 50;
			}
			if (apuesta <= getDinero()){
				restarDinero(apuesta);
			}
			else{
				System.out.println("No dispone de fondos suficientes para subir la apuesta. Su turno pasara automaticamente");
				pasar();
				apuesta = 0;
			}
		}
		return apuesta;
	}
}