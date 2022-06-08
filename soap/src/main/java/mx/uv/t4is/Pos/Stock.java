package mx.uv.t4is.Pos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    private int tortillas;
    private int totopos;

    public Stock() { }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getTotopos() {
        return totopos;
    }

    public void setTotopos(int totopos) {
        this.totopos = totopos;
    }

    public int getTortillas() {
        return tortillas;
    }

    public void setTortillas(int tortillas) {
        this.tortillas = tortillas;
    }
}
