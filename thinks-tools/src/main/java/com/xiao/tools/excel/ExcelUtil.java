package com.xiao.tools.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelUtil {
	/**
	 * 导出Excel
	 * @param titles
	 * @return
	 * @throws IOException
	 */
	public static Workbook exportExcel(List<Object[]> datas,int ... titleRows) throws IOException{
		//创建工作簿
		Workbook wb = new SXSSFWorkbook(5000);
		//创建sheet页
		Sheet sheet = wb.createSheet();
		//定义变量
		int index = 0;
		int titleRow = titleRows==null || titleRows.length==0 || titleRows[0]<0 ? 0 : titleRows[0];
		Row row = null;
		Cell cell = null;
		//设置单元格样式
		//标题文本样式
		CellStyle titleCellStyle = wb.createCellStyle();
		//设置字体样式
		titleCellStyle.setFont(setFontStyle(wb, "Times New Roman", HSSFFont.BOLDWEIGHT_BOLD, (short)14,IndexedColors.BLACK.getIndex()));
		//水平垂直居中
		titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//填充单元格样式
		titleCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		
		//设置数据样式
		CellStyle cellStyle = wb.createCellStyle();
		CreationHelper createHelper = wb.getCreationHelper();
		
		//创建标题行
		for (Object[] title : datas) {
			//创建行
			row = sheet.createRow(index);
			if(index <= titleRow){
				//设置标题行高
				row.setHeightInPoints(40);
				//创建列
				for (int i = 0; i < title.length; i++) {
					cell = row.createCell(i);
					Object value = title[i]==null?"":title[i];
					//设置单元格值和样式
					if(value instanceof String){
						//设置标题列宽
						cell.setCellValue(value.toString());
						cell.setCellStyle(titleCellStyle);
						int len = title[i].toString().length();
						sheet.setColumnWidth(i, ("汉".getBytes().length+1)*len*256);
					}
				}	
			}else{
				for (int i = 0; i < title.length; i++) {
					cell = row.createCell(i);
					Object value = title[i];
					if(value instanceof String){
						//字符串
						cell.setCellValue(value.toString());
					}else if(value instanceof Date){
						//日期类型
						cell.setCellValue((Date)value);
						cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
					}else if(value instanceof Double){
						// 小数格式
						cell.setCellValue(NumberToTextConverter.toText(((Double) value)));
					}else{
						cell.setCellValue(value==null?"":value.toString());
					}
					cell.setCellStyle(cellStyle);
				}
			}
			index ++ ;
		}
		return wb;
	}
	
	/**
	 * 设置单元格值
	 * @param cell
	 * @param cellStyle
	 */
	public static void setCellValue(Cell cell,Object value,CellStyle cellStyle){
		if(value instanceof String){
			//字符串
			cell.setCellValue(value.toString());
		}else if(value instanceof Date){
			//日期类型
			cell.setCellValue((Date)value);
		}else if(value instanceof Double){
			// 小数格式
			cell.setCellValue(NumberToTextConverter.toText(((Double) value)));
		}else{
			cell.setCellValue(value==null?"":value.toString());
		}
		cell.setCellStyle(cellStyle);
	}
	
	/**
	 * 设置字体样式
	 * @param wb
	 * @param fontName
	 * @param boldweight
	 * @param size
	 * @param color
	 * @return
	 */
	public static Font setFontStyle(Workbook wb,String fontName,short boldweight,short size,short color){
		Font font = wb.createFont();
		font.setFontName(fontName);
		font.setBoldweight(boldweight);
		font.setFontHeightInPoints(size);
		font.setColor(color);
		return font;
	}
	
	/**
	 * 指定单元格合并
	 * @param wb
	 * @param positions 下标从0开始 起始行号，终止行号， 起始列号，终止列号 [0,0,0,1]
	 */
	public static void mergeCell(Workbook wb,List<int[]> positions){
		for (int[] position : positions) {
			CellRangeAddress region = new CellRangeAddress(position[0], position[1], position[2], position[3]);
			Sheet sheet = wb.getSheetAt(0);
			sheet.addMergedRegion(region);
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		List<Object[]> titles = new ArrayList<Object[]>();
		Object[] title1 = {"标题1","","标题3",""};
		Object[] title2 = {"标题4","标题5","标题6","标题8"};
		titles.add(title1);
		titles.add(title2);
		Workbook wb = exportExcel(titles,1);
		List<int[]> list = new ArrayList<int[]>	();
		int[] positions1 = {0,0,0,1};
		int[] positions2 = {0,0,2,3};
		list.add(positions1);	
		list.add(positions2);
		mergeCell(wb, list);
		//输出
		OutputStream out = new FileOutputStream("D:"+File.separator+System.currentTimeMillis()+".xlsx");
		wb.write(out);
		out.close();
		wb.close();
	}
}
