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
/*    */ public class PlayerKingInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String teamName;
/*    */   private String position;
/*    */   private String field;
/*    */   private double value;
/*    */   
/*    */   public String getName()
/*    */   {
/* 38 */     return this.name;
/*    */   }
/*    */   
/* 41 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public String getTeamName() {
/* 44 */     return this.teamName;
/*    */   }
/*    */   
/* 47 */   public void setTeamName(String teamName) { this.teamName = teamName; }
/*    */   
/*    */   public String getPosition() {
/* 50 */     return this.position;
/*    */   }
/*    */   
/* 53 */   public void setPosition(String position) { this.position = position; }
/*    */   
/*    */   public String getField() {
/* 56 */     return this.field;
/*    */   }
/*    */   
/* 59 */   public void setField(String field) { this.field = field; }
/*    */   
/*    */   public double getValue() {
/* 62 */     return this.value;
/*    */   }
/*    */   
/* 65 */   public void setValue(double value) { this.value = value; }
/*    */   
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 71 */     StringBuilder stringBuilder = new StringBuilder();
/* 72 */     String ln = "\n";
/* 73 */     stringBuilder.append(getName()).append(ln);
/* 74 */     stringBuilder.append(getTeamName()).append(ln);
/* 75 */     stringBuilder.append(getPosition()).append(ln);
/* 76 */     stringBuilder.append(getField()).append(ln);
/* 77 */     stringBuilder.append(getValue()).append(ln);
/* 78 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              G:\学习课件\软件工程与计算3\测试相关文档\docs\功能测试\testData.jar!\test\data\PlayerKingInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */