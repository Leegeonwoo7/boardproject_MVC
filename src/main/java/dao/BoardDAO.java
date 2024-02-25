package dao;

import bean.BoardDTO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO {
    private SqlSessionFactory sqlSessionFactory;

    private static BoardDAO boardDAO = new BoardDAO();
    public static BoardDAO getInstance() { return boardDAO; }

    public BoardDAO(){
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean makeBoard(Map<String, String> map){
        try {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("boardSQL.makeBoard", map);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }catch(PersistenceException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<BoardDTO> boardList(int startNum, int endNum){
        Map<String, Integer> pageNum = new HashMap<String, Integer>();
        pageNum.put("startNum", startNum);
        pageNum.put("endNum", endNum);

        List<BoardDTO> list = new ArrayList<BoardDTO>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        list = sqlSession.selectList("boardSQL.boardList", pageNum);
        sqlSession.close();
        return list;
    }

    public int getTotalA() {
        int totalA = 0;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        totalA = sqlSession.selectOne("boardSQL.getTotalA");
        sqlSession.close();
        return totalA;
    }
}
