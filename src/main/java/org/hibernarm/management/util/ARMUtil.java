package org.hibernarm.management.util;

import java.io.File;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class ARMUtil {
	private static Logger logger = Logger.getLogger(ARMUtil.class.getName());
	
	private Document doc = null;
	private String archetypeId = "";
	private String armContent = "";

	public ARMUtil(File armFile) {
		try {
			SAXReader saxReader = new SAXReader();
			doc = saxReader.read(armFile);
			Attribute classNode = (Attribute) doc.selectSingleNode("/hibernate-mapping/class/@name");
			archetypeId = classNode.getText();			
			armContent = doc.asXML();
		} catch (Exception e) {
			doc = null;
			archetypeId = "";
			armContent = "";
			logger.error("ARMUtil error", e);
		}
	}

	public String getArchetypeId() {
		return archetypeId;
	}

	public String getARMContent() {
		return armContent;
	}
}
