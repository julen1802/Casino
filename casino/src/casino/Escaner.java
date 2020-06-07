package casino;

import java.util.Scanner;

public class Escaner {
	
	//atributos
	private static Escaner miEscaner=new Escaner();
	private Scanner sc= new Scanner(System.in);

	private Escaner() {
		sc=new Scanner(System.in);
	}

	
	public static Escaner getEscaner(){
		if(miEscaner == null){
			miEscaner = new Escaner();
	  	}
		return miEscaner;
	}
	
	
	public String leerEscanerUsuario(){
		boolean lc=true;
		String lectura=null;
		while (lc){
			lectura=sc.nextLine();
			if (lectura.length()>0){
				lc=false;
			}
		}
		return lectura;
		
	}
	

	
	public int leerEscaner4Opciones(){
		boolean lc = true;
		String lectura;
		int apuesta = 0;
		while (lc){
			lectura = sc.nextLine();
			if (lectura.length() == 1){
				if (lectura.equals("1")){
					apuesta = 1;
					lc = false;
				}
				else if (lectura.equals("2")){
					apuesta = 2;
					lc = false;

				}
				else if (lectura.equals("3")){
					apuesta = 3;
					lc = false;

				}
				else if (lectura.equals("4")){
					apuesta = 4;
					lc = false;

				}
			}
			else{
				System.out.println("Tecla errónea");
			}
		}
		return apuesta;
	}
	
	
	public int decidirJugada(){
		boolean lc=true;
		String lectura;
		int decision=0;
		while (lc){
			lectura = sc.nextLine();
			if (lectura.length() == 1){
				if (lectura.equals("0")){
					decision=0;
					lc=false;
				}
				else if (lectura.equals("2")){
					decision=2;
					lc=false;

				}
				else if (lectura.equals("3")){
					decision=3;
					lc=false;

				}
			}
			else{
				System.out.println("Tecla errónea");
			}
		}
		return decision;
	}
	
	public int decidirIgualar(){
		boolean lc=true;
		String lectura;
		int decision=0;
		while (lc){
			lectura= sc.nextLine();
			if (lectura.length()==1){
				if (lectura.equals("0")){
					decision=0;
					lc=false;
				}
				else if (lectura.equals("1")){
					decision=1;
					lc=false;

				}
			}
			else{
				System.out.println("Tecla errónea");
			}
		}
		return decision;
	}
	
	public int leerEscaner3Opciones(){
		boolean lc = true;
		String lectura;
		int decision = 0;
		while (lc){
			lectura = sc.nextLine();
			if (lectura.length() == 1){
				if (lectura.equals("0")){
					decision = 0;
					lc = false;
				}
				else if (lectura.equals("1")){
					decision = 1;
					lc = false;

				}
				else if (lectura.equals("2")){
					decision = 2;
					lc = false;
				}
			}
			else{
				System.out.println("Tecla errónea");
			}
		}
		return decision;
	}
	public int leerSalirJuego(){
		boolean lc = true;
		String lectura;
		int decision = 0;
		while (lc){
			lectura = sc.nextLine();
			if (lectura.length() == 1){
				if (lectura.equals("0")){
					decision = 0;
					lc = false;
				}
				else if (lectura.equals("1")){
					decision = 1;
					lc = false;

				}
			}
			else{
				System.out.println("Tecla errónea");
			}
		}
		return decision;
	}
	public void cerrarEscaner(){
		sc.close();
	}
	public int avanzar(){
		boolean lc = true;
		String lectura;
		int decision = 0;
		System.out.println("--------------------");
		System.out.println("Pulse 1 para Seguir");
		System.out.println("Tecla errónea");
		while (lc){
			lectura = sc.nextLine();
			if (lectura.length() == 1){
				if (lectura.equals("1")){
					decision = 1;
					lc = false;
				}
			}
			else{
				System.out.println("Tecla errónea");
			}
		}
		return decision;
	}
}