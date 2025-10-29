package implementacion;

public class EmpleadoPermanente extends Empleado {
	private Double valorDia;
	private Double adicional;
	private String categoria; // "inicial", "tecnico" o "experto"
	private Boolean agregarAdicional;
	public EmpleadoPermanente(String nombre, Integer legajo, Double valorDia, Double categoria) {
		super(nombre, legajo); // llama al constructor empleado
		this.valorDia = valorDia;
		this.adicional = 0.02; // Adicional del 2%
		this.agregarAdicional = true;
	}

	void noCorrespondeAdicional() { // si el empleado registra algún atraso se anula el adicional del 2%
		this.agregarAdicional = false;
	}

	@Override
	public Double calcularCostoTarea(Double diasNecesarios) {
		double costoBase = diasNecesarios * this.valorDia; //costo base de la tarea para empleadoPermanente
		if (this.retrasos > 0 || !(agregarAdicional)) { // this.retrasos hereda de Empleado
			return costoBase;
		}
		return costoBase + (costoBase * this.adicional);
	}

}
