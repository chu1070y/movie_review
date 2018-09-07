package org.movie.dao;

import org.movie.domain.ReviewVO;

import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public void updateChoice(int rno, String value) {
        String sql = "update tbl_review set ";
        if (value.equals("g")) {
            sql += "good = nvl(good, 0) + 1";
        } else {
            sql += "bad = nvl(bad, 0)+ 1";
        }
        sql += " where rno =? ";

        String finalSql = sql;
        System.out.println(finalSql);

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(finalSql);
                stmt.setInt(1, rno);
                stmt.executeUpdate();
            }
        }.executeAll();
    }


    public void addReview(ReviewVO vo){
        String sql = "insert into tbl_review (mnum, score, cmt, rno)\n" +
                "values (?, ?, ?, seq_review.nextval)";
        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, vo.getMnum());
                stmt.setInt(2, vo.getScore());
                stmt.setString(3, vo.getCmt());
                stmt.executeUpdate();                               //리뷰 추가하기
            }
        }.executeAll();

    }

    public List<ReviewVO> getAllReviews(int mnum){
        List<ReviewVO> list = new ArrayList<>();
        String sql = "select\n" +
                " mnum, score, cmt, reviewdate, rno,\n" +
                " nvl(good, 0) good, nvl(bad, 0) bad\n" +
                "from tbl_review where mnum = ?\n" +
                "order by rno desc";

        new QueryExecutor(){
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, mnum);           // MovieDAO와 구분해서 고민. 아래도 인덱스값을 써줌.
                rs = stmt.executeQuery();
                while(rs.next()){
                    ReviewVO vo = new ReviewVO();
                    vo.setMnum(rs.getInt(1));   //첫번째 칼럼임을 의미
                    vo.setScore(rs.getInt(2));
                    vo.setCmt(rs.getString(3));
                    vo.setReviewdate(rs.getDate(4));
                    vo.setRno(rs.getInt(5));

                    vo.setGood(rs.getInt(6));
                    vo.setBad(rs.getInt(7));
                    list.add(vo);
                }
            }
        }.executeAll();

        return list;
    }
}