package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class file {
 public static void main(String[] args) throws IOException
 {
	 String path = "K:/tests/TestCase.auto.xml";
	 BufferedReader br = new BufferedReader(new FileReader(path));
	 ArrayList<String> content = new ArrayList<String>();
	 String s = null;
	 int i = 0;
	 while( (s=br.readLine()) != null)
	 {
		 if (s.contains("name="))
		 {
			 int index = s.indexOf("name=\"");
			 s = s.substring(0, index+6);
			 s = s + (++i)+"\""+">";
		 }
		 content.add(s);
	 }
	 br.close();
	 BufferedWriter bw = new BufferedWriter(new FileWriter("K:/1.xml"));
	 for (int j = 0;j < content.size(); j++)
	 {
		 bw.write(content.get(j));
		 bw.newLine();
	 }
	 bw.close();
 }
}
