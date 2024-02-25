package dao;

import bean.GuestBookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GuestBookDAO {
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/board_project";
    private String username = "root";
    private String password = "1234";

    private static GuestBookDAO guestBookDAO = new GuestBookDAO();
    public static GuestBookDAO getInstance(){ return guestBookDAO; }

    public GuestBookDAO(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getConnection(){
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean guestbookWrite(Map<String, String> map){
        String sql = "INSERT INTO guestbook (name, email, homepage, title, content, logtime)" +
                "VALUES(?,?,?,?,?,NOW())";

        getConnection();

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, map.get("name"));
            pstmt.setString(2, map.get("email"));
            pstmt.setString(3, map.get("homepage"));
            pstmt.setString(4, map.get("title"));
            pstmt.setString(5, map.get("content"));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try{
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    public int getTotalA(){
        int totalA = 0;
        String sql = "SELECT count(*) from guestbook";

        getConnection();

        try {
            pstmt=conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            totalA = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return totalA;
    }

    public List<GuestBookDTO> guestbookList(int startNum, int endNum){
        List<GuestBookDTO> list = new ArrayList<GuestBookDTO>();
        String sql = "SELECT * FROM board order by ref desc, seq asc LIMIT ?,?";

        getConnection();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, startNum);
            pstmt.setInt(2, endNum);
            rs = pstmt.executeQuery();

            while (rs.next()){
                GuestBookDTO guestBookDTO = new GuestBookDTO();
                guestBookDTO.setName(rs.getString("name"));
                guestBookDTO.setEmail(rs.getString("email"));
                guestBookDTO.setHomepage(rs.getString("homepage"));
                guestBookDTO.setTitle(rs.getString("title"));
                guestBookDTO.setContent(rs.getString("content"));
                guestBookDTO.setLogtime(rs.getDate("logtime"));

                list.add(guestBookDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }finally {
            try{
                if(rs!=null) rs.close();
                if(conn!=null) conn.close();
                if(pstmt!=null) pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }
}
