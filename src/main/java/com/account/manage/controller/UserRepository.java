package com.account.manage.controller;  
  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.PreparedStatementCreator;  
import org.springframework.jdbc.core.RowMapper;  
import org.springframework.jdbc.support.GeneratedKeyHolder;  
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.account.manage.bean.User;

import ch.qos.logback.core.net.SyslogOutputStream;  
  

  
@Component
public class UserRepository {  
    @Autowired  
    private JdbcTemplate jdbcTemplate;  
  
    @Transactional(readOnly = true)  
    public List<User> findAll() {  
        return jdbcTemplate.query("select * from user", new UserRowMapper());  
    } 
    @Transactional(readOnly = true)  
    public User findUserById(String id) {
        return jdbcTemplate.queryForObject("select * from user where id=?", new Object[]{id}, new UserRowMapper());
    }
  
    @Transactional(readOnly = true)  
    public List<User> findUserByExample(String id,String name,Integer type,String timeScope) throws ParseException {  
            String sql = "select * from user where 1 = 1 ";  
            int i=0;
            if (!id.equals("")) {  
                sql += " and id like '" +"%"+id+"%"+"'";  
            }  
            if (!name.equals("")) {  
                sql += " and name like "+"'%"+ name + "%'";  
            }  
            if (!type.equals(0)) {  
                sql += " and type = "+type;  
            }  
            String[] s = timeScope.split(" - ");
           // sql += " and  createTime > ? and createTime < ? ";
            SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy"); 
            SimpleDateFormat formatter2 = new SimpleDateFormat ("yyyy/MM/dd"); 
            
            Date startTime = formatter.parse(s[0]);
            Date endTime = formatter.parse(s[1]);
            String st= "'"+formatter2.format(startTime)+"'";
            String et="'"+ formatter2.format(endTime)+"'";
            sql+=" and  createTime > "+st+" and createTime < "+et;
            System.out.println(sql);
            
        return jdbcTemplate.query(sql, new UserRowMapper());  
    }  
  
    public User create(final User user) {  
        final String sql = "insert into user(id,name,type,lastLoginTime,state,note) values(?,?,?,?,?,?)";  
  
     //   KeyHolder holder = new GeneratedKeyHolder();  
  
        jdbcTemplate.update(new PreparedStatementCreator() {  
  
            @Override  
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
                ps.setString(1, user.getId());  
                ps.setString(2, user.getName());  
                ps.setInt(3, user.getType());  
                ps.setDate(4, new java.sql.Date(System.currentTimeMillis())); 
                ps.setInt(5, user.getState());  
                ps.setString(6, user.getNote());  
                
                
                return ps;  
            }  
        });  
  
        /*int newUserId = holder.getKey().intValue();  
        user.setId(newUserId);  */
        return user;  
    }  
    
    public void delete(final String id) {  
        final String sql = "delete from user where id="+id;  
        jdbcTemplate.update(sql);  
    }  
  
    public void update(final User user) {  
        jdbcTemplate.update("update user set name=?,state=?,type=?,note=? where id=?",  
                new Object[] { user.getName(),user.getState() ,user.getType(),user.getNote(), user.getId() });  
    }
    public void delete(String[] ids) {
        final String sql = "delete from user where id=";  
        for(String id:ids){
            String sql2=sql+id;
            jdbcTemplate.update(sql2);  
        }
        
    }
    public void on(String[] ids) {
        for(String id:ids){
                 jdbcTemplate.update("update user set state=1 where id="+id);  
            }
        }
        // TODO Auto-generated method stub
    public void off(String[] ids) {
            for(String id:ids){
                jdbcTemplate.update("update user set state=0 where id="+id);  
           }
        }
    }  
  
class UserRowMapper implements RowMapper<User> {  
  
    @Override  
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {  
        User user = new User();  
        user.setId(rs.getString("id"));  
        user.setName(rs.getString("name"));  
        user.setType(rs.getInt("type"));  
        user.setState(rs.getInt("state"));  
        user.setCreateTime(rs.getDate("createTime"));  
        user.setLastLoginTime(rs.getDate("lastLoginTime"));  
        user.setNote(rs.getString("note"));  
  
        return user;  
    }  
}  