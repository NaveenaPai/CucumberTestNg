package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocToHTMLConvertUtility {

	public static void main(String[] args) throws Exception {
		 String filePath = System.getProperty("user.dir")+ "/src/main/resources/screenshots/";
		 String fileName="ScreenShot_2.docx";
		 String name  = fileName.substring(0, fileName.lastIndexOf("."));
		  docx(filePath ,fileName ,name +".htm");
	}
	
	public static void docx(String filePath ,String fileName,String htmlName) throws Exception{
		 String file = filePath + fileName;
		 File f = new File(file); 
		 // Loading word Document generation  XWPFDocument Object 
		 InputStream in = new FileInputStream(f);
		 XWPFDocument	 document = new XWPFDocument(in);
		 // )  Analyse  XHTML Configure  ( Set here IURIResolver To set the directory where the pictures are stored )
		 File imageFolderFile = new File(filePath);
		 XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
		 options.setExtractor(new FileImageExtractor(imageFolderFile));
		 options.setIgnoreStylesIfUnused(false);
		 options.setFragment(true);
		 // )  Will  XWPFDocument Convert to XHTML
		 OutputStream out = new FileOutputStream(new File(filePath + htmlName));
		 XHTMLConverter.getInstance().convert(document, out, options);
		 }
}
