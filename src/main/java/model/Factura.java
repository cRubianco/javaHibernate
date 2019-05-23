package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name="Factura")
@Table(name="factura")
public class Factura {

		// Factura
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="nativoDeBaseDeDatos")
	@GenericGenerator(name="nativoDeBaseDeDatos", strategy="native")
	private Integer id;
	
	@Column(name="Factura", length=25, nullable=false)
	@Type(type="string")
	private String numero;

	@Column(name="Fecha")
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name="idFact_Cliente", foreignKey=@ForeignKey(name="fk_id_factura_id_cliente"))
	private Cliente cliente;

	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Detalle> detalles;
	
		// constructores
	public Factura() {
		super();
	}

	public Factura(String numero) {
		this();
		this.numero = numero;
		this.fecha = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void addDetalle(Detalle detalle) {
		if (this.detalles==null) {
			this.detalles = new ArrayList<>();
		}
		detalle.setFactura(this);
		this.detalles.add(detalle);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Factura))
			return false;
		Factura other = (Factura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// gets y sets
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Detalle> getDetalle() {
		return detalles;
	}

	public void setDetalle(List<Detalle> detalle) {
		this.detalles = detalle;
	}
}
