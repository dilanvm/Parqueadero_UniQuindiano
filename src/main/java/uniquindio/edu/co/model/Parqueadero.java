package uniquindio.edu.co.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Parqueadero {
    private String nombre;
    private ArrayList<EspacioParqueadero> espacios;
    private ArrayList<Vehiculo> vehiculosAdentro;
    private ArrayList<Vehiculo> historialVehiculos;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Tarifa> tarifas;

    /**
     * Construye un nuevo parqueadero.
     *
     * @param nombre El nombre del parqueadero.
     */
    public Parqueadero(String nombre) {
        this.nombre = nombre;
        this.espacios = new ArrayList<>();
        this.vehiculosAdentro = new ArrayList<>();
        this.historialVehiculos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.tarifas = new ArrayList<>();
    }

    // ------------------ CRUD VEHÍCULO ------------------

    /**
     * Registra el ingreso de un vehículo. Busca un espacio disponible y lo asigna.
     *
     * @param vehiculo El vehículo a ingresar.
     */
    public void registrarIngresoVehiculo(Vehiculo vehiculo) {
        // Verificar placa duplicada
        if (buscarVehiculo(vehiculo.getPlaca()) != null) {
            System.out.println("Error: Ya hay un vehículo con esa placa adentro.");
            return;
        }

        // Buscar espacio disponible
        EspacioParqueadero espacioLibre = null;
        for (int i = 0; i < espacios.size(); i++) {
            EspacioParqueadero e = espacios.get(i);
            if (e.getEstado().equals("Disponible") && e.getTipoEspacio().equals(vehiculo.getTipoVehiculo())) {
                espacioLibre = e;
                break;
            }
        }

        if (espacioLibre == null) {
            System.out.println("Error: No hay espacio disponible para el tipo de vehículo: " + vehiculo.getTipoVehiculo());
        } else {
            vehiculo.setEspacioAsignado(espacioLibre);
            espacioLibre.ocupar(vehiculo);
            vehiculosAdentro.add(vehiculo);
            System.out.println("Vehículo ingresado con éxito en el espacio: " + espacioLibre.getCodigo());
        }
    }

    /**
     * Registra la salida de un vehículo y calcula su costo.
     *
     * @param placa La placa del vehículo que va a salir.
     */
    public void registrarSalidaVehiculo(String placa) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        if (vehiculo == null) {
            System.out.println("Error: El vehículo con esa placa no se encuentra adentro.");
            return;
        }

        vehiculo.setHoraSalida(LocalDateTime.now());
        Tarifa tarifa = buscarTarifaPorTipo(vehiculo.getTipoVehiculo());
        
        if (tarifa == null) {
            System.out.println("Error: No existe tarifa para el tipo de vehículo: " + vehiculo.getTipoVehiculo());
            return;
        }

        double costo = vehiculo.calcularCosto(tarifa);
        
        // Polimorfismo: Buscar al conductor en la lista de usuarios para aplicar su descuento específico
        Usuario conductor = buscarUsuario(vehiculo.getIdentificacionConductor());
        if (conductor != null) {
            double descuentoUsuario = conductor.obtenerDescuento();
            if (descuentoUsuario > 0) {
                double descuentoAplicado = costo * descuentoUsuario;
                costo -= descuentoAplicado;
                System.out.println("Se aplicó un descuento por tipo de usuario (" + conductor.getTipoUsuario() + ") del " + (descuentoUsuario * 100) + "%");
            }
        }

        vehiculo.setTotalPagado(costo);
        vehiculo.setEstado("Salio");

        EspacioParqueadero espacio = vehiculo.getEspacioAsignado();
        if (espacio != null) {
            espacio.ponerDisponible();
        }

        vehiculosAdentro.remove(vehiculo);
        historialVehiculos.add(vehiculo);

        System.out.println("Vehículo con placa " + placa + " registró salida. Total a pagar: $" + costo);
    }

    /**
     * Busca un vehículo por su placa entre los que están adentro.
     *
     * @param placa La placa a buscar.
     * @return El vehículo si existe, null en caso contrario.
     */
    public Vehiculo buscarVehiculo(String placa) {
        for (int i = 0; i < vehiculosAdentro.size(); i++) {
            Vehiculo v = vehiculosAdentro.get(i);
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Modifica el nombre del conductor de un vehículo que está adentro.
     *
     * @param placa La placa del vehículo.
     * @param nuevoNombreConductor El nuevo nombre.
     */
    public void modificarVehiculo(String placa, String nuevoNombreConductor) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        if (vehiculo != null) {
            vehiculo.setNombreConductor(nuevoNombreConductor);
            System.out.println("Nombre del conductor actualizado con éxito.");
        } else {
            System.out.println("Error: Vehículo no encontrado.");
        }
    }

    // ------------------ CRUD USUARIO ------------------

    /**
     * Agrega un nuevo usuario al parqueadero.
     *
     * @param usuario El usuario a agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        if (buscarUsuario(usuario.getIdentificacion()) != null) {
            System.out.println("Error: El usuario ya está registrado.");
        } else {
            usuarios.add(usuario);
            System.out.println("Usuario agregado con éxito.");
        }
    }

    /**
     * Elimina a un usuario buscando por su identificación.
     *
     * @param identificacion La identificación del usuario.
     */
    public void eliminarUsuario(String identificacion) {
        Usuario usuario = buscarUsuario(identificacion);
        if (usuario != null) {
            usuarios.remove(usuario);
            System.out.println("Usuario eliminado con éxito.");
        } else {
            System.out.println("Error: Usuario no encontrado.");
        }
    }

    /**
     * Busca un usuario por su identificación.
     *
     * @param identificacion La identificación a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    public Usuario buscarUsuario(String identificacion) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getIdentificacion().equals(identificacion)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Modifica el nombre de un usuario registrado.
     *
     * @param identificacion La identificación del usuario.
     * @param nuevoNombre El nuevo nombre a asignar.
     */
    public void modificarUsuario(String identificacion, String nuevoNombre) {
        Usuario usuario = buscarUsuario(identificacion);
        if (usuario != null) {
            usuario.setNombre(nuevoNombre);
            System.out.println("Nombre de usuario modificado con éxito.");
        } else {
            System.out.println("Error: Usuario no encontrado.");
        }
    }

    // ------------------ CRUD ESPACIO ------------------

    /**
     * Agrega un espacio al parqueadero.
     *
     * @param espacio El espacio a agregar.
     */
    public void agregarEspacio(EspacioParqueadero espacio) {
        if (buscarEspacio(espacio.getCodigo()) != null) {
            System.out.println("Error: El espacio con ese código ya existe.");
        } else {
            espacios.add(espacio);
            System.out.println("Espacio agregado con éxito.");
        }
    }

    /**
     * Elimina un espacio del parqueadero solo si está disponible.
     *
     * @param codigo El código del espacio a eliminar.
     */
    public void eliminarEspacio(String codigo) {
        EspacioParqueadero espacio = buscarEspacio(codigo);
        if (espacio != null) {
            if (espacio.getEstado().equals("Disponible")) {
                espacios.remove(espacio);
                System.out.println("Espacio eliminado con éxito.");
            } else {
                System.out.println("Error: No se puede eliminar el espacio porque no está disponible.");
            }
        } else {
            System.out.println("Error: Espacio no encontrado.");
        }
    }

    /**
     * Busca un espacio de parqueadero por su código.
     *
     * @param codigo El código a buscar.
     * @return El espacio si lo encuentra o null si no.
     */
    public EspacioParqueadero buscarEspacio(String codigo) {
        for (int i = 0; i < espacios.size(); i++) {
            EspacioParqueadero e = espacios.get(i);
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Modifica el tipo de vehículo que permite un espacio.
     *
     * @param codigo El código del espacio.
     * @param nuevoTipo El nuevo tipo de espacio (ej. "CARRO").
     */
    public void modificarEspacio(String codigo, String nuevoTipo) {
        EspacioParqueadero espacio = buscarEspacio(codigo);
        if (espacio != null) {
            espacio.setTipoEspacio(nuevoTipo);
            System.out.println("Tipo de espacio modificado con éxito.");
        } else {
            System.out.println("Error: Espacio no encontrado.");
        }
    }

    // ------------------ OTROS MÉTODOS ------------------

    /**
     * Imprime por consola todos los vehículos que se encuentran adentro.
     */
    public void listarVehiculosAdentro() {
        System.out.println("--- Vehículos Adentro ---");
        for (int i = 0; i < vehiculosAdentro.size(); i++) {
            System.out.println(vehiculosAdentro.get(i).toString());
        }
    }

    /**
     * Imprime por consola todos los espacios que tienen estado "Disponible".
     */
    public void listarEspaciosDisponibles() {
        System.out.println("--- Espacios Disponibles ---");
        for (int i = 0; i < espacios.size(); i++) {
            EspacioParqueadero e = espacios.get(i);
            if (e.getEstado().equals("Disponible")) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Agrega una tarifa al parqueadero.
     *
     * @param tarifa La tarifa a agregar.
     */
    public void agregarTarifa(Tarifa tarifa) {
        tarifas.add(tarifa);
        System.out.println("Tarifa agregada con éxito.");
    }

    /**
     * Busca la tarifa que corresponde a un tipo de vehículo.
     *
     * @param tipoVehiculo El tipo de vehículo ("CARRO", "MOTO", "BICICLETA").
     * @return La tarifa correspondiente o null si no se encuentra.
     */
    public Tarifa buscarTarifaPorTipo(String tipoVehiculo) {
        for (int i = 0; i < tarifas.size(); i++) {
            Tarifa t = tarifas.get(i);
            if (t.getTipoVehiculo().equals(tipoVehiculo)) {
                return t;
            }
        }
        return null;
    }

    // ------------------ CONSULTA DE ESPACIOS ------------------

    public int getTotalEspacios() {
        return espacios.size();
    }

    public int getEspaciosOcupados() {
        int count = 0;
        for (int i = 0; i < espacios.size(); i++) {
            if (espacios.get(i).getEstado().equals("Ocupado")) {
                count++;
            }
        }
        return count;
    }

    public int getEspaciosDisponibles() {
        int count = 0;
        for (int i = 0; i < espacios.size(); i++) {
            if (espacios.get(i).getEstado().equals("Disponible")) {
                count++;
            }
        }
        return count;
    }

// ------------------ REPORTES ------------------

    public int getVehiculosIngresadosHoy() {
        LocalDateTime inicioDia = LocalDateTime.now().toLocalDate().atStartOfDay();
        int count = 0;
        for (int i = 0; i < historialVehiculos.size(); i++) {
            if (historialVehiculos.get(i).getHoraIngreso().isAfter(inicioDia)) {
                count++;
            }
        }
        return count;
    }

    public double getIngresosGenerados() {
        double total = 0;
        for (int i = 0; i < historialVehiculos.size(); i++) {
            total += historialVehiculos.get(i).getTotalPagado();
        }
        return total;
    }

    public double getTiempoPromedioPermancencia() {
        if (historialVehiculos.isEmpty()) return 0;
        double totalMinutos = 0;
        for (int i = 0; i < historialVehiculos.size(); i++) {
            Vehiculo v = historialVehiculos.get(i);
            long minutos = java.time.Duration.between(v.getHoraIngreso(), v.getHoraSalida()).toMinutes();
            totalMinutos += minutos;
        }
        return totalMinutos / historialVehiculos.size();
    }

    public ArrayList<Vehiculo> getVehiculosMasDe(int minutos) {
        ArrayList<Vehiculo> resultado = new ArrayList<>();
        for (int i = 0; i < historialVehiculos.size(); i++) {
            Vehiculo v = historialVehiculos.get(i);
            long tiempo = java.time.Duration.between(v.getHoraIngreso(), v.getHoraSalida()).toMinutes();
            if (tiempo > minutos) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    // Getters y setters básicos para las listas
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<EspacioParqueadero> getEspacios() {
        return espacios;
    }

    public void setEspacios(ArrayList<EspacioParqueadero> espacios) {
        this.espacios = espacios;
    }

    public ArrayList<Vehiculo> getVehiculosAdentro() {
        return vehiculosAdentro;
    }

    public void setVehiculosAdentro(ArrayList<Vehiculo> vehiculosAdentro) {
        this.vehiculosAdentro = vehiculosAdentro;
    }

    public ArrayList<Vehiculo> getHistorialVehiculos() {
        return historialVehiculos;
    }

    public void setHistorialVehiculos(ArrayList<Vehiculo> historialVehiculos) {
        this.historialVehiculos = historialVehiculos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
}
