import java.util.Scanner;
import java.util.Stack;

class Vehiculo {
    String patente;
    String marca;
    String modelo;
    String color;

    public Vehiculo(String patente, String marca, String modelo, String color) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Patente: " + patente + ", Marca: " + marca + ", Modelo: " + modelo + ", Color: " + color;
    }
}

class Estacionamiento {
    private Stack<Vehiculo> pila = new Stack<>();

    public void estacionar(Vehiculo vehiculo) {
        if (pila.size() >= 8) {
            System.out.println("El estacionamiento está lleno.");
        } else {
            pila.push(vehiculo);
            System.out.println("Vehículo estacionado: " + vehiculo);
        }
    }

    public void retirar(String patente) {
        if (pila.isEmpty()) {
            System.out.println("El estacionamiento está vacío.");
            return;
        }

        Stack<Vehiculo> temp = new Stack<>();
        boolean encontrado = false;

        while (!pila.isEmpty()) {
            Vehiculo vehiculo = pila.pop();
            if (vehiculo.patente.equals(patente)) {
                System.out.println("Vehículo retirado: " + vehiculo);
                encontrado = true;
                break;
            } else {
                temp.push(vehiculo);
            }
        }

        while (!temp.isEmpty()) {
            pila.push(temp.pop());
        }

        if (!encontrado) {
            System.out.println("Vehículo no encontrado.");
        }
    }

    public void buscar(String patente) {
        for (Vehiculo vehiculo : pila) {
            if (vehiculo.patente.equals(patente)) {
                System.out.println("Vehículo encontrado: " + vehiculo);
                return;
            }
        }
        System.out.println("Vehículo no encontrado.");
    }

    public void mostrarEstacionados() {
        if (pila.isEmpty()) {
            System.out.println("El estacionamiento está vacío.");
        } else {
            System.out.println("Vehículos estacionados:");
            for (Vehiculo vehiculo : pila) {
                System.out.println(vehiculo);
            }
        }
    }
}

public class CalleSinSalida {
    public static void main(String[] args) {
        Estacionamiento estacionamiento = new Estacionamiento();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Estacionar vehículo");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Buscar vehículo");
            System.out.println("4. Mostrar todos los vehículos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese patente (ejemplo: ABCD123): ");
                    String patente = scanner.nextLine();
                    System.out.print("Ingrese marca del vehículo: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingrese modelo del vehículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ingrese color del vehículo: ");
                    String color = scanner.nextLine();
                    Vehiculo vehiculo = new Vehiculo(patente, marca, modelo, color);
                    estacionamiento.estacionar(vehiculo);
                    break;
                case 2:
                    System.out.print("Ingrese patente del vehículo a retirar: ");
                    String patenteRetirar = scanner.nextLine();
                    estacionamiento.retirar(patenteRetirar);
                    break;
                case 3:
                    System.out.print("Ingrese patente del vehículo a buscar: ");
                    String patenteBuscar = scanner.nextLine();
                    estacionamiento.buscar(patenteBuscar);
                    break;
                case 4:
                    estacionamiento.mostrarEstacionados();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
