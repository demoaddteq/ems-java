/**
 * 
 */
package com.mm.core.resource;
import java.util.*;
/**
 * @author Manoj Kumar Jha
 * date 10/5/2007
 */
/**
 * This class is use for set and get field value, name and type.
 * It defines four vector for that purpose.
 */
public class Support {
	private Vector fieldVec = new Vector();
	private Vector typeFieldVec = new Vector();
	private Vector dataVec = new Vector();
	private Vector typeDataVec = new Vector();
/**
 * 
 * @param strType it takes field type
 * @param strField it takes field name
 */
/**
 * This function is use for setting fields and respective type
 * in corresponsding Vector.
 */
	public void setFieldVec(String strType, String strField){
		typeFieldVec.add(strType);
		fieldVec.add(strField);
	}
/**
 * @param strType it takes type of Data 
 * @param strData it takes value of data.
 */
/**
 * This function is use for setting the data with its type.
 */
	public void setDataVec(String strType, String strData){
		typeDataVec.add(strType);
		dataVec.add(strData);
	}
/**
 * This funciton returns Field Vector for getting a particuler filed.
 */
	public Vector getFieldVec(){
		return fieldVec;
	}
/**
 * This function returns Type vector for getting a particuler field.
 * @return return type of field.
 */
	public Vector getTypeFieldVec(){
		return typeFieldVec;
	}
/**
 * This function returns Data vector which will save in Data Base.
 * @return return data
 */
	public Vector getDataVec(){
		return dataVec;
	}
/**
 * This function return type of corresponding Data.
 * @return type of data
 */
	public Vector getTypeDataVec(){
		return typeDataVec;
	}
}
