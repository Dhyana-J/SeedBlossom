package practice3.controller;
import practice3.model.vo.*;

public class AnimalManager {
	public static void main(String[] args) {
		
		Animal[] a = new Animal[5];
		
		a[0] = new Dog("뽀짝","닥스훈트",5);
		a[1] = new Dog("뭉흐뭇","시바",15);
		a[2] = new Cat("개냥","러시안블루","주인 머리 위","챠콜");
		a[3] = new Cat("브리퉁","브리티시숏헤어","싱크대 식빵 옆","초콜릿");
		a[4] = new Cat("샴땡이","샴","침대 위","베이지 얼굴만 검냥");
		
		for(Animal e : a) {
			e.speak();
		}
		
	}
}
