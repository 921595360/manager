package com.silence.db.mysql;

import com.silence.common.CommonUtils;
import com.silence.db.entity.Column;
import com.silence.db.entity.Table;
import com.silence.db.entity.Table.TableType;
import java.util.List;

/**
 * mysql数据库操作工具类
 *   
 * MySqlUtil  
 *   
 * silence  
 * silence  
 * 2016年1月4日 下午4:26:36  
 *   
 * @version 1.0.0  
 *
 */

public class MySqlUtil {
	
	
	/**
	 * 
	 * genPageSql(生成分页sql)  
	 * @param pageNum
	 * @param pageSize
	 * @return   
	 *String  
	 * @exception   
	 * @since  1.0.0
	 */
	public static String genPageSql(Integer pageNum,Integer pageSize){
		String result=" limit "+(pageNum-1)*pageSize+","+pageSize;
		return result;
	}
	
	
	
	/**
	 * 
	 * getDataTypeString(将column中的数据类型转为数据库对应的类型字符串)  
	 * @param dataType column中的数据类型
	 * @param length 数据长度
	 * @param decimalCount 小数点长度，当数据类型为小数时该值不能为空
	 * @return   
	 *String  
	 * @exception   
	 * @since  1.0.0
	 */
	public static String getDataTypeString(int dataType,int length,int decimalCount){
		
		
		switch (dataType) {
		
		case java.sql.Types.INTEGER:
			return length>0?" INTEGER("+length+")":" INTEGER";
			
		case java.sql.Types.BIGINT:
			return length>0?" BIGINT("+length+")":" BIGINT";
			
		case java.sql.Types.DECIMAL:
			return length>0?" DECIMAL"+"("+length+","+decimalCount+")":" DECIMAL"+"(10,"+decimalCount+")";
			
		case java.sql.Types.VARCHAR:
			return length>0?" VARCHAR("+length+")":" VARCHAR";
			
		default:
			break;
		}
		
		
		return null;
	}
	
	
	/**
	 * GencreateTableSql(生成创建表的sql)  
	 * @param table
	 * @return   
	 *String  
	 * @exception   
	 * @since  1.0.0
	 */
	public static String GencreateTableSql(Table table){
		
	  List<Column> columns = table.getColumns();
		String sql="";
		if(table.getTableType().equals(TableType.TABLE.value)){
			sql+="CREATE TABLE `"+table.getTableCode()+"` (";
			
			int i=0;
			for(Column column:columns){
				sql+=column.getColumnCode()+" ";
				
				sql+=getDataTypeString(column.getDataType(), column.getLen(), column.getDecimalCount());
				
				
				/*是否为主键*/
				if(column.isPK()) sql+=" PRIMARY KEY ";
				
				/*能否为空*/
				if(false==column.getNullAble()) sql+=" NOT NULL ";
				
				/*给列加注释*/
				sql+=" COMMENT'"+column.getColumnName()+"'";
				
				/*设置默认值*/
				if(null!=column.getDefaultValue()) sql+=" DEFAULT '"+column.getDefaultValue()+"' ";
				
				i++;
				
				if(i!=columns.size()) sql+=",";
			}
			
			sql+=")";
			
		}else if(table.getTableType().equals(TableType.VIEW.value)){
			/*如果创建的是视图，暂时不处理，人工创建对应视图*/
			return null;
		}else{
			return null;
		}
		return sql;
	}
	
	
	/**
	 * 
	 * GenerateDropTableSql(生成删除表的sql)  
	 * @param tableCode
	 * @return   
	 *String  
	 * @exception   
	 * @since  1.0.0
	 */
	public static String GenerateDropTableSql(String tableCode){
		String sql="DROP TABLE `"+tableCode+"`";
		return sql;
	}
	
	/**
	 * 
	 * GenerUpdateColumnSql(生成修改列的sql)  
	 * @param column   
	 *String   
	 * @exception   
	 * @since  1.0.0
	 */
	public static String GenerUpdateColumnSql(Column column){
		String sql="ALTER TABLE `"+column.getTableCode()+"` MODIFY COLUMN `"+column.getColumnCode()+"` ";
		sql+=getDataTypeString(column.getDataType(), column.getLen(), column.getDecimalCount());
		if(!column.getNullAble()){
			sql+=" NOT NULL ";
		}
		
		if(!CommonUtils.isNullOrEmpty(column.getDefaultValue())){
			sql+=" DEFAULT '"+column.getDefaultValue()+"' ";
		}
		sql+=" COMMENT'"+column.getColumnName()+"' ";

		
		/*如果是主键则先删除该表主键，然后添加该列为主键（暂时这么实现，后续优化为联合主键）*/
		if(column.isPK()){
			sql+=" ,DROP PRIMARY KEY,";
			sql+="ADD PRIMARY KEY (`"+column.getColumnCode()+"`) ";
		}
		return sql;
	}
	
	
	/**
	 * 
	 * GenerAddColumnSql(生成添加列的sql)  
	 * @param column
	 * @return   
	 *String  
	 * @exception   
	 * @since  1.0.0
	 */
	public static String GenerAddColumnSql(Column column){
		String sql="ALTER TABLE `"+column.getTableCode()+"` ADD COLUMN `"+column.getColumnCode()+"` ";
		sql+=getDataTypeString(column.getDataType(), column.getLen(), column.getDecimalCount());
		if(!column.getNullAble()){
			sql+=" NOT NULL ";
		}
		
		if(!CommonUtils.isNullOrEmpty(column.getDefaultValue())){
			sql+=" DEFAULT '"+column.getDefaultValue()+"' ";
		}
		sql+=" COMMENT'"+column.getColumnName()+"' ";

		
		/*如果是主键则先删除该表主键，然后添加该列为主键（暂时这么实现，后续优化为联合主键）*/
		if(column.isPK()){
			sql+=" ,DROP PRIMARY KEY,";
			sql+="ADD PRIMARY KEY (`"+column.getColumnCode()+"`) ";
		}
		return sql;
	}

	/**
	 * 
	 * GenerDelColumnSql(生成删除列的sql)  
	 * @param column
	 * @return   
	 *String  
	 * @exception   
	 * @since  1.0.0
	 */
	public static String GenerDelColumnSql(Column column){
		String sql="ALTER TABLE `"+column.getTableCode()+"` DROP COLUMN `"+column.getColumnCode()+"` ";
		
		/*如果是主键则直接删除该表的主键*/
		if(column.isPK()){
			sql+=" ,DROP PRIMARY KEY";
		}
		return sql;
	}
	
	


}
