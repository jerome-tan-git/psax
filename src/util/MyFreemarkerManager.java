package util;

import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;

import test.TestLoad;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class MyFreemarkerManager extends FreemarkerManager{
	@Override
	protected Configuration createConfiguration(ServletContext servletContext)
			throws TemplateException {
		Configuration configuration = super.createConfiguration(servletContext);
//		configuration.setSharedVariable("rand", new TestLoad());
		configuration.setSharedVariable("decode", new PicURLparse());
		configuration.setSharedVariable("stats", new UserStats());
		configuration.setSharedVariable("hasCorpForm", new CorpForm());
		
		return configuration;
	}
}
