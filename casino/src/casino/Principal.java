package casino;

public class Principal {
	private static Principal miPrincipal = null;
	public static void main(String[] args) {
		Principal.getPrincipal().inicializarUsuario();
	}
	
	
	private Principal() {
		
	}
	public static Principal getPrincipal(){
		if(miPrincipal==null){
			miPrincipal = new Principal();
		}
		return miPrincipal;
	}
	
	public void jugarPartida()  {
		inicializarUsuario();
	}
	public void jugarPoker(Usuario usu)  {
		Poker.getPoker().jugarPoker(usu);
	}
	public void jugarBlackjack(Usuario usu)  {
		Blackjack.getBlackjack().jugarBlackjack(usu);
	}
	public void jugarRuleta(Usuario usu)  {
		
	}
	private void inicializarUsuario()  {
		int opcion=0;
		String nombre;
		ListaUsuarios listaU = ListaUsuarios.getListaUsuarios();
		System.out.println("-------------------------------------------------");
		System.out.println("Introduzca su nombre y pulse Enter:");
		System.out.println("-------------------------------------------------");
		System.out.println("                                                 ");
		Usuario usu = null;
		nombre = Escaner.getEscaner().leerEscanerUsuario();
		if (ListaUsuarios.getListaUsuarios().existeUnUsuarioConMismoNombre(nombre)){
			usu = ListaUsuarios.getListaUsuarios().buscarUsuarioPorNombre(nombre);
		}
		else {
			usu = listaU.darDeAltaUsuario(nombre);
		}
		System.out.println("-------------------------------------------------");
		System.out.println("Muy buenas, " + (usu.getNombre()));  
		System.out.println("Sus fondos son de : "+ (usu.getDinero())+" €");
		System.out.println("¿A que desea jugar?");
		System.out.println("1:Poker  2:Blackjack  3:Ruleta  4:Salir");
		System.out.println("-------------------------------------------------");
		System.out.println("                                                 ");
		opcion = Escaner.getEscaner().leerEscaner4Opciones(); 
		if (opcion==1){
			jugarPoker(usu);			
		}
		else if (opcion==2){
			jugarBlackjack(usu);			
		}
		else if (opcion==3){
			jugarRuleta(usu);			
			}
		else if (opcion==4){			
		}
	}
}

