package com.silence.db.dao;


import com.silence.db.entity.Column;
import com.silence.db.entity.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ColumnRepository extends CrudRepository<Column,String> {
	/**
	 * findByTableCode(根据tableCode查询列)  
	 * @param tableCode 表名
	 * @return   
	 *List<Column>  
	 * @exception   
	 * @since  1.0.0
	 */
	public Page<Column> findByTableCode(String tableCode, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("delete from Column c where c.table=?1")
	public void deleteByTable(Table table);

	public List<Column> findByTableCode(String tableCode);

}
