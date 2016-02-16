package one.two;

import java.util.Collection;
import java.util.Iterator;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

public class AhorTest{
	
	public static void main(String[] args){
		String name = "puta123";
		System.out.println(isNameAllowed(name));		
	}
	
	public static boolean isNameAllowed(String name) {
		
		Trie trie = new Trie();		
		
		trie.addKeyword("puta");
		trie.addKeyword("his");
		trie.addKeyword("she");
		trie.addKeyword("he");
		
		Collection<Emit> e = trie.parseText(name);
		System.out.println(e);
		
		Iterator<Emit> it = e.iterator();
		while(it.hasNext()) {
			Emit emit = it.next();
			System.out.println("prohibited words - " + emit.getKeyword());
		}
		boolean flag = (e.size() > 0)?false:true;
		
		
		return flag;
	}
}