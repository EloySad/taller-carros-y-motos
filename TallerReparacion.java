import java.util.Scanner;

public class TallerReparacion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Definir los estados disponibles
        String[] estados = {"Pendiente", "En reparación", "Reparado"};

        // Pedir la capacidad máxima del taller
        System.out.print("Ingrese la capacidad máxima del taller: ");
        int maxEmployees = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        // Definir el array tridimensional
        String[][][] taller = new String[maxEmployees][2][5];
        int numTrabajos = 0;

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar trabajo");
            System.out.println("2. Eliminar trabajo");
            System.out.println("3. Actualizar estado de un vehículo");
            System.out.println("4. Buscar vehículo por marca y modelo");
            System.out.println("5. Mostrar agenda de trabajos");
            System.out.println("6. Contar y mostrar vehículos por estado");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    if (numTrabajos >= maxEmployees) {
                        System.out.println("El taller está a máxima capacidad.");
                        break;
                    }
                    // Solicitar la información para cada trabajo y almacenar los datos en el array
                    System.out.print("Ingrese el nombre del empleado: ");
                    String nombreEmpleado = scanner.nextLine();
                    taller[numTrabajos][0][4] = nombreEmpleado; // Almacenar el nombre del empleado

                    System.out.print("Ingrese el tipo de vehículo (0 para Moto, 1 para Carro): ");
                    int tipoVehiculo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner

                    System.out.print("Ingrese la marca: ");
                    String marca = scanner.nextLine();
                    taller[numTrabajos][tipoVehiculo][0] = marca;

                    System.out.print("Ingrese el modelo: ");
                    String modelo = scanner.nextLine();
                    taller[numTrabajos][tipoVehiculo][1] = modelo;

                    System.out.print("Ingrese el año: ");
                    String anio = scanner.nextLine();
                    taller[numTrabajos][tipoVehiculo][2] = anio;

                    System.out.println("Seleccione el estado del vehículo:");
                    System.out.println("0. Pendiente");
                    System.out.println("1. En reparación");
                    System.out.println("2. Reparado");
                    System.out.print("Ingrese el número correspondiente al estado: ");
                    int estadoIndex = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    String estado = estados[estadoIndex];
                    taller[numTrabajos][tipoVehiculo][3] = estado;

                    numTrabajos++;
                    System.out.println("Trabajo agregado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del empleado cuyo trabajo desea eliminar: ");
                    String nombreEmpleadoEliminar = scanner.nextLine();
                    boolean encontradoEliminar = false;

                    for (int i = 0; i < numTrabajos; i++) {
                        if (taller[i][0][4] != null && taller[i][0][4].equalsIgnoreCase(nombreEmpleadoEliminar)) {
                            taller[i] = new String[2][5]; // Vaciar el trabajo
                            encontradoEliminar = true;
                            System.out.println("Trabajo eliminado exitosamente.");
                            break;
                        }
                    }
                    if (!encontradoEliminar) {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la marca del vehículo para actualizar el estado: ");
                    String marcaActualizacion = scanner.nextLine();
                    System.out.print("Ingrese el modelo del vehículo para actualizar el estado: ");
                    String modeloActualizacion = scanner.nextLine();
                    boolean encontradoActualizacion = false;

                    for (int i = 0; i < numTrabajos; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (taller[i][j][0] != null && taller[i][j][0].equalsIgnoreCase(marcaActualizacion)
                                    && taller[i][j][1].equalsIgnoreCase(modeloActualizacion)) {
                                System.out.println("Seleccione el nuevo estado del vehículo:");
                                System.out.println("0. Pendiente");
                                System.out.println("1. En reparación");
                                System.out.println("2. Reparado");
                                System.out.print("Ingrese el número correspondiente al estado: ");
                                estadoIndex = scanner.nextInt();
                                scanner.nextLine(); // Limpiar el buffer del scanner
                                estado = estados[estadoIndex];
                                taller[i][j][3] = estado;
                                System.out.println("Estado actualizado.");
                                encontradoActualizacion = true;
                                break;
                            }
                        }
                        if (encontradoActualizacion) break;
                    }
                    if (!encontradoActualizacion) {
                        System.out.println("Vehículo no encontrado para actualizar.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese la marca del vehículo a buscar: ");
                    String marcaBusqueda = scanner.nextLine();
                    System.out.print("Ingrese el modelo del vehículo a buscar: ");
                    String modeloBusqueda = scanner.nextLine();
                    boolean encontradoBusqueda = false;

                    for (int i = 0; i < numTrabajos; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (taller[i][j][0] != null && taller[i][j][0].equalsIgnoreCase(marcaBusqueda)
                                    && taller[i][j][1].equalsIgnoreCase(modeloBusqueda)) {
                                encontradoBusqueda = true;
                                String tipo = (j == 0) ? "Moto" : "Carro";
                                System.out.printf("\nVehículo encontrado:\nTipo: %s\nMarca: %s\nModelo: %s\nAño: %s\nEstado: %s\nEmpleado: %s\n",
                                        tipo, taller[i][j][0], taller[i][j][1], taller[i][j][2], taller[i][j][3], taller[i][0][4]);
                                break;
                            }
                        }
                        if (encontradoBusqueda) break;
                    }
                    if (!encontradoBusqueda) {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;
                case 5:
                    // Mostrar todos los vehículos en una tabla
                    System.out.println("\nAgenda de trabajos:");
                    System.out.println("| Tipo  | Marca  | Modelo  | Año  | Estado         | Empleado        |");
                    System.out.println("|-------|--------|---------|------|----------------|-----------------|");
                    for (int i = 0; i < numTrabajos; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (taller[i][j][0] != null) {
                                String tipo = (j == 0) ? "Moto" : "Carro";
                                System.out.printf("| %-5s | %-6s | %-7s | %-4s | %-14s | %-15s |\n",
                                        tipo, taller[i][j][0], taller[i][j][1], taller[i][j][2], taller[i][j][3], taller[i][0][4]);
                            }
                        }
                    }
                    break;
                case 6:
                    // Contar y mostrar cuántos vehículos hay en cada estado
                    int pendientes = 0, enReparacion = 0, reparados = 0;
                    for (int i = 0; i < numTrabajos; i++) {
                        for (int j = 0; j < 2; j++) {
                            if (taller[i][j][3] != null) {
                                switch (taller[i][j][3].toLowerCase()) {
                                    case "pendiente":
                                        pendientes++;
                                        break;
                                    case "en reparación":
                                        enReparacion++;
                                        break;
                                    case "reparado":
                                        reparados++;
                                        break;
                                }
                            }
                        }
                    }
                    System.out.println("\nEstado de los vehículos:");
                    System.out.println("Pendiente: " + pendientes);
                    System.out.println("En reparación: " + enReparacion);
                    System.out.println("Reparado: " + reparados);
                    break;
                case 7:
                    // Salir del programa
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
}
