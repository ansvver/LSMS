package library.seat.manage.service;

import org.junit.Assert;
import play.db.DB;
import play.test.UnitTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 12-11-9
 * Time: 下午2:05
 *
 * @author : mahs
 * @version : 1.x.0
 */
public abstract class BaseUnitTest extends UnitTest {

    /**
     * 预设数据库
     *
     * @param fileName 脚本文件名称
     */ 
	/**
     * 预设数据库
     *
     * @param fileName 脚本文件名称
     */ 
    protected void prepareDatabase(String fileName) {
        File scriptFile = new File(fileName);
        Connection conn = DB.getConnection();
        if (scriptFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(scriptFile));
                StringBuilder buildSql = new StringBuilder();
                String buffer = "";
                while ((buffer = br.readLine()) != null) {
                    if (buffer != null && buffer.length() > 0 && !buffer.startsWith("--")) {
                        buildSql.append(buffer);
                        if (buffer.endsWith(";")) {
                            if (!conn.isClosed()) {
                                String sql = buildSql.toString();
                                conn.createStatement().executeUpdate(sql);
                            }
                            buildSql.delete(0, buildSql.length());
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                Assert.assertTrue("执行预设数据脚本时出错 - " + fileName + ",找不到该文件", false);
            } catch (IOException e) {
                Assert.assertTrue("执行预设数据脚本时出错 - " + fileName + ",IO错误", false);
            } catch (SQLException e) {
                Assert.assertTrue("执行预设数据脚本时出错 - " + fileName + ",执行sql语句时出错" ,false);
            }
        }
    }
    
}

