package com.kh.chap01_beforeVSafter.after;


import com.kh.chap01_beforeVSafter.after.model.vo.Desktop;
import com.kh.chap01_beforeVSafter.after.model.vo.SmartPhone;
import com.kh.chap01_beforeVSafter.after.model.vo.Tv;

public class Run {

	
	public static void main(String[] args) {
		
		Desktop d = new Desktop("�Ｚ","d-01","¯¯����ũž",1500000,true);
		
		Tv t = new Tv("LG","t-01","��θ�Ƽ��",3500000,60);
		
		SmartPhone s = new SmartPhone("Apple","s-01","������SE2",1000000,"SKT");
		
		System.out.println(d.information());
		System.out.println(t.information());
		System.out.println(s.information());
		
		
		
		/*
		 * ����� ����
		 * - ���� ���� ���� �ڵ�� ���ο� Ŭ���� �ۼ� ����!
		 * - �ڵ带 ���������� �����ϱ� ������ �ڵ��� �߰��� ������ �����ϴ�.
		 * --> �ڵ��� �ߺ��� �����Ͽ� ���α׷��� ���꼺�̳� ���������� ũ�� �⿩
		 * 
		 * * ����� Ư¡
		 * - Ŭ�������� ����� ���߻���� �Ұ��ϴ�. �ϳ��� Ŭ������ ��� �����ϴ�.
		 * - �ƹ��� ����̶�� �ص�, �θ�Ŭ���� �ʵ尡 private�̸� �ڽ��� �������� �Ұ��ϴ�. protected�� ����.
		 * - ��õǾ������� �ʾƵ�, ��� Ŭ������ ���� Object Ŭ������ �ļ��̴�. ������ ����, Object!
		 * 
		 */
		

	}

}
