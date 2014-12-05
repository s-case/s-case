package uk.ac.ed.inf.srl.corpus;

import java.util.LinkedList;
import java.util.List;

public class CorpusSentence extends Sentence {
	
	Corpus c;
	
	public Corpus getMyCorpus() {
		return c;
	}
	
	public CorpusSentence(Sentence s, Corpus c) {
		super(s);
		c.add(this);
		this.c = c;
		
		for(int i=1; i<this.size(); i++) {
			if(this.get(i).getCorefId()>-1)
				c.addMention(this,i, this.get(i).getCorefId());
		}
		
		for(Predicate p : s.predicates) {
			this.makePredicate(p.idx);
			Predicate new_p = (Predicate)super.get(p.idx);
			new_p.setSense(p.getSense());
		}
		for(Predicate p : s.predicates) {
			for(Word w : p.getArgMap().keySet()) {
				((Predicate)super.get(p.idx)).addArgMap(super.get(w.idx), p.getArgMap().get(w));
				//System.err.println(p.Form + " -" + p.getArgMap().get(w)+"> " + super.get(w.idx).Form);
			}
		}
		

	}
	
	public String toSpecialString() {
		String tag;
		StringBuilder ret=new StringBuilder();
		for(int i=1;i<super.size();++i){
			Word w=super.get(i);
			if(i==1 && c.get(0)==this)
				ret.append(c.getName());
			else
				ret.append("_");
			ret.append("\t").append(i).append("\t").append(w.toString());
					
			if(!(w instanceof Predicate)) //If its not a predicate add the FILLPRED and PRED cols
				ret.append("\t_\t_");
			
			// coref ids: a bit of a hack now--they should be stored for each word instead
			ret.append("\t").append(c.corefId(w));
			
			for(int j=0;j<predicates.size();++j){
				ret.append("\t");
				Predicate pred=predicates.get(j);
				ret.append((tag=pred.getArgumentTag(w))!=null?tag:"_");
			}
			ret.append("%n");
		}
		return ret.toString().trim();	
	}

	public int findhead(int begin, int end) {
		List<Integer> currids =  new LinkedList<Integer>();
		//System.err.println(begin + "--" + end);
		for(int i=1; i<this.size(); i++) {
			//System.err.println(this.get(i).begin + ":" + this.get(i).end + "\t" + this.get(i).Form);
			if(this.get(i).begin>=begin && this.get(i).end<=end)
				currids.add(i);
		}
		
		if(currids.size()==0)
			return -1;
		
		int head = currids.get(0);
		
		for(int i=1; i<currids.size(); i++) {
			if(currids.get(i)>head)
				head=currids.get(i);
		}
		
		boolean containshead = true;
		while(containshead) {
			int newhead = this.get(head).getHeadId();
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
