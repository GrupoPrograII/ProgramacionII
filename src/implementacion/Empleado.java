package implementacion;

public abstract class Empleado {
//-------------------------------------------------------
//para tipo abstracto, los atributos deben usar protected
//-------------------------------------------------------
	protected String nombre;
	protected Integer legajo;
	protected Integer retrasos;
	
	public Empleado (String nombre, Integer legajo) {
		this.nombre = nombre;
		this.legajo = legajo;
		this.retrasos = 0; 		//cualquier empleado nuevo empieza con 0 retrasos

	}
	//----------------------------------------------------------------------------------------------------
	//----- Empleado deberia tener un metodo abstracto para que sus hijos tengan que calcular el costo de 
	//----- una nueva Tarea y cada hijo lo calcula a su manera (Polimorfismo)
	//----------------------------------------------------------------------------------------------------
	public abstract Double calcularCostoTarea(Double diasNecesarios); 
	//su único propósito es forzar a las clases hijas 
	//a que ellas escriban el contenido.
	
	public void registrarRetraso() {
		this.retrasos++; //un retraso aumenta en "1"
	}
	
	//getters
	public String getNombre() {return this.nombre;}
	public Integer getLegajo() {return this.legajo;}
	public Integer getRetrasos() {return this.retrasos;}
}
