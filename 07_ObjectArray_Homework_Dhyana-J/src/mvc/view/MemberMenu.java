package mvc.view;

import java.util.Scanner;
import mvc.model.vo.*;
import mvc.controller.MemberController;

public class MemberMenu {
	
	private MemberController mc = new MemberController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		
		while(true) {
			System.out.println();
			System.out.println("===== ȸ�� ���� �޴� =====");
			System.out.println("1. �ű� ȸ�� ���");
			System.out.println("2. ȸ�� ���� �˻�");
			System.out.println("3. ȸ�� ���� ����");
			System.out.println("4. ȸ�� ���� ����");
			System.out.println("5. ȸ�� ���� ���");
			System.out.println("9. ���α׷� ����");
			
			System.out.print("���ϴ� �޴� �Է� : ");
			int input = sc.nextInt();
			System.out.println();
			switch(input) {
			case 1: insertMember(); break;
			case 2:	searchMember(); break;
			case 3:	updateMember(); break;
			case 4:	deleteMember(); break;
			case 5:	printAllMember(); break;
			case 9:	System.out.println("���α׷��� �����մϴ�."); return;
				default : System.out.println("�߸� �����̽��ϴ�.");
			}
			
		}
		
	}
	
	public void insertMember() {
		//���� ȸ�� ���� �ִ� ȸ�� �� �Ѿ ��� return
		if(mc.getMemberCount()>mc.SIZE) {
			System.out.println("ȸ�� ���� �ִ��Դϴ�. �� �̻� �߰��� �� �����ϴ�.");
			return;
		}
		else {
			String id;
			String pw;
			String name;
			int age;
			char gender;
			String email;
			
			System.out.print("Id �Է� : ");
			id = sc.nextLine();
			
			if(mc.checkId(id)!=null) { //������ ���̵� �����ϴ� ���
				System.out.println("������ ���̵� �����մϴ�. ȸ����� ����");
			}
			else {
				sc.nextLine();
				System.out.print("Pw �Է� : ");
				pw = sc.nextLine();
				System.out.print("�̸� �Է� : ");
				name = sc.nextLine();
				System.out.print("���� �Է� : ");
				age = sc.nextInt();
				System.out.print("���� �Է� : ");
				sc.nextLine();
				gender = sc.nextLine().charAt(0);
				System.out.print("�̸��� �Է� : ");
				email = sc.nextLine();
				
				//��ü ���� �� �޼ҵ� ����
				mc.insertMember(new Member(id,pw,name,age,gender,email));
				
				System.out.println("ȸ�� ��Ͽ� �����߽��ϴ�.");
			}
		}
		
		
	}
	
	public void searchMember() {
		
		while(true) {
			
			System.out.println();
			System.out.println("====== ȸ�� ���� �˻� ======");
			System.out.println("1. ���̵�� �˻��ϱ�");
			System.out.println("2. �̸����� �˻��ϱ�");
			System.out.println("3. �̸��Ϸ� �˻��ϱ�");
			System.out.println("9. ���� �޴���");
			
			System.out.print("�޴� ���� : ");
			int menu = sc.nextInt(); //�޴��� �Է¹���
			
			if(menu == 1||menu==2||menu==3) {
				System.out.print("�˻� ���� : ");
				sc.nextLine(); //���� �������.
				String search = sc.nextLine();
			
				if(mc.searchMember(menu, search)==null) {
					System.out.println("�˻��� ����� �����ϴ�.");
				}else {
				//�˻��� ������ ���Ե� ��� ���� ���
				System.out.println(mc.searchMember(menu, search).information());
				}
				break;
			}
			else if (menu==9) {
				System.out.println("���� �޴��� ���ư��ϴ�.");
				return;
			}
			else {
				System.out.println("�߸� �����̽��ϴ�.");
			}
		
			
			
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			
			}
			
		
		

	}
	
	public void updateMember() {
		
		while(true) {
			System.out.println("====== ȸ�� ���� ���� ======");
			System.out.println("1. ��й�ȣ ����");
			System.out.println("2. �̸� ����");
			System.out.println("3. �̸��� ����");
			System.out.println("9. ���� �޴���");
			
			System.out.print("�޴� ���� : ");
			int menu = sc.nextInt();
			
			if(menu == 1||menu==2||menu==3) {
				sc.nextLine();//���ۺ������
				System.out.print("���� ȸ�� ���̵� : ");
				String userId = sc.nextLine();
				
				Member m = mc.checkId(userId);
				if(m == null) {
					System.out.println("������ ȸ���� �������� �ʽ��ϴ�.");
				}
				else {
					//���� ���� ��� �� ���泻�� �Է¹޴´�.
					System.out.println(m.information());
					System.out.print("���� ���� : ");
					String update = sc.nextLine();
					mc.updateMember(m, menu, update);
					System.out.println("ȸ�� ������ ����Ǿ����ϴ�.");
				}
			}
			else if (menu==9) {
				System.out.println("���� �޴��� ���ư��ϴ�.");
				return;
			}
			else {
				System.out.println("�߸� �Է��߽��ϴ�.");
			}
			
			
			
		
		}
	}
		
	
	public void deleteMember() {
		
		sc.nextLine();
		System.out.print("������ ȸ�� ���̵� : ");
		String userId = sc.nextLine();
		
		if(mc.checkId(userId)==null) {
			System.out.println("������ ȸ���� �������� �ʽ��ϴ�.");
			return;
		}
		
		else {
			System.out.print("���� �����Ͻðڽ��ϱ�?(y/n):");
			String input = sc.nextLine().toUpperCase();
			
			if(input.equals("Y")) {
				
				mc.deleteMember(userId);
				
				System.out.println("ȸ�� ������ �����Ǿ����ϴ�.");
			}
			else {
				System.out.println("��ҵǾ����ϴ�.");
				return;
			}
		}
	}
	
	public void printAllMember() {
		
		for(int i = 0; i<mc.getMemberCount();i++) {
			System.out.println(mc.getMem()[i].information());
		}
		
	}

}
