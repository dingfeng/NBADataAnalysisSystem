package data.teamdata;

public class main 
{
	
	public static void main(String[] args)
	{
		System.out.println("lib : "+lib_parseInt());
//		System.out.println("my : "+my_parseInt());
	}
 public static String num = "1990";
 public static long  lib_parseInt()
 {
	 long start_time = System.currentTimeMillis();
	 Integer.parseInt(num);
	 long end_time = System.currentTimeMillis();
	 long margin_time = end_time - start_time;
	 return margin_time;
 }
 
 public static long my_parseInt()
 {
	 long start_time = System.currentTimeMillis();
	 char [] nums = num.toCharArray();
	 int numi = nums[0] + (0 - '0');
	 long end_time = System.currentTimeMillis();
	 long margin_time = end_time - start_time;
	 return margin_time;
 }
}
