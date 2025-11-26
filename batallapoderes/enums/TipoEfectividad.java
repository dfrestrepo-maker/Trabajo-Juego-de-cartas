package com.batallapoderes.enums;

public enum TipoEfectividad {
    SUPER_EFECTIVO("¡Es súper efectivo!"),
    NORMAL("Daño normal"),
    POCO_EFECTIVO("No es muy efectivo...");
    
    private String mensaje;
    
    TipoEfectividad(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getMensaje() {
        return mensaje;
    }
}
