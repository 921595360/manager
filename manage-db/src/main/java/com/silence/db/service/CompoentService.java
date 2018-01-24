package com.silence.db.service;

import com.silence.db.dao.CompoentRepository;
import com.silence.db.entity.Compoent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 
 *  列 
 * CompoentService
 *   
 * silence  
 * silence  
 *
 * @version 1.0.0  
 *
 */
@Service
public class CompoentService {

	@Autowired
	private CompoentRepository compoentRepository;

	public List<Compoent> findByPageId(String pageId){
		return compoentRepository.findByPageId(pageId);
	}

	public List<Compoent> findByPageIdAndType(String pageId, Integer button) {
		return compoentRepository.findByPageIdAndType(pageId,button);
	}
}
