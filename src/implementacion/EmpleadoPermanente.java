package implementacion;

public class EmpleadoPermanente extends Empleado {
	private Double valorDia;
	private Double adicional; // Adicional del 2%
	private String categoria;
	
	public EmpleadoPermanente(String nombre, Integer legajo, Double valorDia, Double adicional, Double categoria) {
		super(nombre, legajo); //llama al constructor empleado
		this.valorDia = valorDia;
		this.adicional = adicional;
	}

	@Override
	public Double calcularCostoTarea(Double diasNecesarios) {
		// TODO Auto-generated method stub
		return null;
	}

}
