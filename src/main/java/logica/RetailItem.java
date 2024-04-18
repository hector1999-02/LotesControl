package logica;

public class RetailItem {
    private String ITEMID;
    private String NAMEALIAS;
    private String EXTENDEDDESCRIPTION;
    private String DELETED;

    public RetailItem(String ITEMID, String NAMEALIAS, String EXTENDEDDESCRIPTION, String DELETED) {
        this.ITEMID = ITEMID;
        this.NAMEALIAS = NAMEALIAS;
        this.EXTENDEDDESCRIPTION = EXTENDEDDESCRIPTION;
        this.DELETED = DELETED;
    }

    public RetailItem() {
    }

    
    public String getITEMID() {
        return ITEMID;
    }

    public void setITEMID(String ITEMID) {
        this.ITEMID = ITEMID;
    }

    public String getNAMEALIAS() {
        return NAMEALIAS;
    }

    public void setNAMEALIAS(String NAMEALIAS) {
        this.NAMEALIAS = NAMEALIAS;
    }

    public String getEXTENDEDDESCRIPTION() {
        return EXTENDEDDESCRIPTION;
    }

    public void setEXTENDEDDESCRIPTION(String EXTENDEDDESCRIPTION) {
        this.EXTENDEDDESCRIPTION = EXTENDEDDESCRIPTION;
    }

    public String getDELETED() {
        return DELETED;
    }

    public void setDELETED(String DELETED) {
        this.DELETED = DELETED;
    }
}
