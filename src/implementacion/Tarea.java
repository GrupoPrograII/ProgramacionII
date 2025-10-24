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

// -----Metodos publicos-----
	public Tarea(String titulo, String descripcion, Double diasNecesarios, LocalDate fechaAsignacion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.diasNecesarios = diasNecesarios;
		this.fechaAsignacion = fechaAsignacion;
		this.diasRetraso = 0.0; // en 0.0 porque es el inicial...
		this.empleado = null;
	}

	public void asignarEmpleado(Empleado empleado) {
		// asigna empleado a la Tarea actual.

	}

	public void retrasoTarea(Double diasAdicionales) {
		// se utiliza si la tarea fue retrasada, devuelve los días totales de retraso.
		this.diasRetraso = this.diasRetraso + diasAdicionales;
	}

	public Double calcularTiempo() { // si multiplico el total de dias por 8 me va a devolver la cantidad de hrs totales
		Double diasTotales = this.diasNecesarios + this.diasRetraso;
		// 1 día = 8 horas y 0,5 días = 4 horas.
		return diasTotales * 8;
	}

	public void calcularCosto() {

		// horas * valorHora del empleado.
	}

	boolean tareaFinalizada() {
		return false;
		// devuelve true si la tarea fue finalizada.
	}

}