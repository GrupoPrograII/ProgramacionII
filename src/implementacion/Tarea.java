package implementacion;

import java.time.LocalDate;

public class Tarea {
//atributos en privado (a esto se lo llama Encapsulamiento)
	private String titulo;
	private String descripcion;
	private Double diasNecesarios;
	private LocalDate fechaAsignacion; // para fecha hay que usar libreria LocalDate (?)
	private Double diasRetraso;
	private Empleado empleado; // recorda que esta guardando el empleado asignado
	private Boolean finalizada; //nos sirve para saber cuando una tarea esta finalizada

// -----Metodos publicos-----
	public Tarea(String titulo, String descripcion, Double diasNecesarios, LocalDate fechaAsignacion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.diasNecesarios = diasNecesarios;
		this.fechaAsignacion = fechaAsignacion;
		this.diasRetraso = 0.0; // en 0.0 porque es inicial...
		this.empleado = null; // IREP: El empleado asignado no debe ser vacío.
		this.finalizada = false;
	}

	public void asignarEmpleado(Empleado e) {
		this.empleado = e;
		// asigna empleado a la Tarea actual.

	}

	public void retrasoTarea(Double diasAdicionales) {
		// se utiliza si la tarea fue retrasada, devuelve los días totales de retraso.
		this.diasRetraso = this.diasRetraso + diasAdicionales;
	}
//---------------------------------------------------------------
// esta funcion que va a usarse en calcularTiempo y calcularCosto
//---------------------------------------------------------------
	private Double getDiasTotales() {
		return this.diasNecesarios + this.diasRetraso;
	}

	public Double calcularTiempo() { // si multiplico el total de dias por 8 me va a devolver la cantidad de hrs
									 // totales
		// 1 día = 8 horas y 0,5 días = 4 horas.
		return getDiasTotales() * 8;
	}
//----------------------------------------------------------------------------------------
//calcular costo se usa con polimorfismo ya que varia si el empleado es comun o permanente
//----------------------------------------------------------------------------------------
	public Double calcularCosto() {
		if (this.empleado == null) {// IREP (?
			return 0.0;
		}
		// Llama al Empleado, este va a usar su propia lógica (Comun o Permanente).
		return this.empleado.calcularCostoTarea(getDiasTotales());
		// horas * valorHora del empleado.

	}
//----------------------------------------------------------------------------------------
// Método auxiliar para cambiar el estado de la tarea a finalizada.
// Este método será llamado por (HomeSolution/Proyecto).
//----------------------------------------------------------------------------------------
	public void finalizarTarea() {
		this.finalizada = true;
	}
//----------------------------------------	
//Devuelve true si la tarea fue finalizada
//----------------------------------------
	public boolean tareaFinalizada() {
		return this.finalizada;
	}
//---------------------------------------
//TP: El toString solo devuelve el título
//---------------------------------------
	@Override
	public String toString() {
		return this.titulo;	
	}
	
//getters
	public String getTitulo() {return this.titulo;}
	public String getDescripcion(){return this.descripcion;}
	public Double getdiasNecesarios(){return this.diasNecesarios;}
	public LocalDate getFechaAsignacion() {return this.fechaAsignacion;}
	public Double diasRetraso() {return this.diasRetraso;}
	public Empleado getEmpleadoTarea(){return this.empleado;}
	public Boolean getFinalizada() {return this.finalizada;}
	//"ALGUNOS DEBEN ESTAR DE MAS, REVISAR CUALES SE UTILIZAN"
}
