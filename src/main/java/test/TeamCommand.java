package test;

public class TeamCommand 
{
  private int ave_total = 0;   //默认为0为ave,1代表total
  private int all_hot = -1;   //默认小于零代表所有球队的信息，当大于零时代表获得热门球队，数值为命令数组的下标
  private int num = -1;      //默认值为-1，代表无此命令，值为30.当num为正数时，代表命令数组的下标
  private int base_high = 0; //默认值为0代表基本数据类型，1代表高阶数据
  private int sort = -1;     //-1代表没有，当它大于1时代表命令数组的下标
  
  public void readCommand(String[] command)
  {
	  String str = null;
	  for (int i =1; i < command.length; i++)
	  {
		  switch (command[i])
		  {
		  case "-avg":
			  ave_total = 0;
			  break;
		  case "-total":
			  ave_total = 1;
			  break;
		  case "-all":
			  all_hot = -1;
			  break;
		  case "-hot":
			  all_hot = i +1;
			  break;
		  case "-n":
			  num = i +1;
			  break;
		  case  "-high":
			  base_high = 1;
			  break;
		  case "-sort":
			  sort  = i +1;
			  break;
		  }
	  }
  }
  
public int getAve_total() {
	return ave_total;
}
public int getAll_hot() {
	return all_hot;
}
public int getNum() {
	return num;
}
public int getBase_high() {
	return base_high;
}
public int getSort() {
	return sort;
}
  
}
