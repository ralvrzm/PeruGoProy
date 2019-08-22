package pe.edu.pucp.perugoproy.data.entities;

import java.sql.Timestamp;
import java.util.List;

public class DetalleEvento {
    private String idDetalleEvento;
    private String idEvento;
    private String longitud;
    private String latitud;
    private String descripcion;
    private String referencia;

    public String getIdDetalleEvento() {
        return idDetalleEvento;
    }

    public void setIdDetalleEvento(String idDetalleEvento) {
        this.idDetalleEvento = idDetalleEvento;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    //private List<String> comentarios;


}
