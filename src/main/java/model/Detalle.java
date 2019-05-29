package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name="Detalle")
@Table(name="detalle")

public class Detalle {

		//Detalle
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="nativoDeBaseDeDatos")
	@GenericGenerator(name="nativoDeBaseDeDatos", strategy="native")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="Det_Fact", foreignKey=@ForeignKey(name="fk_id_Detalle_id_Factura"))
	private Factura factura;
	

//    @JoinColumn(name = "idProducto")
//	@ManyToOne
//	@JoinColumn(name="idProducto", foreignKey=@ForeignKey(name="fk_idDetalle_idProducto"))
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idProducto", foreignKey=@ForeignKey(name="fk_idDetalle_idProducto"))
	private Producto producto;
    
	@Column(name="Ctdad", length=25, nullable=false)
	@Type(type="int")
	private int cantidad = 0;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idPrecio", foreignKey=@ForeignKey(name="fk_idDetalle_idPrecio"))
	private Precio precio;
	
	//Constrcutores
	public Detalle() {
		super();
	}
	
	public Detalle(Producto prod, int ctdad) {
		this();
		this.producto = prod;
		this.cantidad = ctdad;
		this.precio = prod.getPrecio();
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
		if (!(obj instanceof Detalle))
			return false;
		Detalle other = (Detalle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// get & set
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}
	
	

}
