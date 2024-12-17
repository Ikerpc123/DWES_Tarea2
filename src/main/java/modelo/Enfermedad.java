package modelo;

public class Enfermedad {

	private Long id;
	private String nombre;
	private String sintomas;
	private boolean nociva;
	private String cod_planta;
	
	public Enfermedad() {
		super();
	}
	
	public Enfermedad(Long id, String nombre, String sintomas, boolean nociva, String cod_planta) {
		this.id = id;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.nociva = nociva;
		this.cod_planta = cod_planta;
	}
	
	public Enfermedad(String nombre, String sintomas, boolean nociva, String cod_planta) {
		super();
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.nociva = nociva;
		this.cod_planta = cod_planta;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public boolean isNociva() {
		return nociva;
	}

	public void setNociva(boolean nociva) {
		this.nociva = nociva;
	}

	public String getCod_planta() {
		return cod_planta;
	}

	public void setCod_planta(String cod_planta) {
		this.cod_planta = cod_planta;
	}
	
	
}
