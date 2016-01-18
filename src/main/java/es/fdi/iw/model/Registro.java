package es.fdi.iw.model;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name="allRegistros",
            query="select r from Registro r"),
    @NamedQuery(name="unRegistro",
    		query="select r from Registro r where r.id = :registroParam"),
    @NamedQuery(name="delRegistro", 
     		query="delete  from Registro r where r.id= :idRegistro"),
     @NamedQuery(name="pertenece", 
     		query="select r from Registro r where r.usuario.id= :usuarioParam and r.actividad.id= :actividadParam"),
     		@NamedQuery(name="delRegistro2", 
     		query="delete  from Registro r where r.usuario.id= :usuarioParam and r.actividad.id= :actividadParam"),		
    
})

public class Registro {
	
	long id;
	Usuario usuario;
	Actividad actividad;
	List<Pago> pagos;

	public static Registro crearRegistro(Actividad  actv, Usuario user) {
		
		Registro r = new Registro();
		r.actividad=actv;
		r.usuario=user;
		return r;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	@ManyToOne(targetEntity=Usuario.class, cascade = CascadeType.ALL)
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario u) {
		this.usuario = u;
	}
	
	@ManyToOne(targetEntity=Actividad.class, cascade = CascadeType.ALL)
	public Actividad getActividad() {
		return actividad;
	}
	
	public void setActividad(Actividad a) {
		this.actividad = a;
	}
	
	@OneToMany(targetEntity=Pago.class)
	@JoinColumn(name="id_registro")
	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	
	
	
	
}
