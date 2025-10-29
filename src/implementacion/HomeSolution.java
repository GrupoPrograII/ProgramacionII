package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeSolution implements IHomeSolution{
	
	private Map<Integer, Empleado> empleados = new HashMap<>();
    private Map<Integer, Proyecto> proyectos = new HashMap<>();
	
	@Override
	public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
		if(nombre == null || nombre.isEmpty() || valor < 0) {
			throw new IllegalArgumentException("Datos de empleados invalidos");
		}else {
			Empleado nuevo = new Empleado(nombre, valor);
			empleados.put(nuevo.getLegajo(), nuevo);
		}
	}

	@Override
	public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {
		if(nombre == null || nombre.isEmpty() || valor < 0) {
			throw new IllegalArgumentException("Datos de empleados invalidos");
		}else {
			Empleado nuevo = new Empleado(nombre, valor, categoria);
			empleados.put(nuevo.getLegajo(), nuevo);
		}
	}

	@Override
	public void registrarProyecto(int numeroId, String[] titulos, String[] descripcion, double[] dias, String domicilio,
			String[] cliente, String inicio, String fin) throws IllegalArgumentException {
		if(numeroId < 0 || domicilio == null || cliente == null) {
			throw new IllegalArgumentException("Datos de proyecto invalidos");
		}else {
			Proyecto p = new Proyecto(numeroId,domicilio,cliente,inicio,fin);
			proyectos.put(p.getNumeroId(), p);
		}
	}

	@Override
	public void asignarResponsableEnTarea(Integer numeroId, String titulo) throws Exception {
		Proyecto p = proyectos.get(numero);
	    if (p == null) throw new IllegalArgumentException("Proyecto no encontrado.");
	    if (p.estaFinalizado()) throw new Exception("El proyecto está finalizado.");

	    Tarea t = p.buscarTarea(titulo);
	    if (t == null) throw new IllegalArgumentException("Tarea no encontrada.");
	    if (t.tieneResponsable()) throw new Exception("La tarea ya tiene un responsable.");

	    Object[] disponibles = empleadosNoAsignados();		//obtenes las lista de empleados libres 
	    if (disponibles.length == 0) throw new Exception("No hay empleados disponibles.");

	    Empleado elegido = (Empleado) disponibles[0];    //elige el primero libre

	    t.asignarEmpleado(elegido);
	    p.asignarEmpleadoEnTarea(elegido);
	}
		
	@Override
	public void asignarResponsableMenosRetraso(Integer numeroId, String titulo) throws Exception {
	    Proyecto p = proyectos.get(numeroId);
	    if (p == null) throw new IllegalArgumentException("Proyecto no encontrado.");
	    if (p.estaFinalizado()) throw new Exception("El proyecto está finalizado.");

	    Tarea tarea = p.buscarTarea(titulo);
	    if (tarea == null) throw new IllegalArgumentException("Tarea no encontrada.");
	    if (tarea.tieneResponsable()) throw new Exception("La tarea ya tiene un responsable.");

	    Object[] disponibles = empleadosNoAsignados();
	    if (disponibles.length == 0) throw new Exception("No hay empleados disponibles.");

	    // apartir de aca busca de los disponibles el de menos retrasos
	    Empleado mejor = null;
	    int minRetrasos = 0;

	    for (Object o : disponibles) {
	        Empleado e = (Empleado) o;
	        int retrasos = consultarCantidadRetrasosEmpleado(e.getLegajo());
	        if (retrasos < minRetrasos) {
	            minRetrasos = retrasos;
	            mejor = e;
	        }
	    }
	    
	    tarea.asignarEmpleado(mejor);
	    p.asignarEmpleadoEnTarea(mejor);
	}

	@Override
	public void registrarRetrasoEnTarea(Integer numeroId, String titulo, double cantidadDias)
			throws IllegalArgumentException {
		Proyecto p = proyectos.get(numeroId);
	    Tarea t = p.buscarTarea(titulo);
	    if (t == null) throw new IllegalArgumentException("Tarea no encontrada");
	    if (cantidadDias <= 0) throw new IllegalArgumentException("El retraso debe ser positivo");
	  
	    t.retrasoTarea(cantidadDias);
		
	}

	@Override
	public void agregarTareaEnProyecto(Integer numeroId, String titulo, String descripcion, double dias)
			throws IllegalArgumentException {
		Proyecto p = proyectos.get(numeroId);
	    
	    if (titulo == null || titulo.isEmpty()) throw new IllegalArgumentException("El título de la tarea no puede estar vacío");
	    if (dias <= 0) throw new IllegalArgumentException("La duración estimada debe ser mayor que cero");
	    if (p.buscarTarea(titulo) != null) throw new IllegalArgumentException("Ya existe una tarea con ese título en el proyecto");
	  
	    Tarea nueva = new Tarea(titulo, descripcion, dias);
	    p.agregarTarea(nueva);
	}

	@Override
	public void finalizarTarea(Integer numero, String titulo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reasignarEmpleadoEnProyecto(Integer numeroId, Integer legajo, String titulo) throws Exception {
		Proyecto p = proyectos.get(numeroId);
		Tarea t = p.buscarTarea(titulo);
		if(!t.tieneResponsable()) throw new Exception("La tarea no tiene empleado asignado");
	
		Empleado nuevo = empleados.get(legajo);
		Empleado anterior = t.getResponsable();
		anterior.estaDisponible = true;				//CONTROLAR
		t.setEmpleado(nuevo);						//la idea es que cambie el booleano esta disponible cuando los cambio
		nuevo.estaDisponible = false;				// y que a latarea se le asigne el nuevo empleado
		
	}

	@Override
	public void reasignarEmpleadoConMenosRetraso(Integer numeroId, String titulo) throws Exception {
		Proyecto p = proyectos.get(numeroId);
		Tarea t = p.buscarTarea(titulo);
		if(!t.tieneResponsable()) throw new Exception("La tarea no tiene empleado asignado");
		
		//bueca el de menos restrasos para reasignar
	    Empleado mejor = null;
	    int minRetrasos = 0;

	    for (Empleado i : empleados) {
	        Empleado e = (Empleado) i;
	        int retrasos = consultarCantidadRetrasosEmpleado(e.getLegajo());
	        if (retrasos < minRetrasos) {
	            minRetrasos = retrasos;
	            mejor = e;
	        }
	    }
		Empleado anterior = t.getResponsable();
		anterior.estaDisponible = true;				//CONTROLAR
		t.setEmpleado(mejor);						//la idea es que cambie el booleano esta disponible cuando los cambio
		mejor.estaDisponible = false;				// y que a latarea se le asigne el nuevo empleado
	}

	@Override
	public double costoProyecto(Integer numeroId) {
		Proyecto p = proyectos.get(numeroId);
	    return p.calcularCosto();
	}

	@Override
	public List<Tupla<Integer, String>> proyectosFinalizados() {
		List<Tupla<Integer, String>> finalizados = new ArrayList<>();
        for (Proyecto p : proyectos) {
            if (p.estaFinalizado()) {
                finalizados.add(new Tupla<>(p.getNumero(), p.getDomicilio()));
            }
        }
        return finalizados;
	}

	@Override
	public List<Tupla<Integer, String>> proyectosPendientes() {
		List<Tupla<Integer, String>> pendientes = new ArrayList<>();
        for (Proyecto p : proyectos) {
            if (!p.estaFinalizado()) {
               pendientes.add(new Tupla<>(p.getNumero(), p.getDomicilio()));
            }
        }
        return pendientes;
	}

	@Override
	public List<Tupla<Integer, String>> proyectosActivos() {
		return proyectosPendientes();
	}

	@Override
	public Object[] empleadosNoAsignados() {
		List<Empleado> libres = new ArrayList<>();
	    for (Empleado e : empleados.values()) {
	        if (e.estaDisponible()) {
	            libres.add(e);
	        }
	    }
	    return libres.toArray();
	}

	@Override
	public boolean estaFinalizado(Integer numeroId) {
		Proyecto p = proyectos.get(numeroId);
	    return p.estaFinalizado();
	}

	@Override
	public int consultarCantidadRetrasosEmpleado(Integer legajo) {
		Empleado e = empleados.get(legajo);
	    return e.getRegistraRetraso();
	}

	@Override
	public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] tareasProyectoNoAsignadas(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] tareasDeUnProyecto(Integer numeroId) {
		Proyecto p = proyectos.get(numeroId);
		p.Tareas();				//NO SE si esta bien
		return null;			//quiero que devuelva el conjunto de tareas de un proyecto
	}

	@Override
	public String consultarDomicilioProyecto(Integer numeroId) {
		Proyecto p = proyectos.get(numeroId);
	    return p.getDomicilio();
	}

	@Override
	public boolean tieneRestrasos(String legajo) {
		Empleado e = empleados.get(legajo);
		if(e.retrasos > 0) {
			return true;
		}
	}

	@Override
	public List<Tupla<Integer, String>> empleados() {
		List<Tupla<Integer, String>> listaEmpleados = new ArrayList<>();
	    for (Empleado e : empleados.values()) {
	        listaEmpleados.add(new Tupla<>(e.getLegajo(), e.getNombre()));
	    }
	    return listaEmpleados;
	}

	@Override
	public String consultarProyecto(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

}
