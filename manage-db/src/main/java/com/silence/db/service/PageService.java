package com.silence.db.service;

import com.silence.common.PKUtil;
import com.silence.db.dao.PageRepository;
import com.silence.db.entity.Page;
import com.silence.db.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by silence on 2018/1/4.
 */
@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private ColumnService columnService;


    /**
     * 为表创建默认页面
     * @param table
     */
    public void addDefaultPage(Table table) {
        Page page = new Page();
        page.setPageId(PKUtil.getPrimarykeyStr());
        page.setTableId(table.getTableId());
        page.setTitle(table.getTableName()+"列表");
        page.setType(Page.PageType.LIST.value);
        page.setUrl("/page/to/"+page.getPageId());
        pageRepository.save(page);
    }

    public Page findOne(String pageId) {
        return pageRepository.findOne(pageId);
    }


}
