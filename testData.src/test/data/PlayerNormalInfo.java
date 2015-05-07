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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerNormalInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String name;
/*     */   private int age;
/*     */   private String teamName;
/*     */   private int numOfGame;
/*     */   private int start;
/*     */   private double rebound;
/*     */   private double assist;
/*     */   private double minute;
/*     */   private double efficiency;
/*     */   private double shot;
/*     */   private double three;
/*     */   private double penalty;
/*     */   private double offend;
/*     */   private double defend;
/*     */   private double steal;
/*     */   private double blockShot;
/*     */   private double fault;
/*     */   private double foul;
/*     */   private double point;
/*     */   
/*     */   public String getName()
/*     */   {
/*  95 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  99 */     this.name = name;
/*     */   }
/*     */   
/*     */   public int getAge() {
/* 103 */     return this.age;
/*     */   }
/*     */   
/*     */   public void setAge(int age) {
/* 107 */     this.age = age;
/*     */   }
/*     */   
/*     */   public String getTeamName() {
/* 111 */     return this.teamName;
/*     */   }
/*     */   
/*     */   public void setTeamName(String teamName) {
/* 115 */     this.teamName = teamName;
/*     */   }
/*     */   
/*     */   public int getNumOfGame() {
/* 119 */     return this.numOfGame;
/*     */   }
/*     */   
/*     */   public void setNumOfGame(int numOfGame) {
/* 123 */     this.numOfGame = numOfGame;
/*     */   }
/*     */   
/*     */   public int getStart() {
/* 127 */     return this.start;
/*     */   }
/*     */   
/*     */   public void setStart(int start) {
/* 131 */     this.start = start;
/*     */   }
/*     */   
/*     */   public double getRebound() {
/* 135 */     return this.rebound;
/*     */   }
/*     */   
/*     */   public void setRebound(double rebound) {
/* 139 */     this.rebound = rebound;
/*     */   }
/*     */   
/*     */   public double getAssist() {
/* 143 */     return this.assist;
/*     */   }
/*     */   
/*     */   public void setAssist(double assist) {
/* 147 */     this.assist = assist;
/*     */   }
/*     */   
/*     */   public double getMinute() {
/* 151 */     return this.minute;
/*     */   }
/*     */   
/*     */   public void setMinute(double minute) {
/* 155 */     this.minute = minute;
/*     */   }
/*     */   
/*     */   public double getEfficiency() {
/* 159 */     return this.efficiency;
/*     */   }
/*     */   
/*     */   public void setEfficiency(double efficiency) {
/* 163 */     this.efficiency = efficiency;
/*     */   }
/*     */   
/*     */   public double getShot() {
/* 167 */     return this.shot;
/*     */   }
/*     */   
/*     */   public void setShot(double shot) {
/* 171 */     this.shot = shot;
/*     */   }
/*     */   
/*     */   public double getThree() {
/* 175 */     return this.three;
/*     */   }
/*     */   
/*     */   public void setThree(double three) {
/* 179 */     this.three = three;
/*     */   }
/*     */   
/*     */   public double getPenalty() {
/* 183 */     return this.penalty;
/*     */   }
/*     */   
/*     */   public void setPenalty(double penalty) {
/* 187 */     this.penalty = penalty;
/*     */   }
/*     */   
/*     */   public double getOffend() {
/* 191 */     return this.offend;
/*     */   }
/*     */   
/*     */   public void setOffend(double offend) {
/* 195 */     this.offend = offend;
/*     */   }
/*     */   
/*     */   public double getDefend() {
/* 199 */     return this.defend;
/*     */   }
/*     */   
/*     */   public void setDefend(double defend) {
/* 203 */     this.defend = defend;
/*     */   }
/*     */   
/*     */   public double getSteal() {
/* 207 */     return this.steal;
/*     */   }
/*     */   
/*     */   public void setSteal(double steal) {
/* 211 */     this.steal = steal;
/*     */   }
/*     */   
/*     */   public double getBlockShot() {
/* 215 */     return this.blockShot;
/*     */   }
/*     */   
/*     */   public void setBlockShot(double blockShot) {
/* 219 */     this.blockShot = blockShot;
/*     */   }
/*     */   
/*     */   public double getFault() {
/* 223 */     return this.fault;
/*     */   }
/*     */   
/*     */   public void setFault(double fault) {
/* 227 */     this.fault = fault;
/*     */   }
/*     */   
/*     */   public double getFoul() {
/* 231 */     return this.foul;
/*     */   }
/*     */   
/*     */   public void setFoul(double foul) {
/* 235 */     this.foul = foul;
/*     */   }
/*     */   
/*     */   public double getPoint() {
/* 239 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setPoint(double point) {
/* 243 */     this.point = point;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 249 */     StringBuilder stringBuilder = new StringBuilder();
/* 250 */     String ln = "\n";
/* 251 */     stringBuilder.append(getName()).append(ln);
/* 252 */     stringBuilder.append(getTeamName()).append(ln);
/* 253 */     stringBuilder.append(getNumOfGame()).append(ln);
/* 254 */     stringBuilder.append(getStart()).append(ln);
/* 255 */     stringBuilder.append(getRebound()).append(ln);
/* 256 */     stringBuilder.append(getAssist()).append(ln);
/* 257 */     stringBuilder.append(getMinute()).append(ln);
/* 258 */     stringBuilder.append(getEfficiency()).append(ln);
/* 259 */     stringBuilder.append(getShot()).append(ln);
/* 260 */     stringBuilder.append(getThree()).append(ln);
/* 261 */     stringBuilder.append(getPenalty()).append(ln);
/* 262 */     stringBuilder.append(getOffend()).append(ln);
/* 263 */     stringBuilder.append(getDefend()).append(ln);
/* 264 */     stringBuilder.append(getSteal()).append(ln);
/* 265 */     stringBuilder.append(getBlockShot()).append(ln);
/* 266 */     stringBuilder.append(getFault()).append(ln);
/* 267 */     stringBuilder.append(getFoul()).append(ln);
/* 268 */     stringBuilder.append(getPoint()).append(ln);
/* 269 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              G:\学习课件\软件工程与计算3\测试相关文档\docs\功能测试\testData.jar!\test\data\PlayerNormalInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */