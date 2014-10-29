package uk.ac.ed.inf.srl.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uk.ac.ed.inf.srl.corpus.Predicate;
import uk.ac.ed.inf.srl.corpus.Sentence;
import uk.ac.ed.inf.srl.corpus.Word;

public class SentenceAnnotation {

	public List<String[]> conceptAnno;
	public List<String[]> relationAnno;
	int startIndex;
	int endIndex;
	
	Map<String,Word> concept2word;
	Map<String,String> concept2label;
	List<List<Integer>> wordids;
	private Map<String, Integer> id2conceptAnno;
	
	public SentenceAnnotation(int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		conceptAnno = new LinkedList<String[]>();
		relationAnno = new LinkedList<String[]>();
		id2conceptAnno = new HashMap<String, Integer>();
	}	

	public void addAnnotation(String[] num_anno_word) {
		if(num_anno_word.length==3) {
			if(id2conceptAnno.containsKey(num_anno_word[0]) && num_anno_word[0].startsWith("F")) {
			    int index = id2conceptAnno.get(num_anno_word[0]);
				String[] anno = conceptAnno.remove(index);
				String[] name_from_to1 = anno[1].split(" ");
				String[] name_from_to2 = num_anno_word[1].split(" ");
				
				int from1 = Integer.parseInt(name_from_to1[1]);
				int from2 = Integer.parseInt(name_from_to2[1]);
				int to1   = Integer.parseInt(name_from_to1[2]);
				int to2   = Integer.parseInt(name_from_to2[2]);
				anno[1] = name_from_to1[0] + " " + (from1<from2?from1:from2) + " " + (to1>to2?to2:to1);
				conceptAnno.add(index, anno);
			} else {
				id2conceptAnno.put(num_anno_word[0], conceptAnno.size());
				conceptAnno.add(num_anno_word);
			}
		} else {
			relationAnno.add(num_anno_word);
		}
	}

	private int merge(String[] num_anno_word, Integer integer) {
		return 0;
	}

	public void apply(Sentence sen, boolean framenet) {
		wordids = new LinkedList<List<Integer>>();
		
		int[] begins = new int[sen.size()-1];
		int[] ends   = new int[sen.size()-1];
		for(int i=0; i<begins.length;i++) {
			Word w = sen.get(i+1);
			begins[i] = w.getBegin();
			ends[i]   = w.getEnd() - (framenet?1:0); /** XXX: if FN, use w.getEnd()-1 instead? **/
		}
		
		concept2word = new HashMap<String,Word>();
		concept2label = new HashMap<String,String>();
		TreeMap<Integer, String> pred2sense = new TreeMap<Integer, String>();

		for(String[] anno : conceptAnno) {
			//System.out.println(Arrays.toString(anno));
			String label       = anno[1].split(" ")[0];
			int startCharacter = Integer.parseInt(anno[1].split(" ")[1]); 
			int endCharacter   = Integer.parseInt(anno[1].split(" ")[2]);
			
			List<Integer> currids = new LinkedList<Integer>();
			for(int i=0; i<begins.length;i++) {
				if(begins[i]>=startCharacter && ends[i]<=endCharacter)
					currids.add(i);
			}
			if(currids.size()==0) {
				System.err.println(sen.toString());
				System.err.println("Error: no matching token found for span from " + startCharacter + ":"+ endCharacter);
				System.err.println("Tokens are:");
				for(int i=0; i<begins.length;i++) {
					System.err.println("  " + begins[i] + ":" + ends[i] + "\t" + sen.get(i+1).getForm());
				}
				continue;
			}
			//System.out.println(anno[0]);

			concept2word.put(anno[0], sen.get(head(sen, currids)+1));
			concept2label.put(anno[0], label);
			if(framenet && anno[0].startsWith("F"))
				pred2sense.put(sen.get(head(sen, currids)+1).getIdx(), anno[1].split(" ")[0]);
		}
		
		// mark predicates and collect predicate-argument relationships
		List<Relation> relations = new LinkedList<Relation>();

		for(String[] anno : relationAnno) {
			String[] rel_arg1_arg2 = anno[1].split(" ");
			String rel  = rel_arg1_arg2[0];
			String arg1 = rel_arg1_arg2[1].split(":")[1];
			String arg2 = rel_arg1_arg2[2].split(":")[1];
			
			if(framenet) {
				if(concept2word.containsKey(arg1)) {
					pred2sense.put(concept2word.get(arg1).getIdx(), rel.split(":")[0]);
					relations.add(new Relation(concept2word.get(arg1).getIdx(), concept2word.get(arg2).getIdx(), rel.split(":")[1]));
				}
			} else {			
				int arg1index = concept2word.get(arg1).getIdx();
				int arg2index = concept2word.get(arg2).getIdx();
				
				String l1 = concept2label.get(arg1);
				String l2 = concept2label.get(arg2);
				
				// makePredicate needs to be executed in word order
				// -> collect all predicates and their arguments first
				/** for S-CASE **/
				
				if(l1.equals("Action") && l2.equals("Object")) {
					pred2sense.put(arg1index, "Action");
					relations.add(new Relation(arg1index, arg2index, "Theme"));
				} else if(l1.equals("Actor") && l2.equals("Action")) {
					pred2sense.put(arg2index, "Action");
					relations.add(new Relation(arg2index, arg1index, "Actor"));
				} else if(l1.equals("Action") && l2.equals("Property")) {
					pred2sense.put(arg1index, "Action");
					relations.add(new Relation(arg1index, arg2index, "Property"));
				} else if(l2.equals("Property")) {
					pred2sense.put(arg1index, l1);
					relations.add(new Relation(arg1index, arg2index, "Property"));
				}	/**/
			}
		}
		
		// add predicates/senses (TreeMap.keySet() is in ascending ordered!) 
		for(Integer i : pred2sense.keySet()) {
			sen.makePredicate(i);
			((Predicate)sen.get(i)).setSense(pred2sense.get(i));
		}
		
		// add predicate-argument relationships
		for(Relation r : relations) {
			((Predicate)sen.get(r.head)).addArgMap(sen.get(r.dependent), r.label);
			//System.out.println(sen.get(r.head).getForm() + "["+r.label+"]: " + sen.get(r.dependent).getForm());
		}
	}

	public int head(Sentence sen, List<Integer> currids) {
		int head = currids.get(0);
	
		for(int i=1; i<currids.size(); i++) {
			if(currids.get(i)>head)
				head=currids.get(i);
		}
		
		boolean containshead = true;
		while(containshead) {
			int newhead = sen.get(head+1).getHeadId()-1;
			containshead = false;
			for(int i=0; i<currids.size(); i++)
				if(currids.get(i)==newhead)
					containshead = true;
			if(containshead)
				head = newhead;
		}
		
		return head;
	}

}
