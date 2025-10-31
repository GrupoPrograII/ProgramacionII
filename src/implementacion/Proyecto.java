package entidades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Proyecto {
	
	private int numeroId;
	private String domicilio;
	private String [] cliente;
	private String inicio;
	private String fin;
	private Map<Integer, Tarea> tareas = new HashMap<>(); 
	private boolean estaFinalizado; 
	private List<Empleado> empleadosProyecto;

	public Proyecto(int numeroId, String domicilio, String[] cliente, String inicio, String fin) {
		this.numeroId = numeroId;
		this.domicilio = domicilio;
		this.cliente = cliente;
		this.inicio = inicio;
		this.fin = fin;
		this.estaFinalizado = false;
		
	}
	
	public void agregarTarea (Tarea t) {
		tareas.put(t);
	}
	
	public Tarea buscarTarea (String titulo) throws IllegalArgumentException {
		for (Tarea t : tareas) {
            if (t.getTitulo().equals(titulo)) {
                return t;
            }
		}
        throw new IllegalArgumentException("No se encontró la tarea: " + titulo);
    }
	
	public void asignarEmpleado(Empleado e) {
		empleadosProyecto.add(e);
	}
	
	public boolean todasTareasFinalizadas() {        //corta cuando encuentras una atrea en false, si no sigue y sale en true
        for (Tarea t : tareas.values()) {
            if (!t.tareaFinalizada()) {
            	return false;
            }
        }
        return true;
    }
	
	 public double calcularFinal() {
	        double total = 0;
	        for (Tarea t : tareas.values()) {
	            total += t.calcularCosto();
	        }
	        return total;
	    }
	
	public void finalizar() {
        this.estaFinalizado = true;
    }
	
	 public String toString() {
	        return "Proyecto{" +
	                "numero=" + numeroId +
	                ", domicilio='" + domicilio + '\'' +
	                ", finalizado=" + estaFinalizado +
	                '}';
	    }
	

	public int getNumeroId() { 
		return numeroId; 
		}
	
    public String getDomicilio() { 
    	return domicilio; 
    	}

	public boolean estaFinalizado() {
		return estaFinalizado;
	}
	
	public Map<Integer, Tarea> getTareas() {
        return tareas;
    }
	
	public List<Empleado> getEmpleadosDelProyecto(){
		return empleadosProyecto;
	}


}
