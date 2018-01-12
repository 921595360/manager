package com.silence.init;

import com.silence.common.PKUtil;
import com.silence.db.dao.ColumnDao;
import com.silence.db.dao.TableDao;
import com.silence.db.entity.Column;
import com.silence.db.entity.Table;
import com.silence.db.service.TableService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统初始化
 * Init
 * 实现ApplicationListener接口主要是为了spring启动后获取bean执行相关操作
 * silence
 * silence
 * 2016年1月4日 下午1:01:05
 *
 * @version 1.0.0
 */

public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private TableService tableService;
    private TableDao tableDao;
    private ColumnDao columnDao;
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.tableService = event.getApplicationContext().getBean(TableService.class);
        this.tableDao = event.getApplicationContext().getBean(TableDao.class);
        this.columnDao = event.getApplicationContext().getBean(ColumnDao.class);
        this.dataSource = event.getApplicationContext().getBean(DataSource.class);
        initTable();
    }

    /**
     * 初始化表结构
     */
    public void initTable() {
        try {
            if(tableService.getTableCount()<1){
                DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
                ResultSet rs = metaData.getTables(null, null, null, null);
                Table table = null;
                while (rs.next()) {
                    table = new Table();
                    table.setTableId(PKUtil.getPrimarykeyStr());
                    table.setTableCode(rs.getString("TABLE_NAME"));
                    table.setTableName(rs.getString("TABLE_NAME"));

                    switch(rs.getString("TABLE_TYPE")){
                        case "TABLE":
                            table.setTableType(Table.TableType.TABLE.value);
                            break;
                        case "VIEW":
                            table.setTableType(Table.TableType.VIEW.value);
                            break;
                        default:
                            break;
                    }

                    //设置当前表的所有列
                    ResultSet rs1 = metaData.getColumns(null, null, table.getTableCode(), null);
                    Column column=null;
                    List<Column> columns=new ArrayList<>();
                    while(rs1.next()){
                        column=new Column();
                        column.setTableCode(table.getTableCode());
                        column.setTable(table);
                        column.setColumnName(rs1.getString("COLUMN_NAME"));
                        column.setColumnCode(rs1.getString("COLUMN_NAME"));
                        column.setDataType(rs1.getInt("DATA_TYPE"));
                        column.setDecimalCount(rs1.getInt("DECIMAL_DIGITS"));
                        column.setLen(rs1.getInt("COLUMN_SIZE"));
                        column.setNullAble(rs1.getInt("NULLABLE")>0?true:false);
                        columns.add(column);
                    }
                    table.setColumns(columns);
                    tableService.addTable(table,false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
