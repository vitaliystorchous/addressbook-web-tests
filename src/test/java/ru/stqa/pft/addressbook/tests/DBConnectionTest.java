package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.sql.*;

public class DBConnectionTest {

    @Test
    public void testDBConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from menuEditorItems");
            Items items = new Items();
            while (rs.next()) {
                items.add(new MenuEditorItem().withType(MenuEditorItem.Type.valueOf(rs.getString("type").toUpperCase())).withName(rs.getString("name")));
            }

            rs.close();
            st.close();
            conn.close();
            System.out.println(items);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
