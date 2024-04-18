
package logica;

import java.sql.Date;

public class ReportesDocsFiscales {
    
    private String ninterno;
    private String tdocumento;
    private String treferencia;
    private String dfiscal;
    private Date fecha;
    private String tienda;
    private String name;

    public ReportesDocsFiscales() {
    }

    public ReportesDocsFiscales(String ninterno, String tdocumento, String treferencia, String dfiscal, Date fecha, String tienda, String name) {
        this.ninterno = ninterno;
        this.tdocumento = tdocumento;
        this.treferencia = treferencia;
        this.dfiscal = dfiscal;
        this.fecha = fecha;
        this.tienda = tienda;
        this.name = name;
    }

    public String getNinterno() {
        return ninterno;
    }

    public void setNinterno(String ninterno) {
        this.ninterno = ninterno;
    }

    public String getTdocumento() {
        return tdocumento;
    }

    public void setTdocumento(String tdocumento) {
        this.tdocumento = tdocumento;
    }

    public String getTreferencia() {
        return treferencia;
    }

    public void setTreferencia(String treferencia) {
        this.treferencia = treferencia;
    }

    public String getDfiscal() {
        return dfiscal;
    }

    public void setDfiscal(String dfiscal) {
        this.dfiscal = dfiscal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
