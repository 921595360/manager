package com.silence.core.controller;

import com.silence.db.entity.Column;
import com.silence.db.entity.Page;
import com.silence.db.entity.Table;
import com.silence.db.service.PageService;
import com.silence.db.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by silence on 2018/1/4.
 */
@Controller
@RequestMapping("page")
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private TableService tableService;

    @RequestMapping("/to/{pageId}")
    public ModelAndView toPage(@PathVariable("pageId")  String pageId, ModelAndView mv){
        Page page = pageService.findOne(pageId);
        Table table = tableService.getTableById(page.getTableId());
        if(page.getType().equals(Page.PageType.LIST.value)){
            //列表
            //获取显示字段
            List<Column> columns = table.getColumns();
            mv.addObject("columns",columns);
            mv.setViewName("/page/list");
        }
        return mv;
    }
}
