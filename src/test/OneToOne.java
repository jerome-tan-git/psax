package test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import com.asso.action.MemberBuilt;
//import com.asso.action.MemberEdit;
//import com.asso.action.MemberLoad;
//import com.asso.manager.UserManager;
//import com.asso.manager.impl.UserManagerImpl;
//import com.asso.model.MemberInfo;
//import com.asso.model.User;
//import com.maggie.ssh1.vo.UserRegisterInfo;


public class OneToOne {
	
//	private static Session session;
	
	
	public static void main(String[] args){
		
//		session = HibernateUtil.getSessionFactory().openSession();
//
//		User u = new User();
//		u.setPassword("123");
//		u.setUsername("mama");
//
//	
//		session.beginTransaction();
////		session.delete(u);
////		session.get(User.class, "123");
////		session.saveOrUpdate(p);
//		session.save(u);
//		session.getTransaction().commit();
//
////		System.out.println("User:"+u.toString());
		
		
		
		
//		User u = new User();
//		u.setPassword("121212");
//		u.setUsername("tiffany");
//		
//		UserManager um = new UserManagerImpl();
//		try {
//			if(um.exists(u)){
//				System.out.println("EXISTS?--->true");
//			}else{
//				um.add(u);
//			}
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		UserAction ua = new UserAction();
//		UserRegisterInfo info = new UserRegisterInfo();
//		info.setUsername("miumiu");
//		info.setPassword("55555");
//		info.setPassword1("55555");
//		ua.setuInfo(info);
//		System.out.println(ua.execute());
		
		
		
//		UserLogin ua = new UserLogin();
//		UserRegisterInfo info = new UserRegisterInfo();
//		info.setUsername("admin");
//		info.setPassword("999");		
//		ua.setuInfo(info);
//		System.out.println(ua.execute());
		
//		UserBuilt ua = new UserBuilt();
//		UserRegisterInfo info = new UserRegisterInfo();
//		info.setUsername("miaomiao");
//		info.setPassword("990");		
//		ua.setuInfo(info);
//		System.out.println(ua.execute());
		
//		MemberBuilt mb = new MemberBuilt();
//		UserRegisterInfo info = new UserRegisterInfo();
//		info.setUsername("�Ƿ�");
//		info.setPassword("111");
//		mb.setuInfo(info);
//		System.out.println(mb.execute());
		
//		MemberEdit mb = new MemberEdit();
//		MemberInfo info = new MemberInfo();
//		info.setC_tel("13612121212");
//		info.setId(5);
//		info.setC_addr("�ֵ�·438��");
//		info.setC_name("���");
//		mb.setmemberinfo(info);
//		System.out.println(mb.execute());
		
//		MemberLoad ml = new MemberLoad();
//		UserRegisterInfo info = new UserRegisterInfo();
//		info.setId(2);
//		ml.setuInfo(info);
//		System.out.println(ml.execute());
		
		
//		UserLogin ua = new UserLogin();
//		UserRegisterInfo info = new UserRegisterInfo();
//		info.setUsername("admin");
//		info.setPassword("999");		
//		ua.setuInfo(info);
//		System.out.println(ua.execute());
		
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamname("test");
//		ExamItemsList eil = new ExamItemsList();
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamid(2);
//		info.setGroupid(2);
//		info.setExamname("ʳƷ��Ӽ�����");
//		info.setExamitemid(10);
//		info.setQuestion("������Щ��Ӽ�����Ӥ�׶�ʳƷ������Ӽ���");
//		info.setRefsnum(6);
//		info.setRefs("a)�Ȱ�����,b)����ɫ,c)������,d)��������,e)ɽ�����,f)��������");
//		info.setCategory(3);
//		info.setAnswers("abcdef");		
		
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamname("ʳƷ��Ӽ�����");
//		info.setGroupid(2);		
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamid(2);
//		info.setQuestion("������Щ��Ӽ�����Ӥ�׶�ʳƷ������Ӽ���");
//		info.setCategory(3);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamid(2);
//		info.setQuestion("������Щ��Ӽ�����Ӥ�׶�ʳƷ������Ӽ���");
//		info.setCategory(3);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		//insert
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setRefs("a)�Ȱ�����,b)����ɫ,c)������,d)��������,e)ɽ�����,f)��������");
//		info.setAnswers("abcdef");	
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		//update
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setExamid(2);	
//		info.setCategory(3);
//		info.setQuestion("������Щ��Ӽ�����Ӥ�׶�ʳƷ������Ӽ���");
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
		//update
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setRefid(27);
//		info.setRef("��������2");
//		info.setIstrue(0);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
		//delete
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setRefid(27);
//		info.setRef("��������2");
//		info.setIstrue(0);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
		//delete
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(9);			
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
		//deleteRefByItem
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(9);			
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
		//load
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
////		info.setRefid(30);
////		info.setExamitemid(1);
////		info.setCategory(2);
//		info.setExamid(0);
////		info.setExamitemid(6);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
////		System.out.println(eil.getItem().toString());
////		for(ExamItem i:eil.getItemlist()){
////			System.out.println(i.toString());
////		}
////		for(ExamItem i:eil.getItemf().keySet()){
////			System.out.println(i.toString());
////			for(ExamRef r:eil.getItemf().get(i)){
////				System.out.println("----"+r.toString());
////			}
////		}
//		System.out.println("--**--hashmap--size-"+eil.getItemlistf().size());
//		for(HashMap<ExamItem,List<ExamRef>> f:eil.getItemlistf()){			
//			for(ExamItem i:f.keySet()){
//				System.out.println("--**--"+i.toString());
//				for(ExamRef r:f.get(i)){
//					System.out.println("----"+r.toString());
//				}
//			}
//		}
		
//		List<String> test = new ArrayList<String>();
//		test.add("295");
//		test.add("287");
//		test.add("288");
//		List<Integer> indexes = new ArrayList<Integer>();
//		indexes.add(test.indexOf("295"));
//		indexes.add(test.indexOf("287"));
//		Collections.reverse(indexes);
//		for(int index : indexes){
//			System.out.println(index);
//			test.remove(index);
//			System.out.println("After One Remove-"+test.toString());
//		}
//			
////		System.out.println("ToString------ "+test.toString());
////		System.out.println("INDEX bbb is = "+test.indexOf("bbb"));
////		System.out.println("DELETE bbb = "+test.remove(index));
////		test.add(index, "ggg");
//		System.out.println("REPLACEMENT--- "+test);
		
		String test = "asd,cv,fdgsey,";
		test = test.substring(0,test.length()-1);
		System.out.println(test);
		
		
	}

}
