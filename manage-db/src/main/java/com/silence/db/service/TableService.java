package com.silence.db.service;

import com.silence.common.PKUtil;
import com.silence.db.dao.TableDao;
import com.silence.db.entity.Column;
import com.silence.db.entity.Table;
import com.silence.db.mysql.MySqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 表
 * <p>
 * TableService
 * <p>
 * silence silence 2016年1月7日 下午1:50:57
 *
 * @version 1.0.0
 */
@Service
public class TableService {
    @Autowired
    private TableDao tableDao;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PageService pageService;


    /**
     * delTableData(删除表数据)
     *
     * @param tableCode
     * @param id        void
     * @throws
     * @since 1.0.0
     */
    public void delTableData(String tableCode, String id) {
        tableDao.delTableData(tableCode, id);
    }


    /**
     * updateTableData(更新表数据)
     *
     * @param tableCode 表编码
     * @param id        主键值（平台创建的表都会有一个id字段作为主键）
     * @param data      void
     * @throws
     * @since 1.0.0
     */
    public void updateTableData(String tableCode, String id, Map<String, Object> data) {
        tableDao.updateTableData(tableCode, id, data);
    }

    /**
     * addTableData(添加表数据)
     *
     * @param tableCode 表编码
     * @param data      数据map
     *                  void
     * @return
     * @throws
     * @since 1.0.0
     */
    public Map<String, Object> addTableData(String tableCode, Map<String, Object> data) {
        /*自动生成主键*/
        data.put("ID", PKUtil.getPrimarykeyStr());
        tableDao.addTableData(tableCode, data);
        return data;
    }

    /**
     * getTableDataByTableCode(根据表名查询数据)
     *
     * @param tableCode
     * @return List<Map<String,Object>>
     * @throws
     * @since 1.0.0
     */
    public List<Map<String, Object>> getTableDataByTableCode(String tableCode) {
		
		/*查询总记录*/
        tableDao.getTableDataByTableCodeCount(tableCode);
        return tableDao.getTableDataByTableCode(tableCode);
    }


    /**
     * getTableByTableCode(根据tableCode查询表)
     *
     * @param tableCode
     * @return Table
     * @throws
     * @since 1.0.0
     */
    public Table getTableByTableCode(String tableCode) {
        return tableDao.findByTableCode(tableCode);
    }

    /**
     * getTableById(根据Id查询表)
     *
     * @param Id
     * @return Table
     * @throws
     * @since 1.0.0
     */
    public Table getTableById(String Id) {
        return tableDao.findOne(Id);
    }

    /**
     * getAllTables(获取所有的表)
     *
     * @param pageSize
     * @param pageNum
     * @return List
     * <Table>
     * @throws @since 1.0.0
     */
    public Page<Table> getAllTables(Integer pageNum, Integer pageSize) {
        return tableDao.getAllTables(pageNum, pageSize);
    }

    /**
     * 添加表
     *
     * @param table
     * @param changeTable 是否影响表结构
     * @return
     */
    @Transactional
    public Table addTable(Table table,boolean changeTable) {
		/* 持久化到对应表 */
        tableDao.save(table);
		/*表默认都有一列主键*/
        if (null == table.getColumns()) {
            List<Column> columns = new ArrayList<Column>();
            Column c = new Column();
            c.setColumnCode("ID");
            c.setColumnName("主键");
            c.setDataType(12);
            c.setLen(32);
            c.setNullAble(false);
            c.setPK(true);
            c.setTable(table);
            c.setTableCode(table.getTableCode());
            columns.add(c);
            table.setColumns(columns);
        }
        for (Column column : table.getColumns()) {
            columnService.addColumn(column, false);
        }

        //添加默认页面
        pageService.addDefaultPage(table);

        if(!changeTable) return table;//不修改表结构

        /* 先删除再创建表 */
        jdbcTemplate.update("DROP TABLE IF EXISTS "+table.getTableCode());
        jdbcTemplate.update(MySqlUtil.GencreateTableSql(table));



        return table;
    }

    /**
     * 更新表（不会对表结构产生影响）
     *
     * @param table
     */
    @Transactional
    public void updateTable(Table table) {
		/*不对表结构进行操作*/
        tableDao.save(table);
    }


    /**
     * deleteTable(删除表)
     *
     * @param tableCode void
     * @throws
     * @since 1.0.0
     */
    public void deleteTable(String tableCode) {
        columnService.deleteColumnByTableCode(tableCode, false);
        jdbcTemplate.update(MySqlUtil.GenerateDropTableSql(tableCode));
        tableDao.delete(this.getTableByTableCode(tableCode).getTableId());
    }


    /**
     * getTableCount(获取表的总个数)
     *
     * @return long
     * @throws
     * @since 1.0.0
     */
    public long getTableCount() {
        return tableDao.getTableCount();
    }

}
