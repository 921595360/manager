package com.silence.db.dao;


import com.silence.db.entity.Compoent;
import com.silence.db.entity.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompoentRepository extends CrudRepository<Compoent,String> {

	List<Compoent> findAll();

	List<Compoent> findByPageId(String pageId);

	List<Compoent> findByPageIdAndType(String pageId, Integer button);
}
