package com.silence.db.dao;


import com.silence.db.entity.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableRepository extends CrudRepository<Table,String> {

	public List<Table> findAll();
	
	public Table findByTableCode(String tableCode);

	Page<Table> findAll(Pageable page);
}
