package org.hibernarm.management.util;

import java.io.File;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.serialize.ADLSerializer;
import org.openehr.rm.support.identification.ArchetypeID;

import se.acode.openehr.parser.ADLParser;

public class ArchetypeUtil {
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
		} catch (Exception e) {
			archetype = null;
			archetypeId = "";
			archetypeContent = "";
		}
	}

	public String getArchetypeId() {
		return archetypeId;
	}
	
	public String getArchetypeContent() {	
		return archetypeContent;
	}
}
