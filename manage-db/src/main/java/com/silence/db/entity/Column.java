package com.silence.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 列（字段）
 *   
 * Column  
 *   
 * silence  
 * silence  
 * 2016年1月4日 下午4:27:46  
 *   
 * @version 1.0.0  
 *
 */
@Entity
@javax.persistence.Table(name="sy_column")
public class Column implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@javax.persistence.Column(length=32,name="column_id")
	private String columnId;

	/*字段编码*/
	@javax.persistence.Column(name="column_code")
	private String columnCode;
	
	/*列名*/
	@javax.persistence.Column(name="column_name")
	private String columnName;
	
	/*表*/
	@JsonIgnore
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="table_id",referencedColumnName="table_id")
	private Table table;
	
	/*表编码*/
	@javax.persistence.Column(name="table_code",nullable=false)
	private String tableCode;
	
	
	/*长度*/
	@javax.persistence.Column(name="len")
	private int len;
	
	/* 小数位数(紧针对数字类型) */
	@javax.persistence.Column(name="decimal_count")
    private int decimalCount;
    
    /** 是否主键 freemarker布尔类型显示需要特殊处理，此处修改存储类型回避该问题*/
	@javax.persistence.Column(name="is_pk",columnDefinition = "integer",length = 1)
    private boolean isPK;
	
	/*能否为空*/
	@javax.persistence.Column(name="null_able",columnDefinition = "integer",length = 1)
    private boolean nullAble;
	
	/**
	 * 数据类型
	 * 3:DECIMAL 小数
	 * 4:INTEGER 整数
	 * -5:BIGINT 长整数
	 * 12:VARCHAR 字符串
	 */
	@javax.persistence.Column(name="data_type")
	private Integer dataType;
	
	/*默认值*/
	@javax.persistence.Column(name="default_value")
	private String defaultValue;
	
	/*顺序*/
	@javax.persistence.Column(name="column_order")
	private Integer columnOrder;

	public static enum DataType{
		DECIMAL(3),//小数
		INTEGER(4),//整数
		BIGINT(-5),//长整数
		VARCHAR(12);//字符串
		public final Integer value;
		DataType(int value){this.value=value;}
	}
	

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public boolean isPK() {
		return isPK;
	}

	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(Integer columnOrder) {
		this.columnOrder = columnOrder;
	}

	public int getDecimalCount() {
		return decimalCount;
	}

	public void setDecimalCount(int decimalCount) {
		this.decimalCount = decimalCount;
	}

	public boolean getNullAble() {
		return nullAble;
	}

	public void setNullAble(boolean nullAble) {
		this.nullAble = nullAble;
	}

	

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}



	@Override
	public String toString() {
		return "Column [columnId=" + columnId + ", columnCode=" + columnCode + ", columnName=" + columnName + ", table="
				+ table + ", tableCode=" + tableCode + ", len=" + len + ", decimalCount=" + decimalCount + ", isPK="
				+ isPK + ", nullAble=" + nullAble + ", dataType=" + dataType + ", defaultValue=" + defaultValue
				+ ", columnOrder=" + columnOrder + "]";
	}
	
}
