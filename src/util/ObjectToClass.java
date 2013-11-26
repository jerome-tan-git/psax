package util;

import com.asso.model.MemberInfo;

public class ObjectToClass {

	public static MemberInfo o2memberinfo(Object[] object){
		MemberInfo mi = new MemberInfo();
		mi.setId((Integer) object[0]);
		mi.setC_name((String)object[1]);     
        mi.setC_tel((String)object[2]);
		mi.setC_addr((String)object[3]);
		mi.setC_email((String)object[4]);
		mi.setContactperson((String)object[5]);
		mi.setP_mp((String)object[6]);
		mi.setP_email((String)object[7]);
		mi.setP_tel((String)object[8]);
		mi.setC_logo((String)object[9]);
		return mi;
	}
	
}
