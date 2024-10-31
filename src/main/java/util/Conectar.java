package util;

import java.io.Serializable;

public class Conectar implements Serializable{
	
	private static Conectar instancia;
	
	private Conectar() {
		
	}
	
	public static Conectar getConectar() {
		if(instancia == null)
		{
			instancia = new Conectar();
		}
		return instancia;
	}
	
}
