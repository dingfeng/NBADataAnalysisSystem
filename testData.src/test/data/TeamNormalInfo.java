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
/*     */ public class TeamNormalInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String teamName;
/*     */   private double three;
/*     */   private int numOfGame;
/*     */   private double shot;
/*     */   private double penalty;
/*     */   private double offendRebound;
/*     */   private double defendRebound;
/*     */   private double rebound;
/*     */   private double assist;
/*     */   private double fault;
/*     */   private double steal;
/*     */   private double blockShot;
/*     */   private double foul;
/*     */   private double point;
/*     */   
/*     */   public String getTeamName()
/*     */   {
/*  75 */     return this.teamName;
/*     */   }
/*     */   
/*     */   public void setTeamName(String teamName) {
/*  79 */     this.teamName = teamName;
/*     */   }
/*     */   
/*     */   public double getThree() {
/*  83 */     return this.three;
/*     */   }
/*     */   
/*     */   public void setThree(double three) {
/*  87 */     this.three = three;
/*     */   }
/*     */   
/*     */   public int getNumOfGame() {
/*  91 */     return this.numOfGame;
/*     */   }
/*     */   
/*     */   public void setNumOfGame(int numOfGame) {
/*  95 */     this.numOfGame = numOfGame;
/*     */   }
/*     */   
/*     */   public double getShot() {
/*  99 */     return this.shot;
/*     */   }
/*     */   
/*     */   public void setShot(double shot) {
/* 103 */     this.shot = shot;
/*     */   }
/*     */   
/*     */   public double getPenalty() {
/* 107 */     return this.penalty;
/*     */   }
/*     */   
/*     */   public void setPenalty(double penalty) {
/* 111 */     this.penalty = penalty;
/*     */   }
/*     */   
/*     */   public double getOffendRebound() {
/* 115 */     return this.offendRebound;
/*     */   }
/*     */   
/*     */   public void setOffendRebound(double offendRebound) {
/* 119 */     this.offendRebound = offendRebound;
/*     */   }
/*     */   
/*     */   public double getDefendRebound() {
/* 123 */     return this.defendRebound;
/*     */   }
/*     */   
/*     */   public void setDefendRebound(double defendRebound) {
/* 127 */     this.defendRebound = defendRebound;
/*     */   }
/*     */   
/*     */   public double getRebound() {
/* 131 */     return this.rebound;
/*     */   }
/*     */   
/*     */   public void setRebound(double rebound) {
/* 135 */     this.rebound = rebound;
/*     */   }
/*     */   
/*     */   public double getAssist() {
/* 139 */     return this.assist;
/*     */   }
/*     */   
/*     */   public void setAssist(double assist) {
/* 143 */     this.assist = assist;
/*     */   }
/*     */   
/*     */   public double getFault() {
/* 147 */     return this.fault;
/*     */   }
/*     */   
/*     */   public void setFault(double fault) {
/* 151 */     this.fault = fault;
/*     */   }
/*     */   
/*     */   public double getSteal() {
/* 155 */     return this.steal;
/*     */   }
/*     */   
/*     */   public void setSteal(double steal) {
/* 159 */     this.steal = steal;
/*     */   }
/*     */   
/*     */   public double getBlockShot() {
/* 163 */     return this.blockShot;
/*     */   }
/*     */   
/*     */   public void setBlockShot(double blockShot) {
/* 167 */     this.blockShot = blockShot;
/*     */   }
/*     */   
/*     */   public double getFoul() {
/* 171 */     return this.foul;
/*     */   }
/*     */   
/*     */   public void setFoul(double foul) {
/* 175 */     this.foul = foul;
/*     */   }
/*     */   
/*     */   public double getPoint() {
/* 179 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setPoint(double point) {
/* 183 */     this.point = point;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 189 */     StringBuilder stringBuilder = new StringBuilder();
/* 190 */     String ln = "\n";
/* 191 */     stringBuilder.append(getTeamName()).append(ln);
/* 192 */     stringBuilder.append(getThree()).append(ln);
/* 193 */     stringBuilder.append(getNumOfGame()).append(ln);
/* 194 */     stringBuilder.append(getShot()).append(ln);
/* 195 */     stringBuilder.append(getPenalty()).append(ln);
/* 196 */     stringBuilder.append(getOffendRebound()).append(ln);
/* 197 */     stringBuilder.append(getDefendRebound()).append(ln);
/* 198 */     stringBuilder.append(getRebound()).append(ln);
/* 199 */     stringBuilder.append(getAssist()).append(ln);
/* 200 */     stringBuilder.append(getFault()).append(ln);
/* 201 */     stringBuilder.append(getSteal()).append(ln);
/* 202 */     stringBuilder.append(getBlockShot()).append(ln);
/* 203 */     stringBuilder.append(getFoul()).append(ln);
/* 204 */     stringBuilder.append(getPoint()).append(ln);
/* 205 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              G:\学习课件\软件工程与计算3\测试相关文档\docs\功能测试\testData.jar!\test\data\TeamNormalInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */