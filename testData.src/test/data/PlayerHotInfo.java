/*    */ package test.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerHotInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String teamName;
/*    */   private String position;
/*    */   private String field;
/*    */   private double value;
/*    */   private double upgradeRate;
/*    */   
/*    */   public String getName()
/*    */   {
/* 42 */     return this.name;
/*    */   }
/*    */   
/* 45 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public String getTeamName() {
/* 48 */     return this.teamName;
/*    */   }
/*    */   
/* 51 */   public void setTeamName(String teamName) { this.teamName = teamName; }
/*    */   
/*    */   public String getPosition() {
/* 54 */     return this.position;
/*    */   }
/*    */   
/* 57 */   public void setPosition(String position) { this.position = position; }
/*    */   
/*    */   public String getField() {
/* 60 */     return this.field;
/*    */   }
/*    */   
/* 63 */   public void setField(String field) { this.field = field; }
/*    */   
/*    */   public double getValue() {
/* 66 */     return this.value;
/*    */   }
/*    */   
/* 69 */   public void setValue(double value) { this.value = value; }
/*    */   
/*    */   public double getUpgradeRate() {
/* 72 */     return this.upgradeRate;
/*    */   }
/*    */   
/* 75 */   public void setUpgradeRate(double upgradeRate) { this.upgradeRate = upgradeRate; }
/*    */   
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 81 */     StringBuilder stringBuilder = new StringBuilder();
/* 82 */     String ln = "\n";
/* 83 */     stringBuilder.append(getName()).append(ln);
/* 84 */     stringBuilder.append(getTeamName()).append(ln);
/* 85 */     stringBuilder.append(getPosition()).append(ln);
/* 86 */     stringBuilder.append(getField()).append(ln);
/* 87 */     stringBuilder.append(getValue()).append(ln);
/* 88 */     stringBuilder.append(getUpgradeRate()).append(ln);
/* 89 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              G:\学习课件\软件工程与计算3\测试相关文档\docs\功能测试\testData.jar!\test\data\PlayerHotInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */