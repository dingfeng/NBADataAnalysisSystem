package data.matchdata;

import java.io.File;

public class main {
public static void main(String[] args)
{
	String s = "ssssdddd";
	System.out.println("index s : "+s.indexOf('k'));
	System.out.println("index d : "+s.indexOf('d'));
	File file = new File("C:/Users/FD/Desktop/新建文件夹 (2)");
	File[] file_list = file.listFiles();
    print(file_list);
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    file_list = file.listFiles();
    print(file_list);
}

public static void print(File[] files)
{
	for (File f : files)
	{
		System.out.print(f.getName());
		System.out.print(";");
	}
}

}
