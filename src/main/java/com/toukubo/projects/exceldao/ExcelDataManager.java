package com.toukubo.projects.exceldao;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.toukubo.projects.Project;

public class ExcelDataManager {
	private static final String FILENAME = "projects.xlsx";
	public List<Project> loadProjects(){
		try{
			
			net.enclosing.list.List listUtil  = new net.enclosing.list.List();
			List projects = listUtil.list(Project.class);
			


		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return null;
	}
	public static String getStr(Cell cell){ //データ型毎の読み取り
		switch(cell.getCellType()){
		case Cell.CELL_TYPE_BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
			//return cell.getStringCellValue();(※）
		case Cell.CELL_TYPE_NUMERIC:
			return Double.toString(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		}
		return "";// CELL_TYPE_BLANK,CELL_TYPE_ERROR
	}
	public static void main(String args[]){
		ExcelDataManager manager = new ExcelDataManager();
		manager.loadProjects();
		System.err.println("hoge");
	}
}
