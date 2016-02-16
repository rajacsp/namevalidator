package one.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * Name Validator Test File
 *
 */
public class App {
	
	
	public static void main(String[] args) {
		boolean flag = Name.getInstance().isNameAllowed("ass");
		System.out.println(flag);
	}
	
	public void loadTest(){
		ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "Name.xml" });

		NameService cust = (NameService) appContext.getBean("nameService");

		// Resource resource = cust.getResource("file:c:\\testing.txt");
		// Resource resource = cust.getResource("url:http://www.google.com");
		Resource resource = cust.getResource("classpath:one/name.txt");

		try {
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
