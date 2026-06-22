/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicagestionlibros;

/**
 * @author aleja
 */
public class Libro {

    // Atributos del libro
    private String codigo;
    private String titulo;
    private String autor;
    private Genero genero;
    private String anio;
    private String estado;

    // Constructor con parametros
    public Libro(String codigo, String titulo, String autor, Genero genero, String anio, String estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anio = anio;
        this.estado = estado;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    // Muestra la informacion del libro de forma legible
    @Override
    public String toString() {
        return "Codigo: " + codigo
                + "\nTitulo: " + titulo
                + "\nAutor: " + autor
                + "\nGenero: " + genero
                + "\nAnio: " + anio
                + "\nEstado: " + estado;
    }
}
