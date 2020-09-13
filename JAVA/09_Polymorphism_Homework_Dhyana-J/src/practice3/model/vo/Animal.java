package practice3.model.vo;

public abstract class Animal {

	private String name;
	private String kinds;

	Animal() {

	}

	Animal(String name, String kinds){
		this.name = name;
		this.kinds = kinds;
	}

	
	@Override
	public String toString() {
		return "저의 이름은 "+name+"이고, 종류는 "+kinds+"이에오";
	}

	
//	abstract method는 상속과 overriding을 강제하므로, public 또는 protected만 가능!
	public abstract void speak();
	
}
