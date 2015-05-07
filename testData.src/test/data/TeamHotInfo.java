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
/*    */ public class TeamHotInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String teamName;
/*    */   private String league;
/*    */   private String field;
/*    */   private double value;
/*    */   
/*    */   public String getTeamName()
/*    */   {
/* 34 */     return this.teamName;
/*    */   }
/*    */   
/* 37 */   public void setTeamName(String teamName) { this.teamName = teamName; }
/*    */   
/*    */   public String getLeague() {
/* 40 */     return this.league;
/*    */   }
/*    */   
/* 43 */   public void setLeague(String league) { this.league = league; }
/*    */   
/*    */   public String getField() {
/* 46 */     return this.field;
/*    */   }
/*    */   
/* 49 */   public void setField(String field) { this.field = field; }
/*    */   
/*    */   public double getValue() {
/* 52 */     return this.value;
/*    */   }
/*    */   
/* 55 */   public void setValue(double value) { this.value = value; }
/*    */   
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 61 */     StringBuilder stringBuilder = new StringBuilder();
/* 62 */     String ln = "\n";
/* 63 */     stringBuilder.append(getTeamName()).append(ln);
/* 64 */     stringBuilder.append(getLeague()).append(ln);
/* 65 */     stringBuilder.append(getField()).append(ln);
/* 66 */     stringBuilder.append(getValue()).append(ln);
/* 67 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              G:\学习课件\软件工程与计算3\测试相关文档\docs\功能测试\testData.jar!\test\data\TeamHotInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */