package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadImage {

	public static void main(String[] args) throws Exception {
//		String html = "aaa<img src=\"http://latex.codecogs.com/gif.latex?%5Cfrac%7B%5Cpartial%20%7D%7B%5Cpartial%20x%7D\" alt=\"\\frac{\\partial }{\\partial x}\"></img>sf<img src=\"http://latex.codecogs.com/gif.latex?%5Csqrt%5B1%5D%7B2%7D\" alt=\"\\sqrt[1]{2}\"></img>";
//		System.out.println(filterString(html, "./ckimages"));
	}

//	public static String filterString(String _str, String _folderName) {//String _urlPath
//		String returnStr = _str;
//		Document doc = Jsoup.parse(_str);
//		Elements imgs = doc.select("img");
//		if(!new File(_folderName).exists())
//		{
//			new File(_folderName).mkdirs();
//		}
//		for (Element img : imgs) {
//			String url = img.attr("src");
//			if (url.toLowerCase().startsWith(
//					"http://latex.codecogs.com/gif.latex?")) {
//				String fileName = _folderName + File.separator +UUID.randomUUID().toString() + ".gif";
//				try {
//					DownloadImage.download(url, fileName);
//					img.attr("src", fileName);
//				} catch (Exception e) {}
//			}
//			
//		}
//		returnStr = doc.getElementsByTag("body").html().replaceAll("\r", "").replaceAll("\n", "");
//		return returnStr;
//	}
	
	public static String filterString(String _str, String _folderName, String _urlPath) {
		String returnStr = _str;
		Document doc = Jsoup.parse(_str);
		Elements imgs = doc.select("img");
		System.out.println("_folderName---"+_folderName);
		if(!new File(_folderName).exists())
		{
			new File(_folderName).mkdirs();
			System.out.println("CREATED!!!---"+_folderName);
		}
		for (Element img : imgs) {
			String url = img.attr("src");
			if (url.toLowerCase().startsWith("http://latex.codecogs.com/gif.latex?")) {
//				String fileName = _folderName + File.separator +UUID.randomUUID().toString() + ".gif";
				String filename = File.separator +UUID.randomUUID().toString() + ".gif";
				String fileName1 = _folderName + filename;
				String fileName2 = _urlPath + filename;
				try {
					DownloadImage.download(url, fileName1);
					img.attr("src", fileName2);
				} catch (Exception e) {}
			}
			
		}
		returnStr = doc.getElementsByTag("body").html().replaceAll("\r", "").replaceAll("\n", "");
		return returnStr;
	}
	
	public static void download(String urlString, String filename)
			throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		byte[] bs = new byte[1024];
		int len;
		OutputStream os = new FileOutputStream(filename);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
	}
	
}
