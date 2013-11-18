package org.hibernarm.management.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.serialize.ADLSerializer;
import org.openehr.rm.support.identification.ArchetypeID;

import se.acode.openehr.parser.ADLParser;

public class ArchetypeUtil {
	private static Logger logger = Logger.getLogger(ArchetypeUtil.class.getName());
	
	private Archetype archetype = null;
	private String archetypeId = "";
	private String archetypeContent = "";

	public ArchetypeUtil(File archetypeFile) {
		try {
			ADLParser parser = new ADLParser(archetypeFile);
			archetype = parser.parse();
			ArchetypeID archetypeIdNode = archetype.getArchetypeId();
			archetypeId = archetypeIdNode.toString();	
			ADLSerializer adlSerilizer = new ADLSerializer();
			archetypeContent = adlSerilizer.output(archetype);
		} catch (Throwable e) {
			archetype = null;
			archetypeId = "";
			archetypeContent = "";
			logger.error("ArchetypeUtil error", e);
		}
	}

	public String getArchetypeId() {
		return archetypeId;
	}
	
	public String getArchetypeContent() {	
		return archetypeContent;
	}
}
