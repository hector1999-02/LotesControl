/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

public class Sucursales {
    private String STOREID;
    private String NAME;

    public Sucursales(String STOREID, String NAME) {
        this.STOREID = STOREID;
        this.NAME = NAME;
    }

    public Sucursales() {
    }

    public String getSTOREID() {
        return STOREID;
    }

    public void setSTOREID(String STOREID) {
        this.STOREID = STOREID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
