package ui.mainui;

import java.awt.Color;
import java.awt.Toolkit;
import java.math.BigDecimal;

public class FrameSize {
	static int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int width=3*screenWidth/4;
	public static int height = 8*screenHeight/9;
	public static Color backColor = new Color(68,68,68,200);
	public static Color buttonbackColor=new Color(68,68,68);
	public static double roundForNumber(double v){
		BigDecimal bg = new BigDecimal(v);
        double f = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f;
    }
}
