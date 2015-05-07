/*     */ package test.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TeamHighInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String teamName;
/*     */   private double winRate;
/*     */   private double offendRound;
/*     */   private double offendEfficient;
/*     */   private double defendEfficient;
/*     */   private double offendReboundEfficient;
/*     */   private double defendReboundEfficient;
/*     */   private double stealEfficient;
/*     */   private double assistEfficient;
/*     */   
/*     */   public String getTeamName()
/*     */   {
/*  55 */     return this.teamName;
/*     */   }
/*     */   
/*     */   public void setTeamName(String teamName) {
/*  59 */     this.teamName = teamName;
/*     */   }
/*     */   
/*     */   public double getWinRate() {
/*  63 */     return this.winRate;
/*     */   }
/*     */   
/*     */   public void setWinRate(double winRate) {
/*  67 */     this.winRate = winRate;
/*     */   }
/*     */   
/*     */   public double getOffendRound() {
/*  71 */     return this.offendRound;
/*     */   }
/*     */   
/*     */   public void setOffendRound(double offendRound) {
/*  75 */     this.offendRound = offendRound;
/*     */   }
/*     */   
/*     */   public double getOffendEfficient() {
/*  79 */     return this.offendEfficient;
/*     */   }
/*     */   
/*     */   public void setOffendEfficient(double offendEfficient) {
/*  83 */     this.offendEfficient = offendEfficient;
/*     */   }
/*     */   
/*     */   public double getDefendEfficient() {
/*  87 */     return this.defendEfficient;
/*     */   }
/*     */   
/*     */   public void setDefendEfficient(double defendEfficient) {
/*  91 */     this.defendEfficient = defendEfficient;
/*     */   }
/*     */   
/*     */   public double getOffendReboundEfficient() {
/*  95 */     return this.offendReboundEfficient;
/*     */   }
/*     */   
/*     */   public void setOffendReboundEfficient(double offendReboundEfficient) {
/*  99 */     this.offendReboundEfficient = offendReboundEfficient;
/*     */   }
/*     */   
/*     */   public double getDefendReboundEfficient() {
/* 103 */     return this.defendReboundEfficient;
/*     */   }
/*     */   
/*     */   public void setDefendReboundEfficient(double defendReboundEfficient) {
/* 107 */     this.defendReboundEfficient = defendReboundEfficient;
/*     */   }
/*     */   
/*     */   public double getStealEfficient() {
/* 111 */     return this.stealEfficient;
/*     */   }
/*     */   
/*     */   public void setStealEfficient(double stealEfficient) {
/* 115 */     this.stealEfficient = stealEfficient;
/*     */   }
/*     */   
/*     */   public double getAssistEfficient() {
/* 119 */     return this.assistEfficient;
/*     */   }
/*     */   
/*     */   public void setAssistEfficient(double assistEfficient) {
/* 123 */     this.assistEfficient = assistEfficient;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 129 */     StringBuilder stringBuilder = new StringBuilder();
/* 130 */     String ln = "\n";
/* 131 */     stringBuilder.append(getTeamName()).append(ln);
/* 132 */     stringBuilder.append(getWinRate()).append(ln);
/* 133 */     stringBuilder.append(getOffendRound()).append(ln);
/* 134 */     stringBuilder.append(getOffendEfficient()).append(ln);
/* 135 */     stringBuilder.append(getDefendEfficient()).append(ln);
/* 136 */     stringBuilder.append(getOffendReboundEfficient()).append(ln);
/* 137 */     stringBuilder.append(getDefendReboundEfficient()).append(ln);
/* 138 */     stringBuilder.append(getStealEfficient()).append(ln);
/* 139 */     stringBuilder.append(getAssistEfficient()).append(ln);
/* 140 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              G:\学习课件\软件工程与计算3\测试相关文档\docs\功能测试\testData.jar!\test\data\TeamHighInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */