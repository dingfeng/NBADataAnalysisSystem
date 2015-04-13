package po;

import java.awt.Image;


public class PlayerPO implements Comparable<PlayerPO>{

	private Image action;// 大头图片
	private Image portrait;// 全身图片
	private String name;// 姓名
	private int number;// 球衣号码
	private String position;// 位置
	private int heightfeet;// 身高的英尺
	private int heightinch;// 身高的英寸
	private int weight;// 体重（磅）
	private String birth;// 生日
	private int age;// 年龄
	private int exp;// 球龄
	private String school;// 毕业学校
    //private String teamnameAbridge; //球队
	
	public PlayerPO(Image action, Image portrait, String name, int number,
			String position, int heightfeet, int heightinch, int weight,
			String birth, int age, int exp, String school) {
		super();
		this.action = action;
		this.portrait = portrait;
		this.name = name;
		this.number = number;
		this.position = position;
		this.heightfeet = heightfeet;
		this.heightinch = heightinch;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
	}

//	public String getTeamnameAbridge()
//	{
//		return teamnameAbridge;
//	}
	
	public Image getAction() {
		return action;
	}

	public Image getPortrait() {
		return portrait;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public String getPosition() {
		return position;
	}

	public int getHeightfeet() {
		return heightfeet;
	}

	public int getHeightinch() {
		return heightinch;
	}

	public int getWeight() {
		return weight;
	}

	public String getBirth() {
		return birth;
	}

	public int getAge() {
		return age;
	}

	public int getExp() {
		return exp;
	}

	public String getSchool() {
		return school;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name+" ");
		sb.append(position+" ");
		sb.append(heightfeet+" ");
		sb.append(heightinch+" ");
		sb.append(weight+" ");
		sb.append(birth+" ");
		sb.append(age+" ");
		sb.append(school+" ");
		return sb.toString();
	}

	@Override
	public int compareTo(PlayerPO e) {
		return name.compareTo(e.getName());
	}
}
