package model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Precio")
@Table(name="precio")
public class Precio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="nativoDeBaseDeDatos")
	@GenericGenerator(name="nativoDeBaseDeDatos", strategy="native")
	private Integer id;
	
	@Column(name="Monto")
	private Integer monto;
	
	@Column
	private LocalDateTime fecha;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idProducto", foreignKey=@ForeignKey(name="fk_idPrecio_idProducto"))
	private Producto producto;
	
	public Precio() {
		super();
	}
	
	public Precio(int prec, LocalDateTime fecha) {
		this();
		this.monto = prec;
		this.fecha = fecha;
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
		if (!(obj instanceof Precio))
			return false;
		Precio other = (Precio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// get & set
	public int getPrecio() {
		return monto;
	}

}
