package implementacion;

public class EmpleadoComun extends Empleado{
	private Double valorHora;
	
	public EmpleadoComun(String nombre, Integer legajo, Double valorHora) {
		super(nombre, legajo); //llama al constructor Empleado, nos falta valorHora.
		this.valorHora = valorHora;
	}
//----------------------------------------------------------------------------------------------------
//--- Override sirve para detectar problemas
//--- sirve para decir que este metodo no es nuevo, esta implementando o modificando un comportamiento de la clase padre. 
//--- "Es una etiqueta de Polimorfismo".
//----------------------------------------------------------------------------------------------------
	@Override
	public Double calcularCostoTarea(Double diasNecesarios) {
		// Calculo por horas: (Días * 8 horas) * Valor/hora
		double horasTrabajo = diasNecesarios * 8;
		return horasTrabajo * this.valorHora;
	}
}
