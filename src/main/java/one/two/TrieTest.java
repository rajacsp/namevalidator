package one.two;

import java.util.Collection;
import java.util.Iterator;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

public class TrieTest {
	
	private static final Trie trie = new Trie();
	
	{
		trie.addKeyword("asshole");
		trie.addKeyword("asswipe");
	}

	public static void main(String[] args){
		TrieTest t = new TrieTest();
		
		System.out.println(t.isNameAllowed("ass"));
	}
	
	public boolean isNameAllowed(String name) {
		System.out.println("{isNameAllowed} name : "+name);
		Collection<Emit> e = trie.parseText(name);
		Iterator<Emit> it = e.iterator();
		while(it.hasNext()) {
			Emit emit = it.next();
			System.out.println("prohibited words - " + emit.getKeyword());
		}
		return (e.size() > 0)?false:true;
	}
}
