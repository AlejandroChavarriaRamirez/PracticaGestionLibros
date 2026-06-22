/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicagestionlibros;
/*
 * Alejandro Chavarria Ramirez
 * Daniela Jazmin Gomez Peña
 * Marisol Del Carmen Masis Monge
 */
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BibliotecaApp {
    
    // Lista que guarda todos los libros en memoria
    static ArrayList<Libro> libros = new ArrayList<>();
    
    // Nombre del archivo donde se guardan los datos
    static String nombreArchivo = "libros.txt";

    public static void main(String[] args) {
        // Al iniciar cargamos los datos del archivo
        cargarArchivo();
        
        int opcion = 0;
        do {
            String menu = JOptionPane.showInputDialog(
                    "=== BIBLIOTECA ===\n"
                    + "1. Registrar libro\n"
                    + "2. Editar libro\n"
                    + "3. Buscar libro\n"
                    + "4. Eliminar libro\n"
                    + "5. Mostrar todos\n"
                    + "6. Salir\n\n"
                    + "Elija una opcion:");

            // Si el usuario cierra la ventana salimos
            if (menu == null) {
                break;
            }

            try {
                opcion = Integer.parseInt(menu);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (opcion) {
                case 1: registrar(); break;
                case 2: editar(); break;
                case 3: buscar(); break;
                case 4: eliminar(); break;
                case 5: mostrarTodos(); break;
                case 6: break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }

        } while (opcion != 6);

        // Al salir guardamos los datos en el archivo
        guardarArchivo();
        JOptionPane.showMessageDialog(null, "Datos guardados. Hasta luego.",
                "Sistema cerrado", JOptionPane.INFORMATION_MESSAGE);
    }
    // Carga los datos del archivo al iniciar el programa
    public static void cargarArchivo() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(nombreArchivo));
            try {
                while (true) {
                    String codigo = dis.readUTF();
                    String titulo = dis.readUTF();
                    String autor = dis.readUTF();
                    Genero genero = Genero.valueOf(dis.readUTF());
                    String anio = dis.readUTF();
                    String estado = dis.readUTF();
                    libros.add(new Libro(codigo, titulo, autor, genero, anio, estado));
                }
            } catch (EOFException e) {
                // Fin del archivo alcanzado
            }
            dis.close();

        } catch (IOException e) {
            // Si el archivo no existe simplemente empieza vacio
        }
    }
    // Guarda todos los libros del ArrayList en el archivo al salir
    public static void guardarArchivo() {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombreArchivo));
            for (Libro l : libros) {
                dos.writeUTF(l.getCodigo());
                dos.writeUTF(l.getTitulo());
                dos.writeUTF(l.getAutor());
                dos.writeUTF(l.getGenero().toString());
                dos.writeUTF(l.getAnio());
                dos.writeUTF(l.getEstado());
            }
            dos.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Registra un libro nuevo en la lista
    public static void registrar() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del libro:");
        // Valido que el codigo no este repetido
        for (Libro l : libros) {
            if (l.getCodigo().equals(codigo)) {
                JOptionPane.showMessageDialog(null, "Ya existe un libro con ese codigo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String titulo = JOptionPane.showInputDialog("Ingrese el titulo:");
        String autor = JOptionPane.showInputDialog("Ingrese el autor:");

        String[] generos = {"NOVELA", "HISTORIA", "CIENCIA", "INFANTIL"};
        String generoElegido = (String) JOptionPane.showInputDialog(null,
                "Seleccione el genero:", "Genero",
                JOptionPane.QUESTION_MESSAGE, null, generos, generos[0]);
        //
        String anio = JOptionPane.showInputDialog("Ingrese el año de publicacion:");
        // El estado por defecto es Disponible
        Libro nuevo = new Libro(codigo, titulo, autor, Genero.valueOf(generoElegido), anio, "Disponible");
        libros.add(nuevo);

        JOptionPane.showMessageDialog(null, "Libro registrado correctamente",
                "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    // Edita la informacion de un libro existente
    public static void editar() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del libro a editar:");

        for (Libro l : libros) {
            if (l.getCodigo().equals(codigo)) {
                String titulo = JOptionPane.showInputDialog("Ingrese el nuevo titulo:", l.getTitulo());
                String autor = JOptionPane.showInputDialog("Ingrese el nuevo autor:", l.getAutor());

                String[] generos = {"NOVELA", "HISTORIA", "CIENCIA", "INFANTIL"};
                String generoElegido = (String) JOptionPane.showInputDialog(null,
                        "Seleccione el nuevo genero:", "Genero",
                        JOptionPane.QUESTION_MESSAGE, null, generos, l.getGenero().toString());

                String anio = JOptionPane.showInputDialog("Ingrese el nuevo año:", l.getAnio());

                String[] estados = {"Disponible", "Prestado"};
                String estado = (String) JOptionPane.showInputDialog(null,
                        "Seleccione el estado:", "Estado",
                        JOptionPane.QUESTION_MESSAGE, null, estados, l.getEstado());

                l.setTitulo(titulo);
                l.setAutor(autor);
                l.setGenero(Genero.valueOf(generoElegido));
                l.setAnio(anio);
                l.setEstado(estado);

                JOptionPane.showMessageDialog(null, "Libro editado correctamente",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro un libro con ese codigo",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    // Busca un libro por su codigo
    public static void buscar() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del libro a buscar:");
        for (Libro l : libros) {
            if (l.getCodigo().equals(codigo)) {
                JOptionPane.showMessageDialog(null, l.toString(),
                        "Libro encontrado", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro un libro con ese codigo",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    // Elimina un libro de la lista por su codigo
    public static void eliminar() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo del libro a eliminar:");
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getCodigo().equals(codigo)) {
                libros.remove(i);
                JOptionPane.showMessageDialog(null, "Libro eliminado correctamente",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro un libro con ese codigo",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    // Muestra todos los libros registrados
    public static void mostrarTodos() {
        if (libros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay libros registrados",
                    "Lista vacia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String lista = "";
        for (Libro l : libros) {
            lista += l.toString() + "\n-------------------------\n";
        }
        
        JOptionPane.showMessageDialog(null, lista,
                "Todos los libros", JOptionPane.INFORMATION_MESSAGE);
    }
}
