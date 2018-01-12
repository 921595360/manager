package com.silence.db.dao;


import com.silence.db.entity.Column;
import com.silence.db.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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
public class ColumnDao{
	
	@Autowired
	private ColumnRepository columnRepository;
	
	public Page<Column> findByTableCode(String tableCode, Integer pageNum, Integer pageSize){
		Pageable pageAble= new PageRequest(pageNum, pageSize);
		return columnRepository.findByTableCode(tableCode,pageAble);
	}
	
	public void deleteByTable(Table table){
		columnRepository.deleteByTable(table);
	}

	public Column findOne(String columnId) {
		return columnRepository.findOne(columnId);
	}

	public Column save(Column column) {
		return columnRepository.save(column);
	}

	public void delete(Column column) {
		columnRepository.delete(column);
	}

	public List<Column> findByTableCode(String tableCode) {
		return columnRepository.findByTableCode(tableCode);
	}

}
