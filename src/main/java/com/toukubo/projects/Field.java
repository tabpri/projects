package com.toukubo.projects;
import lombok.Data;

@Data
public class Field {
	boolean withRunningConstraintNode = false;
	boolean withCacheflowConstraint = false;
	boolean onCriticalPathes = false;
	double remainingMoney = 0d;
	double remainingHours = 0d;
	double investrableHours = 0d;
	double investrableMoney = 0d;
	Project projectOnCriticalPath = null;
}
