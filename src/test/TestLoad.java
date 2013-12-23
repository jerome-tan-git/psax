package test;

import java.util.List;
import java.util.Random;

import org.apache.catalina.session.StandardSessionFacade;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class TestLoad implements TemplateMethodModel {
final static Random rnd_seed = new Random(System.currentTimeMillis());
	
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateMethodModel#exec(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public Object exec(List args) throws TemplateModelException {
//		System.out.println(args.get(0)); 
//		System.out.println(((String)(args.get(0)))); 
		return rnd_seed.nextInt();
	}
}
