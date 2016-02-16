package one.two;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;

public class Name {
	
	@Getter @Setter
	private static Name instance;
	
	private static final Trie trie = new Trie();
	
	public static Name createInstance(final String wordsFile) {
		if(instance == null){
			instance = new Name(wordsFile);
		}
		return instance;
	}
	
	private Name(final String wordsFile) {
		System.out.println("==================== init ForumNick with WordsFile ====================");
		loadProhibitedWords(wordsFile);
	}
	
	public static Name getInstance(){
		if(instance == null){
			instance = createInstance("name.txt");
			return instance;
		}		
		
		return instance;
	}
	
	private void loadProhibitedWords(final String wordsFile) {
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "name.xml" });

		NameService cust = (NameService) appContext.getBean("nameService");

		// Resource resource = cust.getResource("file:c:\\testing.txt");
		// Resource resource = cust.getResource("url:http://www.google.com");
		Resource resource = cust.getResource("classpath:one/"+wordsFile);
		
		try {
			String words = IOUtils.toString(resource.getInputStream());
			String[] pwords = words.split(",");
			System.out.println("[ForumNick][loadProhibitedWords] length - " + pwords.length);
			for(String word:pwords) {
				trie.addKeyword(word.trim());
			}
			System.out.println(words);
		} catch (IOException e) {
			System.out.println("[ForumNick][loadProhibitedWords][error] cannot load Prohibited Words for Forum");
			e.printStackTrace();
		}
		
		Collection<Emit> e = trie.parseText("forum");
		Iterator<Emit> it = e.iterator();
		while(it.hasNext()) {
			Emit emit = it.next();
			System.out.println(emit.getKeyword());
		}
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
