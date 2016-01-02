package com.toukubo.projects;

import lombok.Data;

@Data
public class Link {
	Project parent = null;
	Project child = null;
	double possibility = 0f;
	

}
