package casino;

public class Usuario {
		//atributos
		private String  nombre;
		private int  idUsuario;
		private int dinero;
		
		//constructora

		public Usuario(String pNombre, int pIdUsuario, int pDinero)
		{
			nombre = pNombre;
			idUsuario = pIdUsuario;
			dinero = pDinero;
			
		
		}
		public int getDinero(){
			return dinero;
		}
		public void sumarDinero(int pDinero){
			dinero = dinero + pDinero;
		}
		public void restarDinero(int pDinero){
			dinero = dinero - pDinero;
		}
		public String getNombre(){
			return nombre;
		}
		public void setNombre (String pNombre){
			this.nombre=pNombre;
		}
		public int getIdUsuario(){
			return idUsuario;
		}
		// otros métodos
		public boolean tieneEsteNombre(String pNombre)
		{
			if (pNombre == nombre){
				return true;
			}
			else {
				return false;
			}
		
		}
		public boolean tieneEsteId(int pId)
		{
			if (pId == idUsuario){
				return true;
			}
			else {
				return false;
			}
		
		}
		
		public boolean tieneElMismoNombre(Usuario pNombre)
		{
			if (pNombre.idUsuario==idUsuario){
				return true;
			}
			else {
				return false;
			}
		}

		public void imprimir()
		{
			System.out.println("El usuario "+(nombre)+" tiene el ID "+(idUsuario)+" y tiene "+(dinero)+" €");
	  		}
}
