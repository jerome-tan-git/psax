package test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asso.model.Article;

import util.CONSTANT;
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
//		info.setUsername("乔峰");
//		info.setPassword("111");
//		mb.setuInfo(info);
//		System.out.println(mb.execute());
		
//		MemberEdit mb = new MemberEdit();
//		MemberInfo info = new MemberInfo();
//		info.setC_tel("13612121212");
//		info.setId(5);
//		info.setC_addr("浦电路438号");
//		info.setC_name("杨过");
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
//		info.setExamname("食品添加剂考试");
//		info.setExamitemid(10);
//		info.setQuestion("以下哪些添加剂属于婴幼儿食品禁用添加剂？");
//		info.setRefsnum(6);
//		info.setRefs("a)谷氨酸钠,b)焦糖色,c)柠檬酸,d)亚硝酸钠,e)山梨酸钾,f)苯甲酸钠");
//		info.setCategory(3);
//		info.setAnswers("abcdef");		
		
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamname("食品添加剂考试");
//		info.setGroupid(2);		
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamid(2);
//		info.setQuestion("以下哪些添加剂属于婴幼儿食品禁用添加剂？");
//		info.setCategory(3);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamid(2);
//		info.setQuestion("以下哪些添加剂属于婴幼儿食品禁用添加剂？");
//		info.setCategory(3);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		//insert
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setRefs("a)谷氨酸钠,b)焦糖色,c)柠檬酸,d)亚硝酸钠,e)山梨酸钾,f)苯甲酸钠");
//		info.setAnswers("abcdef");	
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		//update
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setExamid(2);	
//		info.setCategory(3);
//		info.setQuestion("以下哪些添加剂属于婴幼儿食品禁用添加剂？");
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
		//update
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setRefid(27);
//		info.setRef("亚硝酸钠2");
//		info.setIstrue(0);
//		eil.seteInfo(info);
//		System.out.println(eil.execute());
		
		//delete
//		ExamItemsList eil = new ExamItemsList();
//		ExamBuiltInfo info = new ExamBuiltInfo();
//		info.setExamitemid(8);
//		info.setRefid(27);
//		info.setRef("亚硝酸钠2");
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
		
//		String test = "asd,cv,fdgsey,";
//		test = test.substring(0,test.length()-1);
//		System.out.println(test);
		
		
//		System.out.println("today----"+CONSTANT.getTodayDate());
		
		
//		String date = "2012-12-23 11:12:56";
//		if(date.length()>=10){
//			System.out.println("YEAR="+date.substring(0,4));
//			System.out.println("MONTH="+date.substring(5, 7));
//			System.out.println("DAY="+date.substring(8,10));
//		}
		
		List<String> arts = new ArrayList<String>();
		arts.add("2012-08-24 12:51:12");
		arts.add("2012-09-12 09:45:18");
		arts.add("2012-12-22 14:05:12");
		arts.add("1981-06-17 02:15:49");
		arts.add("2013-01-23 15:16:46");
		arts.add("2013-12-24");
		arts.add("2013-05-14 10:12:56");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		List<Date> datelist = new ArrayList<Date>();
		 /**字符串转时间**/  
		for(String art:arts){
			try {
				datelist.add((Date) format.parse(art));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		/**打印时间**/  
        System.out.println("排序前：");  
        for(Date d:datelist){  
            System.out.println(format.format(d));  
        }  
        /**冒泡排序**/  
        Date tempDate = null;  
        for(int i=datelist.size()-1; i>0; --i) {  
           for(int j=0; j<i; ++j) {  
                /**从大到小的排序**/  
                if(datelist.get(j+1).after(datelist.get(j))){  
                    tempDate = datelist.get(j);  
                    datelist.set(j, datelist.get(j+1));  
                    datelist.set(j+1, tempDate);  
                }else{  
                /**从小到大**/  
//                  tempDate = dateList.get(j);  
//                  dateList.set(j, dateList.get(j+1));  
//                  dateList.set(j+1, tempDate);  
                }  
            }  
        }  
        /**打印排序之后的时间**/
        System.out.println("排序后：");  
        for(Date d:datelist){  
            System.out.println(format.format(d));  
        }  
		
		
	}

}
