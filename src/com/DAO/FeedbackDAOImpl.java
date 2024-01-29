package com.DAO;

import com.entity.FeedbackDtls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAOImpl  implements FeedbackDAO {
    private Connection conn;

    public FeedbackDAOImpl(Connection conn) {
        super();
        this.conn=conn;
    }



    public boolean addFeedback(FeedbackDtls fdb){
        boolean f=false;
        try{
            String sql="insert into feedback (fdbkname,fdbkemail,fdbkmessage) values(?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,fdb.getFeedname());
            ps.setString(2,fdb.getFeedemail());
            ps.setString(3, fdb.getFeedmessage());

            int i=ps.executeUpdate();

            if(i>0){
                f=true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return f;
    }

    @Override
    public List<FeedbackDtls> getFeedbacks() {

        List<FeedbackDtls> list= new ArrayList<FeedbackDtls>();
        FeedbackDtls fdb=null;

        try{
            String sql="select * from feedback";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            int i;
            while(rs.next()){
                fdb=new FeedbackDtls();
                fdb.setFid(rs.getInt(1));
                fdb.setFeedname(rs.getString(2));
                fdb.setFeedemail(rs.getString(3));
                fdb.setFeedmessage(rs.getString(4));
                list.add(fdb);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


}