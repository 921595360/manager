package com.silence.db.service;

import com.silence.common.PKUtil;
import com.silence.db.dao.CompoentRepository;
import com.silence.db.dao.PageRepository;
import com.silence.db.entity.Column;
import com.silence.db.entity.Compoent;
import com.silence.db.entity.Page;
import com.silence.db.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by silence on 2018/1/4.
 */
@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private CompoentRepository compoentRepository;


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
        //添加列
        List<Column> columns = table.getColumns();
        for (Column column:columns) {
            Compoent c = new Compoent();
            c.setPageId(page.getPageId());
            c.setExt1(column.getColumnName());
            c.setType(Compoent.Type.TD.value);
            c.setExt2(column.getColumnCode());
            compoentRepository.save(c);
        }
        pageRepository.save(page);

        //添加表单页面
        Page formPage = new Page();
        formPage.setPageId(PKUtil.getPrimarykeyStr());
        formPage.setTableId(table.getTableId());
        formPage.setTitle(table.getTableName()+"表单");
        formPage.setType(Page.PageType.FORM.value);
        formPage.setUrl("/page/form/"+formPage.getPageId());

        pageRepository.save(formPage);

        //添加组件
        for (Column column:columns) {
            Compoent c = new Compoent();
            c.setPageId(formPage.getPageId());
            c.setExt1(column.getColumnName());
            if(Column.DataType.BIGINT.value.equals(column.getDataType())||Column.DataType.INTEGER.value.equals(column.getDataType())){
                //整数
                c.setType(Compoent.Type.NUMBER.value);
            }else if(Column.DataType.DECIMAL.value.equals(column.getDataType())){
                //小数
                c.setType(Compoent.Type.DECIMAL.value);
            }else if(Column.DataType.VARCHAR.value.equals(column.getDataType())){
                c.setType(Compoent.Type.TEXT.value);
            }else{
                c.setType(Compoent.Type.TEXT.value);
            }
            c.setExt2(column.getColumnCode());
            c.setExt3("form-group col-xs-2");//样式
            compoentRepository.save(c);
        }

        Compoent save = new Compoent();
        save.setPageId(formPage.getPageId());
        save.setClick("var params=$('#form').serialize();$.get('/table/editData/"+table.getTableId()+"?'+params);");
        save.setExt1("保存");//显示文本
        save.setExt3("btn btn-success");//样式
        save.setType(Compoent.Type.BUTTON.value);
        compoentRepository.save(save);




        //添加默认页面中的默认组件
        Compoent compoent = new Compoent();
        compoent.setPageId(page.getPageId());
        compoent.setExt1("添加");//显示文本
        compoent.setClick(" openWin('800px', '450px',['/page/form/"+formPage.getPageId()+"','no'])");
        compoent.setExt3("btn btn-success");//样式
        compoent.setType(Compoent.Type.LINK.value);
        compoentRepository.save(compoent);
    }

    public Page findOne(String pageId) {
        return pageRepository.findOne(pageId);
    }


}
