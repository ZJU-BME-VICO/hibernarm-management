package org.hibernarm.management.test;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.hibernarm.management.model.ARMBean;
import org.hibernarm.management.model.ArchetypeBean;

public class ArchetypeTestBase {
	protected static Map<String, ArchetypeBean> archetypes = new HashMap<String, ArchetypeBean>();
	protected static Map<String, ARMBean> arms = new HashMap<String, ARMBean>();

	protected static void initial() throws IOException {
		ArchetypeBean archetype = new ArchetypeBean();
		archetype.setName("openEHR-EHR-OBSERVATION.blood_pressure.v2");
		archetype.setContent(readLines("org/hibernarm/management/test/openEHR-EHR-OBSERVATION.blood_pressure.v2.adl"));
		archetypes.put("openEHR-EHR-OBSERVATION.blood_pressure.v2", archetype);

		ARMBean arm = new ARMBean();
		arm.setName("openEHR-EHR-OBSERVATION.blood_pressure.v1");
		arm.setContent(readLines("org/hibernarm/management/test/openEHR-EHR-OBSERVATION.blood_pressure.v1.arm.xml"));
		arms.put("openEHR-EHR-OBSERVATION.blood_pressure.v1", arm);
	}

//	protected void createTestBase() throws IOException {
//		ArchetypeImpl impl = new ArchetypeImpl();
//		for (String key : archetypes.keySet()) {
//			impl.addArchetype(archetypes.get(key));
//		}
//		for (String key : arms.keySet()) {
//			impl.addArm(arms.get(key));
//		}
//	}

//	protected void cleanTestBase() {
//		ArchetypeImpl impl = new ArchetypeImpl();
//		for (String key : archetypes.keySet()) {
//			impl.deleteArchetype(key);
//		}
//		for (String key : arms.keySet()) {
//			impl.deleteArm(key);
//		}
//	}

	protected static String readLines(String name) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(name),"utf-8"));

		String line = reader.readLine();
		while (line != null) {
			result.append(line);
			result.append("\n");
			line = reader.readLine();
		}
		return result.toString();
	}

}
