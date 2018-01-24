package com.silence.db.dao;

import com.silence.db.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * 
 *   
 * TableDao  
 *   
 * silence  
 * silence  
 * 2016年1月5日 上午9:59:10  
 *   
 * @version 1.0.0  
 *
 */
@Component
public class TableDao{
	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Table> findAll(){
		return tableRepository.findAll();
	}
	
	public Table findByTableCode(String tableCode){
		return tableRepository.findByTableCode(tableCode);
	}

	public Table findOne(String id) {
		return tableRepository.findOne(id);
	}

	public Table save(Table table) {
		return tableRepository.save(table);
	}

	public void delete(String tableId) {
		tableRepository.delete(tableId);
	}

	public void delTableData(String tableCode, String id) {
		jdbcTemplate.update("delete from "+tableCode+" where id= '"+id+"'");
	}

	public void updateTableData(String tableCode, String pk, Map<String, Object> data) {
		String sql="update "+tableCode+" t1 set ";
		String setSql="";
		for(String column:data.keySet()){
			setSql+=","+column+"='"+data.get(column)+"'";
		}
		//去除第一个','号
		setSql=setSql.substring(1);
		sql+=setSql+" where "+pk+"='"+data.get(pk)+"'";
		jdbcTemplate.update(sql);
	}

	public void addTableData(String tableCode, Map<String, Object> data) {

		String columnSql="";
		for(String column:data.keySet()){
			columnSql+=","+column;
		}
		//去除第一个','号
		columnSql=columnSql.substring(1);

		String valueSql="";
		for(String column:data.keySet()){
			valueSql+=",'"+data.get(column)+"'";
		}
		valueSql=valueSql.substring(1);

		String sql="insert into  "+tableCode+" ("+columnSql+") values("+valueSql+") ";
		jdbcTemplate.update(sql);
	}

	public List<Map<String, Object>> getTableDataByTableCode(String tableCode) {
		return jdbcTemplate.queryForList("select * from  "+tableCode+"");
	}

	public Long getAllTablesCount() {
		return tableRepository.count();
	}

	public Page<Table> getAllTables(Integer pageNum, Integer pageSize) {
		Pageable page=new PageRequest(pageNum-1,pageSize);
		return tableRepository.findAll(page);
	}

	public Integer getTableDataByTableCodeCount(String tableCode) {
		return jdbcTemplate.queryForObject("select count(0) from "+tableCode,Integer.class);
	}

	public long getTableCount() {
		return tableRepository.count();
	}
	
}
