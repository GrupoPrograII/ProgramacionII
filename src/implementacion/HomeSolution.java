import java.util.Map;
package entidades;

public class HomeSolutionImpl {

	private Map<Integer, Empleado> empleados = new HashMap<>();
    private Map<Integer, Proyecto> proyectos = new HashMap<>();
	
	private int sigNumProy = 1; 		//para asignar los codigos de los proyectos, las IDs automaticamente

	
	public void nuevosEmpleados(String nombre, double valor) {
		if(nombre == null || nombre.isEmpty() || valor < 0) {
			throw new IllegalArgumentException("Datos de empleados invalidos");
		}else {
			Empleado nuevo = new Empleado(nombre, valor);
			empleados.put(nuevo.getLegajo(), nuevo);
		}
	}
	
	public void nuevosEmpleados(String nombre, double valor, String categoria) {
		if(nombre == null || nombre.isEmpty() || valor < 0) {
			throw new IllegalArgumentException("Datos de empleados invalidos");
		}else {
			Empleado nuevo = new Empleado(nombre, valor, categoria);
			empleados.put(nuevo.getLegajo(), nuevo);
		}
	}
	
	public void nuevoProyecto(String direccion, String cliente, int fechaInicio, int fechaFin) { //crea un proyecto con una lista de tareas propias
		if(direccion == null || cliente == null) {
			throw new IllegalArgumentException("Datos de proyecto invalidos");
		}else {
			Proyecto p = new Proyecto(sigNumProy++,direccion,cliente,fechaInicio,fechaFin);
			proyectos.put(p.getNumero(), p);
		}
	}
	
	public void asignarTarea(Integer numeroProyecto, String titulo, String descripcion, double dias) {
	    Proyecto p = buscarProyecto(numeroProyecto);
	    
	    if (titulo == null || titulo.isEmpty()) {
	        throw new IllegalArgumentException("El título de la tarea no puede estar vacío");
	    }
	    if (dias <= 0) {
	        throw new IllegalArgumentException("La duración estimada debe ser mayor que cero");
	    }
	    if (p.buscarTarea(titulo) != null) {
	        throw new IllegalArgumentException("Ya existe una tarea con ese título en el proyecto");
	    }

	    Tarea nueva = new Tarea(titulo, descripcion, dias);
	    p.agregarTarea(nueva);
	}
	
	public void asignarEmpleado(int numero, String titulo) {   //el numero correpsonde al numero de proyecto aignado
		Proyecto p = buscarProyecto(numero);
		Tarea t = p.buscarTarea(titulo);
		
		if(t.tieneResponsable()) {
			throw new Exception("La tarea ya tiene un responsable");
		}else {
			Empleado e = buscarEmpleadoDisponible();
			t.setResponsable(e);
			e.asignarTarea(t);
		}
	}
	
	public void reAsignarEmpleadoEnProyecto(int numero, int legajo, String titulo) {
		Proyecto p = buscarProyecto(numero);
		Tarea t = p.buscarTarea(titulo);
		if(!t.tieneResponsable()) {
			throw new Exception("La tarea no tiene empleado asignado");
		}else {
			Empleado nuevo = buscarEmpleadoPorLegajo(legajo);
			Empleado anterior = t.getResponsable();
			anterior.liberarTarea(t);
			t.setResponsable(nuevo);
			nuevo.asignarTarea(t);
		}
	}
	
	public void informarRetraso(Integer numeroProyecto, String tituloTarea, double diasRetraso) {
	    Proyecto p = buscarProyecto(numeroProyecto);
	    Tarea t = p.buscarTarea(tituloTarea);
	    if (t == null){
	    	throw new IllegalArgumentException("Tarea no encontrada");
	    }
	    if (diasRetraso <= 0) {
	    	throw new IllegalArgumentException("El retraso debe ser positivo");
	    }
	    t.agregarRetraso(dias);
	}
	
	public double costoProyecto(Integer numeroProyecto) {
	    Proyecto p = buscarProyecto(numeroProyecto);
	    return p.calcularCosto();
	}

	public String consultarProyecto(Integer numeroProyecto) {
	    Proyecto p = buscarProyecto(numeroProyecto);
	    return p.getEstado(); // Por ejemplo: "Pendiente", "Activo" o "Finalizado"
	}
	
	public boolean terminoElProyecto(Integer numeroProyecto) {
	    Proyecto p = buscarProyecto(numeroProyecto);
	    return p.estaFinalizado();
	}
	
	public List<Proyecto> proyectosPendientes() {
	    List<Proyecto> pendientes = new ArrayList<>();
	    for (Proyecto p : proyectos.values()) {
	        if (!p.estaFinalizado()) {
	            pendientes.add(p);
	        }
	    }
	    return pendientes;
	}
	
}

