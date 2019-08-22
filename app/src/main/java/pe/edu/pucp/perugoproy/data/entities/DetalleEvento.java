package pe.edu.pucp.perugoproy.data.entities;

import java.util.List;

public class DetalleEvento {
    private int idDetalleEvento;
    private int idEvento;
    private List<String> comentarios;


    public int getIdDetalleEvento() {
        return idDetalleEvento;
    }

    public void setIdDetalleEvento(int idDetalleEvento) {
        this.idDetalleEvento = idDetalleEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }
}
