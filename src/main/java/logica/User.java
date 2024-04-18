package logica;

import java.io.Serializable;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre; // Corresponde al campo 'nombre' en la base de datos
    private String farmacia; // Corresponde al campo 'farmacia' en la base de datos
    private String password;

    public User() {
    }

    // Constructor para insertar un nuevo usuario
    public User(String nombre, String farmacia, String password) {
        this.nombre = nombre;
        this.farmacia = farmacia;
        this.password = password;
    }

    // Constructor para recuperar un usuario de la base de datos
    public User(int id, String nombre, String farmacia, String password) {
        this.id = id;
        this.nombre = nombre;
        this.farmacia = farmacia;
        this.password = password;
    }

    // Getters y setters para todas las propiedades
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(String farmacia) {
        this.farmacia = farmacia;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}