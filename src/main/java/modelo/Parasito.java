package modelo;

public class Parasito {
	
	private Long id;
	private String nombre;
	private String color;
	private Long idEnfermedad;
	
	public Parasito() {
		super();
	}
	
	public Parasito(String nombre, String color) {
		super();
		this.nombre = nombre;
		this.color = color;
	}
	
	public Parasito(Long id, String nombre, String color) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.color = color;
	}
	
	public Parasito(String nombre, String color, Long idEnfermedad) {
		super();
		this.nombre = nombre;
		this.color = color;
		this.idEnfermedad = idEnfermedad;
	}
	
	public Parasito(Long id, String nombre, String color, Long idEnfermedad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.color = color;
		this.idEnfermedad = idEnfermedad;
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
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public Long getIdEnfermedad() {
		return idEnfermedad;
	}
	
	public void setIdEnfermedad(Long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

}
