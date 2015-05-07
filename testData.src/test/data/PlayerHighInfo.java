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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerHighInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String name;
/*     */   private String position;
/*     */   private String teamName;
/*     */   private String league;
/*     */   private double gmSc;
/*     */   private double realShot;
/*     */   private double shotEfficient;
/*     */   private double reboundEfficient;
/*     */   private double offendReboundEfficient;
/*     */   private double defendReboundEfficient;
/*     */   private double assistEfficient;
/*     */   private double stealEfficient;
/*     */   private double blockShotEfficient;
/*     */   private double faultEfficient;
/*     */   private double frequency;
/*     */   
/*     */   public String getName()
/*     */   {
/*  82 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  86 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getPosition() {
/*  90 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(String position) {
/*  94 */     this.position = position;
/*     */   }
/*     */   
/*     */   public String getTeamName() {
/*  98 */     return this.teamName;
/*     */   }
/*     */   
/*     */   public void setTeamName(String teamName) {
/* 102 */     this.teamName = teamName;
/*     */   }
/*     */   
/*     */   public String getLeague() {
/* 106 */     return this.league;
/*     */   }
/*     */   
/*     */   public void setLeague(String league) {
/* 110 */     this.league = league;
/*     */   }
/*     */   
/*     */   public double getGmSc() {
/* 114 */     return this.gmSc;
/*     */   }
/*     */   
/*     */   public void setGmSc(double gmSc) {
/* 118 */     this.gmSc = gmSc;
/*     */   }
/*     */   
/*     */   public double getRealShot() {
/* 122 */     return this.realShot;
/*     */   }
/*     */   
/*     */   public void setRealShot(double realShot) {
/* 126 */     this.realShot = realShot;
/*     */   }
/*     */   
/*     */   public double getShotEfficient() {
/* 130 */     return this.shotEfficient;
/*     */   }
/*     */   
/*     */   public void setShotEfficient(double shotEfficient) {
/* 134 */     this.shotEfficient = shotEfficient;
/*     */   }
/*     */   
/*     */   public double getReboundEfficient() {
/* 138 */     return this.reboundEfficient;
/*     */   }
/*     */   
/*     */   public void setReboundEfficient(double reboundEfficient) {
/* 142 */     this.reboundEfficient = reboundEfficient;
/*     */   }
/*     */   
/*     */   public double getOffendReboundEfficient() {
/* 146 */     return this.offendReboundEfficient;
/*     */   }
/*     */   
/*     */   public void setOffendReboundEfficient(double offendReboundEfficient) {
/* 150 */     this.offendReboundEfficient = offendReboundEfficient;
/*     */   }
/*     */   
/*     */   public double getDefendReboundEfficient() {
/* 154 */     return this.defendReboundEfficient;
/*     */   }
/*     */   
/*     */   public void setDefendReboundEfficient(double defendReboundEfficient) {
/* 158 */     this.defendReboundEfficient = defendReboundEfficient;
/*     */   }
/*     */   
/*     */   public double getAssistEfficient() {
/* 162 */     return this.assistEfficient;
/*     */   }
/*     */   
/*     */   public void setAssistEfficient(double assistEfficient) {
/* 166 */     this.assistEfficient = assistEfficient;
/*     */   }
/*     */   
/*     */   public double getStealEfficient() {
/* 170 */     return this.stealEfficient;
/*     */   }
/*     */   
/*     */   public void setStealEfficient(double stealEfficient) {
/* 174 */     this.stealEfficient = stealEfficient;
/*     */   }
/*     */   
/*     */   public double getBlockShotEfficient() {
/* 178 */     return this.blockShotEfficient;
/*     */   }
/*     */   
/*     */   public void setBlockShotEfficient(double blockShotEfficient) {
/* 182 */     this.blockShotEfficient = blockShotEfficient;
/*     */   }
/*     */   
/*     */   public double getFaultEfficient() {
/* 186 */     return this.faultEfficient;
/*     */   }
/*     */   
/*     */   public void setFaultEfficient(double faultEfficient) {
/* 190 */     this.faultEfficient = faultEfficient;
/*     */   }
/*     */   
/*     */   public double getFrequency() {
/* 194 */     return this.frequency;
/*     */   }
/*     */   
/*     */   public void setFrequency(double frequency) {
/* 198 */     this.frequency = frequency;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 204 */     StringBuilder stringBuilder = new StringBuilder();
/* 205 */     String ln = "\n";
/* 206 */     stringBuilder.append(getName()).append(ln);
/* 207 */     stringBuilder.append(getPosition()).append(ln);
/* 208 */     stringBuilder.append(getTeamName()).append(ln);
/* 209 */     stringBuilder.append(getLeague()).append(ln);
/* 210 */     stringBuilder.append(getGmSc()).append(ln);
/* 211 */     stringBuilder.append(getRealShot()).append(ln);
/* 212 */     stringBuilder.append(getShotEfficient()).append(ln);
/* 213 */     stringBuilder.append(getReboundEfficient()).append(ln);
/* 214 */     stringBuilder.append(getOffendReboundEfficient()).append(ln);
/* 215 */     stringBuilder.append(getDefendReboundEfficient()).append(ln);
/* 216 */     stringBuilder.append(getAssistEfficient()).append(ln);
/* 217 */     stringBuilder.append(getStealEfficient()).append(ln);
/* 218 */     stringBuilder.append(getBlockShotEfficient()).append(ln);
/* 219 */     stringBuilder.append(getFaultEfficient()).append(ln);
/* 220 */     stringBuilder.append(getFrequency()).append(ln);
/* 221 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              G:\学习课件\软件工程与计算3\测试相关文档\docs\功能测试\testData.jar!\test\data\PlayerHighInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */