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
	private Object[] tareas = new Object[10];    // si es una ibject es un array fijo, Cual era el limite de tareas de una proyecto?
	private boolean estaFinalizado; 
	private List<Tupla<Integer, String>> empleadosProyecto;

	public Proyecto(int numeroId, String domicilio, String[] cliente, String inicio, String fin) {
		this.numeroId = numeroId;
		this.domicilio = domicilio;
		this.cliente = cliente;
		this.inicio = inicio;
		this.fin = fin;
		this.estaFinalizado = false;
		
	}
	
	public void agregarTarea (Tarea t) {
		for (int i = 0; i < tareas.length; i++) {
	        if (tareas[i] == null) {
	            tareas[i] = t;
	            return;
	        }
	    }
	    throw new IllegalStateException("No hay espacio para más tareas");
	}
	
	public Tarea buscarTarea (String titulo) throws IllegalArgumentException {
		for (Object o : tareas) {
	        if (o != null) {
	            Tarea t = (Tarea) o;
	            if (t.getTitulo().equals(titulo)) {
	                return t;
	            }
	        }
	    }
        throw new IllegalArgumentException("No se encontró la tarea: " + titulo);
    }
	
	public void asignarEmpleado(Empleado e) {
		empleadosProyecto.add(new Tupla <>(e.getLegajo(), e.getNombre()));
	}
	
	public boolean todasTareasFinalizadas() {        //corta cuando encuentras una atrea en false, si no sigue y sale en true
		for (Object o : tareas) {
	        if (o != null) {
	            Tarea t = (Tarea) o;
	            if (!t.tareaFinalizada()) {
	            	return false;
	            }
	        }
        }
		return false;
    }
	
	 public double calcularFinal() {
	        double total = 0;
	        for (Object o : tareas) {
	        	Tarea t = (Tarea) o;
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
	
	public Object[] getTareas() {
        return tareas;
    }
	
	public List<Tupla<Integer, String>> getEmpleadosDelProyecto(){  
		return empleadosProyecto;
	}

}
