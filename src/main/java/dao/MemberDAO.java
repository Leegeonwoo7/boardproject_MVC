package dao;

import bean.MemberDTO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//import java.lang.reflect.Member;

public class MemberDAO {
    private SqlSessionFactory sqlSessionFactory;

    private static MemberDAO memberDAO = new MemberDAO();
    public static MemberDAO getInstance(){ return memberDAO; }

    public MemberDAO(){
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

//catch(PersistenceException e){
    public boolean isValidateId(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberDTO memberDTO = sqlSession.selectOne("memberSQL.isValidateId", id);
        sqlSession.close();
        //null이면 사용가능
        if(memberDTO != null){
            return true;
        }else{
            return false;
        }
    }

    public boolean makeAccount(MemberDTO memberDTO){
        try {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("memberSQL.makeAccount", memberDTO);
            sqlSession.commit();
            sqlSession.close();
            return true;
        } catch (PersistenceException e){
            e.printStackTrace();
            return false;
        }
    }

    public MemberDTO login(String id, String password){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);


        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login", map);
        sqlSession.close();
        return memberDTO;
    }

    public MemberDTO getMemberById(String id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberDTO memberDTO = sqlSession.selectOne("memberSQL.getMemberById", id);
        sqlSession.close();
        return memberDTO;
    }

    public boolean updateMember(MemberDTO memberDTO){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("memberSQL.updateMember", memberDTO);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
