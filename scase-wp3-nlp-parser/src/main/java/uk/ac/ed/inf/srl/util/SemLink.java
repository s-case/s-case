package uk.ac.ed.inf.srl.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SemLink {

	Map<String, List<String>> pb2verbnet;
        Map<String, Map<String, List<String>>> pbargs2verbnet;
	
	public static void main(String[] args) {
		SemLink nb = new SemLink("nombank.1.0");
	}
	
	public SemLink(String filename) {
		pb2verbnet = new HashMap<String, List<String>>();
		pbargs2verbnet = new HashMap<String, Map<String, List<String>>>();

		SAXParser parser;
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
			SemLinkHandler dh = new SemLinkHandler();
			parser.parse(new File(filename), dh);
			pb2verbnet = dh.getMapping();
			pbargs2verbnet = dh.getAMapping();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] getVerbNetClasses(String p) {
		if(!pb2verbnet.containsKey(p))
			return new String[0];
		List<String> classes = pb2verbnet.get(p);
		String[] retval = new String[classes.size()];
		retval = classes.toArray(retval);
		return retval;
	}

    public String[] getVerbNetArg(String p, String a) {
	if(!pbargs2verbnet.containsKey(p))
	    return new String[0];
	if(!pbargs2verbnet.get(p).containsKey(a))
	    return new String[0];
	List<String> args = pbargs2verbnet.get(p).get(a);
	String[] retval = new String[args.size()];
	retval = args.toArray(retval);
	return retval;
    }
	
	private class SemLinkHandler extends DefaultHandler {
	    Map<String, List<String>> mapping;
	    Map<String, Map<String, List<String>>> amapping;
	    String curr_p;
	    
	    public Map<String, List<String>> getMapping() {
		return mapping;
	    }

	    public Map<String, Map<String, List<String>>> getAMapping() {
		return amapping;
	    }
	    
	    public SemLinkHandler() {
		super();
		mapping = new HashMap<String, List<String>>();
		amapping = new HashMap<String, Map<String, List<String>>>();
		curr_p = "";
	    }
	    
	    @Override
	    public void startElement(String uri, String localName, String qName,
				     Attributes attributes) throws SAXException {
		
		if(qName.equals("argmap")) {
		    String pb = attributes.getValue("pb-roleset");
		    String vn = attributes.getValue("vn-class");
		    
		    if(!mapping.containsKey(pb))
			mapping.put(pb, new LinkedList<String>());
		    mapping.get(pb).add(vn);

		    curr_p = pb;
		}

		if(qName.equals("role")) {
		    String pb = attributes.getValue("pb-arg");
                    String vn = attributes.getValue("vn-theta");
		    
		    if(!amapping.containsKey(curr_p))
			amapping.put(curr_p, new HashMap<String, List<String>>());
		    if(!amapping.get(curr_p).containsKey(pb))
			amapping.get(curr_p).put(pb, new LinkedList<String>());
		    amapping.get(curr_p).get(pb).add(vn);
		}
	    }
	    
	}
    
}
