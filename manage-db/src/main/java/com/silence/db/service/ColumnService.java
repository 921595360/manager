package com.silence.db.service;

import com.silence.db.dao.ColumnDao;
import com.silence.db.entity.Column;
import com.silence.db.mysql.MySqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.List;

/**
 * 
 *  列 
 * ColumnService  
 *   
 * silence  
 * silence  
 * 2016年1月7日 下午1:50:44  
 *   
 * @version 1.0.0  
 *
 */
@Service
public class ColumnService {
	@Autowired
	private ColumnDao columnDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private TableService tableService;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * findByColumnId(根据Id查询)  
	 * @param columnId
	 * @return   
	 *Column  
	 * @exception   
	 * @since  1.0.0
	 */
	public Column findByColumnId(String columnId){
		return columnDao.findOne(columnId);
	}
	
	
	/**
	 * 获取所有列
	 * @param tableCode 表编码
	 * @return
	 */
	public Page<Column> findByTableCode(String tableCode,Integer pageNum, Integer pageSize){
		Page<Column> page = columnDao.findByTableCode(tableCode,pageNum,pageSize);
		return page;
	}
	
	
	
	/**
	 * 
	 * addColumn(添加列)  
	 * @param column
	 * @param isChange   是否影响表结构
	 *void  
	 * @return 
	 * @exception   
	 * @since  1.0.0
	 */
	public Column addColumn(Column column,boolean isChange){
		column.setTable(tableService.getTableByTableCode(column.getTableCode()));
		String sql=MySqlUtil.GenerAddColumnSql(column);
		if(isChange) jdbcTemplate.update(sql);
		return columnDao.save(column);
	}
	
	/**
	 * 
	 * deleteColumn(删除列)  
	 * @param column
	 * @param isChange   是否影响表结构
	 *void  
	 * @exception   
	 * @since  1.0.0
	 */
	public void deleteColumn(Column column,boolean isChange){
		String sql=MySqlUtil.GenerDelColumnSql(column);
		if(isChange) jdbcTemplate.update(sql);
		columnDao.delete(column);
	}
	
	
	/**
	 * 
	 * updateColumn(更新列)  
	 * @param column
	 * @param isChange   是否影响表结构
	 *void  
	 * @exception   
	 * @since  1.0.0
	 */
	public void updateColumn(Column column,boolean isChange){
		column.setTable(tableService.getTableByTableCode(column.getTableCode()));
		
		if(isChange){
			String sql=MySqlUtil.GenerUpdateColumnSql(column);
			jdbcTemplate.update(sql);
		}
		columnDao.save(column);
	}

	
	/**
	 * 
	 * deleteColumnByTableCode(根据表名删除列)  
	 * @param tableCode
	 * @param isChange   是否影响表结构
	 *void  
	 * @exception   
	 * @since  1.0.0
	 */
	public void deleteColumnByTableCode(String tableCode, boolean isChange) {
		List<Column> columns = this.findByTableCode(tableCode);
		for(Column column:columns){
			
			if(isChange){
				String sql=MySqlUtil.GenerDelColumnSql(column);
				jdbcTemplate.update(sql);
			}
			
			columnDao.delete(column);
		}
	}

	public List<Column> findByTableCode(String tableCode) {
		return columnDao.findByTableCode(tableCode);
	}


	/**
	 * 
	 * deleteColumn(删除列)  
	 * @param columnId   
	 *void  
	 * @exception   
	 * @since  1.0.0
	 */
	public void deleteColumn(String columnId) {
		Column column = this.findByColumnId(columnId);
		deleteColumn(column, true);
	}
	
	
	
}
