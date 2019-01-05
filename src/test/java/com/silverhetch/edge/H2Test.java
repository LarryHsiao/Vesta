package com.silverhetch.edge;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2Test {
    @Test
    public void simple() throws Exception {
        Class.forName("org.h2.Driver");
        final Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "", "");
        final Statement stmt = conn.createStatement();
        stmt.execute("drop table if exists user");
        stmt.execute("create table user(id int primary key, name varchar(100))");
        stmt.execute("insert into user values(1, 'hello')");
        stmt.execute("insert into user values(2, 'world')");

        final ResultSet rs = stmt.executeQuery("select * from USER");

        int count = 0;
        while (rs.next()) {
            System.out.println("id " + rs.getInt("id") + " name " + rs.getString("name"));
            count++;
        }

        Assert.assertEquals(2, count);
        stmt.close();
    }
}
