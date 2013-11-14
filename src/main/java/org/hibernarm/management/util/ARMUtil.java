package org.hibernarm.management.util;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ARMUtil {
	private Document doc = null;
	private String archetypeId = "";
	private String armContent = "";

	public ARMUtil(File armFile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(armFile);
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node classNode = (Node) xpath.evaluate("/hibernate-mapping/class",
					doc, XPathConstants.NODE);
			NamedNodeMap classNodeAttributes = classNode.getAttributes();
			Node classNodeNameNode = classNodeAttributes.getNamedItem("name");
			archetypeId = classNodeNameNode.getNodeValue();

			DOMSource domSource = new DOMSource(doc);
			StringWriter writerStr = new StringWriter();
			Result resultStr = new StreamResult(writerStr);
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFactory.newTransformer();
			transFormer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transFormer.setOutputProperty(OutputKeys.INDENT, "yes");
			transFormer.transform(domSource, resultStr);
			armContent = writerStr.getBuffer().toString();
		} catch (Exception e) {
			doc = null;
			archetypeId = "";
			armContent = "";
		}
	}

	public String getArchetypeId() {
		return archetypeId;
	}

	public String getARMContent() {
		return armContent;
	}
}
