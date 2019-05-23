package model;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity(name="Cliente")
@Table(name="cliente")
public class Cliente {

		// atributos
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="nativoDeBaseDeDatos")
	@GenericGenerator(name="nativoDeBaseDeDatos", strategy="native")
	private Long id;

	@Column(name="Código")
	private String codigo;
	
	@Column(name="Apellido")
	private String apellido;

	@Column(name="Nombre")
	private String nombre;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idCuenta", foreignKey=@ForeignKey(name="fk_idCliente_idCuenta"))
	private Cuenta cuenta;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Factura> facturas;

	
		// constructores
	public Cliente() {
		super();
	}
	
	public Cliente(String codigo, String apellido, String nombre, String nroCuenta) {
		this();
		this.codigo = codigo;
	    this.apellido = apellido;
	    this.nombre = nombre;
	    this.cuenta = new Cuenta(nroCuenta, this);
	    this.facturas = new ArrayList<>();
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
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void agregarFactura(Factura factura) {
    	this.facturas.add(factura);
    	factura.setCliente(this);
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> factura) {
		this.facturas = factura;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	
}	