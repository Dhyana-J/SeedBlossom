package practice3.model.vo;

public class Dog extends Animal{
	
	private static final String PLACE = "�ְ�ī��";
	private int weight;
	
	public Dog() {
		
	}
	
	public Dog(String name, String kinds, int weight) {
		super(name,kinds);
		this.weight = weight;
	}
	
	//final�� getter�� ���ش� ������ �� ������
	public String getPLACE() {
		return PLACE;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public void speak() {
		System.out.println(super.toString()+" �����Դ� "+weight+"kg �̸�");
	}

}