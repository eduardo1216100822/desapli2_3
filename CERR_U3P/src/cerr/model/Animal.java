package cerr.model;

public class Animal {
	private Long id;
	private String name;
	private String home;
	private int age;
	
	public Animal(Long id, String name, String home, int age) {
		super();
		this.id = id;
		this.name = name;
		this.home = home;
		this.age = age;
	}
	
	public Animal() {
		this(0L,"","",0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", home=" + home + ", age=" + age + "]";
	}
		
	
}
