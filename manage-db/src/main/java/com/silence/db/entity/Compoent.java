package com.silence.db.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by silence on 2018/1/4.
 * 组件
 */
public class Compoent {
    @Id
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    @javax.persistence.Column(length=32,name="compoent_id")
    private String compoentId;
    @javax.persistence.Column(name="compoent_name",length = 20)
    private String compoentName;//名称
    @javax.persistence.Column(name="show_condition")
    private String showCondition;//显示条件
    @javax.persistence.Column(name="enable_condition")
    private String enableCondition;//启用条件
    @javax.persistence.Column(name="type")
    private Integer type;
    @javax.persistence.Column(name="page_id")
    private String pageId;//所属页面
    @javax.persistence.Column(name="click")
    private String click;//触发操作

    public static enum Type{
        BUTTON(1),//按钮
        COMBOX(2),//下拉框
        TEXT(3), //文本框
        NUMBER(4), //整数框
        DECIMAL(5), //小数框
        EADIO(6), //单选按钮
        CHECKBOX(7), //多选按钮
        DATEBOX(8), //日期框
        DATETIME(9);//日期时间
        public final Integer value;
        Type(int value){this.value=value;}
    }

    public String getCompoentId() {
        return compoentId;
    }

    public void setCompoentId(String compoentId) {
        this.compoentId = compoentId;
    }

    public String getCompoentName() {
        return compoentName;
    }

    public void setCompoentName(String compoentName) {
        this.compoentName = compoentName;
    }

    public String getShowCondition() {
        return showCondition;
    }

    public void setShowCondition(String showCondition) {
        this.showCondition = showCondition;
    }

    public String getEnableCondition() {
        return enableCondition;
    }

    public void setEnableCondition(String enableCondition) {
        this.enableCondition = enableCondition;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public Compoent() {
    }

    public Compoent(String compoentName, String showCondition, String enableCondition, Integer type, String pageId, String click) {
        this.compoentName = compoentName;
        this.showCondition = showCondition;
        this.enableCondition = enableCondition;
        this.type = type;
        this.pageId = pageId;
        this.click = click;
    }
}
