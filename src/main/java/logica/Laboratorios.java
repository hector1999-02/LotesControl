/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Hector Mejia IT
 */
public class Laboratorios {
    private String id_lab;
    private String nombre;

    public Laboratorios() {
    }

    public Laboratorios(String id_lab, String nombre) {
        this.id_lab = id_lab;
        this.nombre = nombre;
    }

    public String getId_lab() {
        return id_lab;
    }

    public void setId_lab(String id_lab) {
        this.id_lab = id_lab;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
