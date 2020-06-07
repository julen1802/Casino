package casino;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaUsuarios 
{
	// atributos

	private ArrayList < Usuario > nListaUsuarios;
	private static ListaUsuarios miListaUsuarios = null;
	// constructora

	private ListaUsuarios()
	{ 
		nListaUsuarios = new ArrayList<Usuario>();	
	}

	// otros métodos

	public static ListaUsuarios getListaUsuarios()
	{
		if(miListaUsuarios == null){
			miListaUsuarios = new ListaUsuarios();
  		}
  		return miListaUsuarios;
	}

	public int obtenerNumUsuarios()
	{
		return nListaUsuarios.size();
	}

	private Iterator<Usuario> getIterador()
	{
 		return nListaUsuarios.iterator();  
	}
	public boolean esta(Usuario pUsuario)
	{
		return nListaUsuarios.contains(pUsuario);

	}
	public Usuario buscarUsuarioPorId(int pId)
	{
		boolean existe = false;
		Usuario unUsuario = null;
		Iterator <Usuario> iter = getIterador();
		while(iter.hasNext() && !existe) {
			unUsuario = iter.next();
  			if(unUsuario.tieneEsteId(pId)) {
  				existe = true;
  			}
  		}
  		if (!existe) {
  			unUsuario = null;
  		}
  		return unUsuario;
	}

	public Usuario buscarUsuarioPorNombre(String pNombre)
	{
		boolean existe = false;
		Usuario unUsuario = null;
		Iterator <Usuario> iter = getIterador();
		while(iter.hasNext() && !existe) {
			unUsuario = iter.next();
  			if(unUsuario.tieneEsteNombre(pNombre)) {
  				existe = true;
  			}
  		}
  		if (!existe) {
  			unUsuario = null;
  		}
  		return unUsuario;
	}

	public boolean existeUnUsuarioConMismoNombre(String pNombre)
	{
		Usuario unUsuario = null;
		unUsuario = buscarUsuarioPorNombre(pNombre);
		if(unUsuario!=null) {
			return true;
  		}
		else {
			return false;
		}
	}

	public Usuario darDeAltaUsuario(String pNombre)
	{
		if (!existeUnUsuarioConMismoNombre(pNombre)) {
			int id = buscarIdLibre();
			int dinero = 500;
			Usuario usuarioNuevo= new Usuario(pNombre, id, dinero);
			nListaUsuarios.add(usuarioNuevo);
			return usuarioNuevo;     //No se si cogiendolo seguira en la lista
		}
		else {
			return buscarUsuarioPorNombre(pNombre);
		}
	}
	public int buscarIdLibre(){
		int num=0;
		int num2=0;
		Usuario unUsuario = null;
		while (num2!=num){
			num2=num;
			Iterator <Usuario> iter = getIterador();
			while(iter.hasNext()) {
				unUsuario = iter.next();
				if (unUsuario.getIdUsuario()==num) {
					num=num+1;
				}
			}
		}
		
		return num;
	}
	public void imprimir()
	{	
		Usuario unUsuario = null;
		Iterator <Usuario> iter = getIterador();
		while(iter.hasNext()) {
			unUsuario = iter.next();
			unUsuario.imprimir();
  		}
	}

	public void resetear()
	{
		nListaUsuarios = null;
 		nListaUsuarios = new ArrayList<Usuario>();
	}

}
