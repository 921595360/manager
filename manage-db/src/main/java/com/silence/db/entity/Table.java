package com.silence.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 表（实体）
 *   
 * Table  
 *   
 * silence  
 * silence  
 * 2016年1月4日 下午4:28:22  
 *   
 * @version 1.0.0  
 *
 */
@Entity
@javax.persistence.Table(name="sy_table")
public class Table implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length=32,name="table_id")
	private String tableId;

	@Column(name="table_code")
	@NaturalId /*该注解类似联合主键的功能,并且加了该注解后，该字段不允许被修改*/
	private String tableCode;

	/*名称*/
	@Column(name="table_name")
	private String tableName;
	
	/*类型*/
	@Column(name="table_type")
	private Integer tableType;
	
	/**
	 * 表对应的所有列
	 */
	@JsonIgnore/*该注解用于将对象解析json时忽略掉加了注解的属性*/
	@OneToMany(mappedBy="table",fetch=FetchType.LAZY)
	private List<com.silence.db.entity.Column> columns;
	
	
	/*表类型*/
	public static enum TableType{
		TABLE(1),VIEW(2);
		public final Integer value;
		
		TableType(int value){this.value=value;}
		
		@Override
		public String toString() {
			return String.valueOf(this.value);
		}
		
		
	}


	public String getTableCode() {
		return tableCode;
	}


	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public Integer getTableType() {
		return tableType;
	}


	public void setTableType(Integer tableType) {
		this.tableType = tableType;
	}

	public List<com.silence.db.entity.Column> getColumns() {
		return columns;
	}

	public void setColumns(List<com.silence.db.entity.Column> columns) {
		this.columns = columns;
	}


	


	public String getTableId() {
		return tableId;
	}


	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	@Override
	public String toString() {
		return "Table [tableId=" + tableId + ", tableCode=" + tableCode + ", tableName=" + tableName + ", tableType="
				+ tableType + ", columns=" + columns + "]";
	}
	
}
