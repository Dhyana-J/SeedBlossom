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
		return "���� �̸��� "+name+"�̰�, ������ "+kinds+"�̿���";
	}

	
//	abstract method�� ��Ӱ� overriding�� �����ϹǷ�, public �Ǵ� protected�� ����!
	public abstract void speak();
	
}
