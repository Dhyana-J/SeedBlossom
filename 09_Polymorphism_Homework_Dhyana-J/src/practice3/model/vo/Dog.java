package practice3.model.vo;

public class Dog extends Animal{
	
	private static final String PLACE = "애견카페";
	private int weight;
	
	public Dog() {
		
	}
	
	public Dog(String name, String kinds, int weight) {
		super(name,kinds);
		this.weight = weight;
	}
	
	//final은 getter만 써준다 변경할 수 없으니
	//static을 이용해 객체마다 메소드가 생성될 필요가 없도록 한다.
	public static String getPLACE() {
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
		System.out.println(super.toString()+" 몸무게는 "+weight+"kg 이멍");
	}

}
