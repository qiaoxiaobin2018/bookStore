package cn.itcast.bookStore.order.Dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;

public class OrderDao {
    private QueryRunner queryRunner = new TxQueryRunner();

}
