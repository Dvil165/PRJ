/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.users;

import duy.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author baodu
 */
// sửa lại cách đặt tên class, tên package
// vì sao nên sẻialize
public class UsersDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        //1. get connection
        //2. create SQL String
        //3. create Statement object
        //4. execute query
        //5. process result
        // obj đặc biệt là của jdbc api 
        //1 bắt buộc phải khai báo
        //2 xử lí
        //3 đóng lại at anycost

        // alt shift F format code
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        boolean rs = false;
        try {
            // 1.get connection
            // có connection chưa chắc đã có kết nối
            con = DBHelper.getConnection();

            // nên kiểm tra khác nuill trước khi đi tiếp
            if (con != null) { // dong nay la qry
                //2. create SQL string
                // mỗi mệnh dề phải trên 1 dòng, các chữ/câu lệnh cách nhau space
                String sql = "select username "
                        + "From users "
                        + "Where username = ? "
                        + "And password = ?";

                //3. create statement obj
                stm = con.prepareStatement(sql);
                // do nó đang là prepare stm nên phải có set
                // ? trong sql đc tính từ trái sang phải, vị trí số 1
                stm.setString(1, username);
                stm.setString(2, password);

                //4. execute Query
                result = stm.executeQuery();
                // nếu dùng statement thì hàm này phải truyền ở hàm này
                // nên đây cũng là lí do mà nó phải load lại

                // 5. process result
                if (result.next()) { // dong if nay la mapping
                    rs = true;
                } // end of username and password are checked
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rs;

    }

    private List<UsersDTO> accounts;

    public List<UsersDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String lastName) throws SQLException, /*ClassNotFoundException*/ NamingException {
        {

            Connection con = null;
            PreparedStatement stm = null;
            ResultSet result = null;

            try {
                // 1.get connection
                // có connection chưa chắc đã có kết nối
                con = DBHelper.getConnection();

                // nên kiểm tra khác nuill trước khi đi tiếp
                if (con != null) { // dong nay la qry
                    String sql = "Select username, password, lastName, isAdmin "
                            + "From Users "
                            + "Where lastName Like ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + lastName + "%");
                    result = stm.executeQuery();
                    while (result.next()) {
                        // get data from result set
                        String username = result.getString("username");
                        String password = result.getString("password");
                        String fullname = result.getString("lastName");
                        boolean role = result.getBoolean("isAdmin");

                        // set properties cho DTO
                        UsersDTO dto = new UsersDTO(username, password, fullname, role);

                        if (this.accounts == null) {
                            this.accounts = new ArrayList<>();
                        }
                        this.accounts.add(dto);
                    }// end accounts exits
                } // end con has been available
            } finally {
                if (result != null) {
                    result.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            }

        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        //1. get connection
        //2. create SQL String
        //3. create Statement object
        //4. execute query
        //5. process result
        // obj đặc biệt là của jdbc api 
        //1 bắt buộc phải khai báo
        //2 xử lí
        //3 đóng lại at anycost

        // alt shift F format code
        Connection con = null;
        PreparedStatement stm = null;
        
        boolean rs = false;
        try {
            // 1.get connection
            // có connection chưa chắc đã có kết nối
            con = DBHelper.getConnection();

            // nên kiểm tra khác nuill trước khi đi tiếp
            if (con != null) { // dong nay la qry
                //2. create SQL string
                // mỗi mệnh dề phải trên 1 dòng, các chữ/câu lệnh cách nhau space
                String sql = "Delete From Users "
                        + "Where username = ?";
                //3. create statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                // do nó đang là prepare stm nên phải có set
                // ? trong sql đc tính từ trái sang phải, vị trí số 1
                
                //4. execute Update
                int effectedRows = stm.executeUpdate(); // doi voi select la excuQry
                // con lai delete, up, insert la excuteUpdate
                // nếu dùng statement thì hàm này phải truyền ở hàm này
                // nên đây cũng là lí do mà nó phải load lại
                
                // 5. process result
                if (effectedRows > 0){
                    rs = true;
                }
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return rs;
    }
}
