package com.silence.db.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.persistence.Column;

/**
 * Created by silence on 2018/1/4.
 */
@Entity
@javax.persistence.Table(name="sy_page")
public class Page {

    @Id
    @javax.persistence.Column(length=32,name="page_id")
    private String pageId;

    @Column(name="table_id",length = 32)
    private String tableId;  //对应表

    @Column(name="load_script")
    private String loadScript;  //加载脚本

    @Column(name="url")
    private String url;  //页面访问地址

    @Column(name="title")
    private String title;  //标题

    @Column(name="type")
    private Integer type;//页面类型

    /*页面类型*/
    public static enum PageType{
        LIST(1),FORM(2),OTHER(-1);
        public final Integer value;
        PageType(int value){this.value=value;}
        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getLoadScript() {
        return loadScript;
    }

    public void setLoadScript(String loadScript) {
        this.loadScript = loadScript;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Page() {
    }

    public Page(String tableId, String loadScript, String url, String title, Integer type) {
        this.tableId = tableId;
        this.loadScript = loadScript;
        this.url = url;
        this.title = title;
        this.type = type;
    }
}
