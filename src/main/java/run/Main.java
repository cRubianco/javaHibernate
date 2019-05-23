package run;

import java.time.LocalDateTime;

import org.hibernate.Session;

import hibernate.HibernateUtil;
import model.Cliente;
import model.Detalle;
import model.Factura;
import model.Precio;
import model.Producto;

public class Main {

	public static void main(String[] args) {
		Cliente pablito = new Cliente("A1010","Pablo", "Clavo", "A01001");	
		Cliente pedrito = new Cliente("A2011","Pedro", "Pica", "A01002");
		Cliente pepito = new Cliente("A1012","Pepe", "Piedra", "A01003");
		
		Factura fac1 = new Factura("1223");
		Factura fac2 = new Factura("1231");
		Factura fac3 = new Factura("1231");
		
		pablito.agregarFactura(fac1);
		pepito.agregarFactura(fac2);
		pablito.agregarFactura(fac3);
		
		LocalDateTime fec1 = LocalDateTime.of(2017, 9, 18, 18, 50); 
		LocalDateTime fec2 = LocalDateTime.of(2017, 8, 8, 11, 50); 
		LocalDateTime fec3 = LocalDateTime.of(2017, 7, 7, 14, 50);
//		LocalDateTime fec4 = LocalDateTime.of(2017, 7, 7, 19, 50); 
		
		Precio prec1 = new Precio(250, fec1);
		Precio prec2 = new Precio(5250, fec2);
		Precio prec3 = new Precio(9250, fec3);
							
		Producto prod1 = new Producto("mem01", "Memoria DDR1", prec1, 250);
		Producto prod2 = new Producto("teve1", "Tv Led 32", prec2, 5250);
		Producto prod3 = new Producto("hela1", "Heladera D1", prec3, 9250);
		
		fac1.addDetalle(new Detalle(prod2, 1));
		fac2.addDetalle(new Detalle(prod3, 1));
		fac3.addDetalle(new Detalle(prod1, 1));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			session.save(pablito);
			session.save(pedrito);
			session.save(pepito);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		HibernateUtil.getSessionFactory().close();
	}


}
