package implementacion;

import jdk.internal.misc.FileSystemOption;

public class Cliente {
	private String nombre;
	private Integer telefono;
	private String email;

//-------metodos publicos---------
	
	//constructor
	public Cliente(String nombre, Integeer telefono, String email) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	//el IREP se realiza aca? como se realiza???? lpm jsdjsdjdj
		
//		if (nombre == null || telefono == null || email == null) {
//			return;
		}
	
	
	//getters
	//tengo entendido que hay que hacer distintas funciones tipo get para obtener datos de atributos 
	//para saber u obtener el contenido de la variable.
	public String getNombre() {
		return nombre;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public String email() {
		return email;
	}
	

}
