package com.toukubo.projects;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.toukubo.sqlite.BasicEntity;


@Entity
public class Project extends BasicEntity{
	//	public static final String FIND_ALL = "findAllPomodoros";

	String name = "";
	String link = "";

	boolean done = false;

	boolean triedAtLeastOnce = false;
	boolean oneshot = false;
	String scaleWith = "me";
	boolean cf = false;
	int timeIncrease = 0;
	int moneyIncrease =0;
	int possibility = 0;
	int moneyIncreaseAsSideEffect = 0;
	int sideEffectPossibility = 0;

	int timeNeeded =0;
	int moneyNeeded = 0;
	int outsourcability = 0;

	float totalNeeded = 0;

	double simpleRoi = 0;


	int totalIncrease = 0;
	boolean breakdown = false;
	boolean essentialisable = false;
	boolean brokenDown = false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean isTriedAtLeastOnce() {
		return triedAtLeastOnce;
	}
	public void setTriedAtLeastOnce(boolean triedAtLeastOnce) {
		this.triedAtLeastOnce = triedAtLeastOnce;
	}
	public boolean isOneshot() {
		return oneshot;
	}
	public void setOneshot(boolean oneshot) {
		this.oneshot = oneshot;
	}
	public String getScaleWith() {
		return scaleWith;
	}
	public void setScaleWith(String scaleWith) {
		this.scaleWith = scaleWith;
	}
	public boolean isCf() {
		return cf;
	}
	public void setCf(boolean cf) {
		this.cf = cf;
	}
	public int getTimeIncrease() {
		return timeIncrease;
	}
	public void setTimeIncrease(int timeIncrease) {
		this.timeIncrease = timeIncrease;
	}
	public int getMoneyIncrease() {
		return moneyIncrease;
	}
	public void setMoneyIncrease(int moneyIncrease) {
		this.moneyIncrease = moneyIncrease;
	}
	public int getPossibility() {
		return possibility;
	}
	public void setPossibility(int possibility) {
		this.possibility = possibility;
	}
	public int getMoneyIncreaseAsSideEffect() {
		return moneyIncreaseAsSideEffect;
	}
	public void setMoneyIncreaseAsSideEffect(int moneyIncreaseAsSideEffect) {
		this.moneyIncreaseAsSideEffect = moneyIncreaseAsSideEffect;
	}
	public int getSideEffectPossibility() {
		return sideEffectPossibility;
	}
	public void setSideEffectPossibility(int sideEffectPossibility) {
		this.sideEffectPossibility = sideEffectPossibility;
	}
	public int getTimeNeeded() {
		return timeNeeded;
	}
	public void setTimeNeeded(int timeNeeded) {
		this.timeNeeded = timeNeeded;
	}
	public int getMoneyNeeded() {
		return moneyNeeded;
	}
	public void setMoneyNeeded(int moneyNeeded) {
		this.moneyNeeded = moneyNeeded;
	}
	public int getOutsourcability() {
		return outsourcability;
	}
	public void setOutsourcability(int outsourcability) {
		this.outsourcability = outsourcability;
	}
	public float getTotalNeeded() {
		return totalNeeded;
	}
	public void setTotalNeeded(float totalNeeded) {
		this.totalNeeded = totalNeeded;
	}
	public double getSimpleRoi() {
		return simpleRoi;
	}
	public void setSimpleRoi(double simpleRoi) {
		this.simpleRoi = simpleRoi;
	}
	public int getTotalIncrease() {
		return totalIncrease;
	}
	public void setTotalIncrease(int totalIncrease) {
		this.totalIncrease = totalIncrease;
	}
	public boolean isBreakdown() {
		return breakdown;
	}
	public void setBreakdown(boolean breakdown) {
		this.breakdown = breakdown;
	}
	public boolean isEssentialisable() {
		return essentialisable;
	}
	public void setEssentialisable(boolean essentialisable) {
		this.essentialisable = essentialisable;
	}
	public boolean isBrokenDown() {
		return brokenDown;
	}
	public void setBrokenDown(boolean brokenDown) {
		this.brokenDown = brokenDown;
	}

	public static void main(String[] args) {
		Project project = new Project();
		project.setName("testproject");
		project.save();
		List projects = (List)project.list();
		for (Object object : projects) {
			Project project2 =  (Project)object;
			System.err.println(project2.getName());
		}
	}
	@Transient 
	@Override
	public String getClassName() {
		return "Project";
	}
	@Transient 
	@Override
	public Class getClazz() {
		return Project.class;
	}
	@Transient 
	@Override
	public String getSqlitedbid() {
		return "project";
	}



}
