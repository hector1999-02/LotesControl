package logica;

import java.sql.Date;

public class ArticulosPorLotes {

    private String itemId;
    private String sap;
    private String Descripcion;
    private Date fechaCaducidad;
    private String lote;
    private String observacion;
    private int cantidad;
    private double precio_etiquetado;
    private String vendedor;
    private String laboratorio;
    private String registro_sanitario;
    private String devolutivo;

    public ArticulosPorLotes(String itemId, String Descripcion, Date fechaCaducidad, String lote, String observacion, int cantidad, double precio_etiquetado, String vendedor, String laboratorio) {
        this.itemId = itemId;
        this.Descripcion = Descripcion;
        this.fechaCaducidad = fechaCaducidad;
        this.lote = lote;
        this.observacion = observacion;
        this.cantidad = cantidad;
        this.precio_etiquetado = precio_etiquetado;
        this.vendedor = vendedor;
        this.laboratorio = laboratorio;
    }

    public String getRegistro_sanitario() {
        return registro_sanitario;
    }

    public void setRegistro_sanitario(String registro_sanitario) {
        this.registro_sanitario = registro_sanitario;
    }

    public String getDevolutivo() {
        return devolutivo;
    }

    public void setDevolutivo(String devolutivo) {
        this.devolutivo = devolutivo;
    }

    
    
    public String getSap() {
        return sap;
    }

    public void setSap(String sap) {
        this.sap = sap;
    }

    

    public ArticulosPorLotes() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_etiquetado() {
        return precio_etiquetado;
    }

    public void setPrecio_etiquetado(double precio_etiquetado) {
        this.precio_etiquetado = precio_etiquetado;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

}
