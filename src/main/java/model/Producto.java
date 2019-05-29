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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name="Producto")
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="nativoDeBaseDeDatos")
	@GenericGenerator(name="nativoDeBaseDeDatos", strategy="native")
	private Integer id;
	
	@Column(name="Código")
	@Type(type="string")
	private String codigo;
	
	@Column(name="Descripción", length=255, nullable=false)
	@Type(type="string")
	private String descripcion;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idPrecio", foreignKey=@ForeignKey(name="fk_idProducto_idPrecio"))
	private Precio precio;
	
	@ManyToMany(mappedBy="productos")
	private List<Proveedor> proveedores = new ArrayList<Proveedor>();
	
	//Constrcutores
	public Producto() {
		super();
	}
	public Producto(String codigo, String descrip, Precio precio, int i) {
		this();
		this.codigo = codigo;
		this.descripcion = descrip;
		this.precio = precio;
	}
	
	// metodos	


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
		if (!(obj instanceof Producto))
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// get & set
	public String getCodigo() {
		return codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Precio getPrecio() {
		return precio;
	}
	public void setPrecio(Precio precio) {
		this.precio = precio;
	}
	public List<Proveedor> getProveedores() {
		return proveedores;
	}
	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

}
