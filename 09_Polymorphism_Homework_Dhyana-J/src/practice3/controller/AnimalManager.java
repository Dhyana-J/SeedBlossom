package practice3.controller;
import practice3.model.vo.*;

public class AnimalManager {
	public static void main(String[] args) {
		
		Animal[] a = new Animal[5];
		
		a[0] = new Dog("��¦","�ڽ���Ʈ",5);
		a[1] = new Dog("���幵","�ù�",15);
		a[2] = new Cat("����","���þȺ��","���� �Ӹ� ��","í��");
		a[3] = new Cat("�긮��","�긮Ƽ�ü����","��ũ�� �Ļ� ��","���ݸ�");
		a[4] = new Cat("������","��","ħ�� ��","������ �󱼸� �˳�");
		
		for(Animal e : a) {
			e.speak();
		}
		
	}
}
