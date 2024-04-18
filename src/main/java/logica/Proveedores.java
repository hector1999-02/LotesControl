/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Hector Mejia IT
 */
public class Proveedores {
    private String idProv;
    private String Nombre;

    public Proveedores() {
    }

    public Proveedores(String idProv, String Nombre) {
        this.idProv = idProv;
        this.Nombre = Nombre;
    }

    public String getIdProv() {
        return idProv;
    }

    public void setIdProv(String idProv) {
        this.idProv = idProv;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
