package po;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.w3c.dom.Document;

import vo.Area;

public class TeamPO implements Comparable<TeamPO>{
	private Image image; // 队伍图标
	private String name; // 队伍名称
	private String nameAbridge; // 名称缩写
	private String address; // 所在地
	private String matchArea; // 赛区
	private Area playerArea;// 分区
	private String manage; // 主场
	private int foundYear; // 建立时间

	public String toString()
	{
	   return name+" "+nameAbridge+" "+address+" "+matchArea+" "+manage+" "+foundYear;
	}
	public TeamPO(Image image, String name, String nameAbridge, String address,
			String matchArea, Area playerArea, String manage, int foundYear) {
		super();
		this.image = image;
		this.name = name;
		this.nameAbridge = nameAbridge;
		this.address = address;
		this.matchArea = matchArea;
		this.playerArea = playerArea;
		this.manage = manage;
		this.foundYear = foundYear;
	}

	public Image getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public String getNameAbridge() {
		return nameAbridge;
	}

	public String getAddress() {
		return address;
	}

	public String getMatchArea() {
		return matchArea;
	}

	public Area getPlayerArea() {
		return playerArea;
	}

	public String getManage() {
		return manage;
	}

	public int getFoundYear() {
		return foundYear;
	}
	@Override
	public int compareTo(TeamPO o) {
		return name.compareTo(o.getName());
	}
}
