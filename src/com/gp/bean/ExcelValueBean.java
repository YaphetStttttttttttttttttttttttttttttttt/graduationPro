package com.gp.bean;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.gp.model.vo.GetExcelValue;

public class ExcelValueBean {
	
	/**
	 *  ..传入excel一条数据，根据resultType返回处理后的数据
	 * @param cell
	 * @param resultType
	 * @param isNecessary 是否为空 
	 * @return
	 */
	public static GetExcelValue valueDeal(Cell cell, int resultType, boolean isNecessary) {
		GetExcelValue gev = new GetExcelValue();
		boolean result = false;
		if(resultType == GlobalName.resultType_STRING) {
			if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
				String stringValue = cell.getStringCellValue();
				result = true;
				gev.setStringValue(stringValue);
			}else {	
				//如果这一条数据在数据库不是必要的，返回true
				if(!isNecessary) {
					result = true;
				}
			}
		}else {
			int intValue = 0; 
			long longValue = 0; 
			double doubleValue = 0;
			float floatValue = 0;
			if(cell != null && cell.getCellType() == CellType.NUMERIC) {
				switch (resultType) {
				case GlobalName.resultType_INT: 
					intValue = (int)cell.getNumericCellValue(); gev.setIntValue(intValue); break;
				case GlobalName.resultType_LONG: 
					longValue = (long)cell.getNumericCellValue(); gev.setLongValue(longValue); break;
				case GlobalName.resultType_DOUBLE: 
					doubleValue = (double)cell.getNumericCellValue(); gev.setDoubleValue(doubleValue); break;
				case GlobalName.resultType_FLOAT: 
					floatValue = (float)cell.getNumericCellValue(); gev.setFloatValue(floatValue); break;
				default:break;
				}
				result = true;
			}else if(cell != null && cell.getCellType() == CellType.FORMULA){
				switch (resultType) {
				case GlobalName.resultType_INT: 
					intValue = (int)cell.getNumericCellValue(); gev.setIntValue(intValue); break;
				case GlobalName.resultType_LONG: 
					longValue = (long)cell.getNumericCellValue(); gev.setLongValue(longValue); break;
				case GlobalName.resultType_DOUBLE: 
					doubleValue = (double)cell.getNumericCellValue(); gev.setDoubleValue(doubleValue); break;
				case GlobalName.resultType_FLOAT: 
					floatValue = (float)cell.getNumericCellValue(); gev.setFloatValue(floatValue); break;
				default:break;
				}
				result = true;
			}else {
				if(!isNecessary) {
					result = true;
				}
			}
		}
		gev.setResult(result);
		return gev;
	}
}
