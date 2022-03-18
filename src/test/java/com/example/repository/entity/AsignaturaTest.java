package com.example.repository.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AsignaturaTest {
	
	@Test
	void test() {
	Asignatura a1= new Asignatura();
	
	a1.setId(1);
	assertEquals(1, a1.getId(), "Mismo id");
	
	String nombre="Nombre Prueba";
	a1.setNombre(nombre);
	assertEquals(nombre, a1.getNombre(), "Mismo error");
	
	
	String descripcion="Descripcion Prueba";
	a1.setDescripcion(descripcion);
	assertEquals(descripcion, a1.getDescripcion(), "Misma descripcion");
	
	Asignatura a2=new Asignatura();
	a2.setId(2);
	a2.setNombre(nombre+"222");
	a2.setDescripcion(descripcion+"2");
	assertEquals(a1.hashCode(), a2.hashCode(),"Mismo hash code");
	
	assertEquals(a1,a1,"Mismo objeto");
	
	assertNotEquals(a1, nombre, "Distinto objeto");
	
	a1.setId(null);
	
	assertNull(a1.getId(), "id de e1 is null");
	assertNotEquals(a1, a2, "a1 a null y a2 no");
	
	}
}
