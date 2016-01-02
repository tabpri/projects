package com.toukubo.projects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import net.arnx.jsonic.JSON;

@Data
public class Project {

	String name = "";
	
	String parents = "";
	String children = "";
	
	double roi = 0;
	double exponented = 0d;
	
	boolean actionable = false;
	boolean running = false;
	boolean onCriticalPathes = false;
	
	
	
	
//	int scale = 1;
	double integratedReturn = 0d;
	double integratedInvestment = 0d;
	
	// ----------------------------------------
	boolean cfValidCondition = false; 

	boolean done = false;

	boolean triedAtLeastOnce = false;
	boolean oneshot = false;
	String scaleWith = "me";
	boolean cf = false;
	int moneyIncreaseAsSideEffect = 0;

	int outsourcability = 0;

	float totalNeeded = 0;
	double childrenHourInvestmentTotal = 0d;
	double availableHourTotal = 0d;
	
	

	public static void main(String[] args) {
		net.enclosing.list.List list = new net.enclosing.list.List("./");
		Field field = new Field();
		List<Project> projects = list.list(Project.class);
		for (Iterator iterator = projects.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			project.setIntegratedReturn(project.getParentReturnTotal(projects,project));
			if(project.isOnCriticalPathes()){
				project.getChildrenInvestmentTotal(projects, project);
				System.err.println(project.getAvailableHourTotal());
				System.err.println(project.getAvailableHourTotal());
				field.setOnCriticalPathes(true);;
				field.setProjectOnCriticalPath(project);
				field.setProjectOnCriticalPath(project);
				
			}
		}
		
		projects = list.list(Project.class);
		for (Iterator iterator = projects.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			System.err.println("project : " + project.getName());
			System.err.println("=================================================================");
			
			double integratedReturn = project.getParentReturnTotal(projects,project);
			double integratedInvestment = project.getChildrenInvestmentTotal(projects,project);
			if(field.isOnCriticalPathes() && !project.isOnCriticalPathes() && project.getChildrenHourInvestmentTotal()>0){
				double remainingHours = field.getProjectOnCriticalPath().getAvailableHourTotal() - field.getProjectOnCriticalPath().getChildrenHourInvestmentTotal();
				remainingHours = remainingHours / 2;
				double possibility = integratedInvestment / remainingHours;
				double riskScore = field.getProjectOnCriticalPath().getIntegratedReturn() * possibility;
				
				integratedReturn -= riskScore;
			}
			double roi = integratedInvestment==0?0:(integratedReturn/integratedInvestment);
			if(integratedInvestment>0){
				System.err.println("----------------------------");
				System.err.println(integratedReturn);
				System.err.println(integratedInvestment);
				System.err.println(project.getChildrenHourInvestmentTotal());
				
			}
			
	
			project.setIntegratedReturn(integratedReturn);
			project.setIntegratedInvestment(integratedInvestment);
			project.setRoi(roi);
			
			double exponented = Math.pow(integratedReturn/10, 15/(integratedInvestment>15?15:integratedInvestment));
			project.setExponented(exponented);
			System.err.println(project.getRoi());
			
		}
		projects.sort(Comparator.comparingDouble(Project::getExponented).reversed());
		list.writeList(projects, Project.class);
	}
	

	private double getChildrenInvestmentTotal(List<Project> projects,Project root) {
		double total = 0;
		List<Link> childrenLinks =  getChildrenObjects(projects);
		for (Link childLink : childrenLinks) {
			if(childLink.getChild().getName().equals("ROOT")){
				total += 1*childLink.possibility;
			}else{
				double value = childLink.getChild().getChildrenInvestmentTotal(projects,root)*childLink.possibility;;
				total += value;
				if(childLink.getChild().getName().equals("hour"))
					root.setChildrenHourInvestmentTotal(root.getChildrenHourInvestmentTotal()+childLink.possibility);
				if(childLink.getChild().getName().equals("available_hour"))
					root.setAvailableHourTotal(root.getAvailableHourTotal()+childLink.possibility);
			}
		}
		return total;
	}

	private List<Link> getChildrenObjects(List<Project> projects) {
		List<Link> returned = new ArrayList<Link>();
		String string = this.getChildren();
//		string = "hoge:0.5";
		Map<String,BigDecimal> rows = JSON.decode(string);
		
		for (String name : rows.keySet()) {
			Link link = new Link();
			Project project = findByName(name,projects);
			link.setChild(project);
			BigDecimal doublevalue = (BigDecimal)rows.get(name);
			link.setPossibility(doublevalue.doubleValue());
			returned.add(link);
			
		}
//		map.keySet()..list().forEach();
		return returned;	}

	private double getParentReturnTotal(List<Project> projects,Project root) {
		double total = 0;
		List<Link> parentLinks =  getParentObjects(projects);
		for (Link parentLink : parentLinks) {
			if(parentLink.getParent().getName().equals("ROOT")){
				total += 1*parentLink.possibility;
			}else{
				total += parentLink.getParent().getParentReturnTotal(projects,root)*parentLink.possibility;
				System.err.println("currenttotal:"+total);
				System.err.println("parent:"+parentLink.getParent().getName());
				if(parentLink.getParent().getName().equals("on_critical_path")){
					root.setOnCriticalPathes(true);
				}

			}
		}
		
		System.err.println("whole total of this gen"+total+this.getName());
		return total;
	}
	public List<Link> getParentObjects(List<Project> projects){
		List<Link> returned = new ArrayList<Link>();
		String string = this.getParents();
//		string = "hoge:0.5";
		Map<String,BigDecimal> rows = JSON.decode(string);
		
		for (String parentName : rows.keySet()) {
			Link link = new Link();
			System.err.println(parentName);
			Project project = findByName(parentName,projects);
			link.setParent(project);
			BigDecimal doublevalue = (BigDecimal)rows.get(parentName);
			link.setPossibility(doublevalue.doubleValue());
			returned.add(link);
			
		}
//		map.keySet()..list().forEach();
		return returned;
				
	}

	private Project findByName(String parentName, List<Project> projects) {
		List<Project> returned =projects.stream().filter(p -> p.getName().equals(parentName)).collect(Collectors.toList());
		if(returned.size()>0){
			return (Project)returned.get(0); 
			
		}
		return null;
	}

}
