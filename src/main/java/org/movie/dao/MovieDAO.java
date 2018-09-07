package org.movie.dao;

import org.movie.domain.MovieVO;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public List<MovieVO> getList(){

        final List<MovieVO> list = new ArrayList<MovieVO>();

        final String sql="select * from tbl_movie order by mnum desc";

        new QueryExecutor(){
            public void doJob() throws Exception{
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while(rs.next()){
                    MovieVO vo = new MovieVO();
                    vo.setMnum(rs.getInt("mnum"));
                    vo.setMtitle(rs.getString("mtitle"));
                    vo.setActors(rs.getString("actors"));
                    vo.setDirector(rs.getString("director"));
                    vo.setGrade(rs.getString("grade"));
                    vo.setPoster(rs.getString("poster"));
                    list.add(vo);
                }
            }
        }.executeAll();

        return list;
    }

    public MovieVO getMovie(final int mnum){
        final MovieVO vo = new MovieVO();
        final String sql = "select * from tbl_movie where mnum = ?";

        new QueryExecutor(){

            public void doJob() throws Exception{
                stmt = con.prepareStatement(sql);
                stmt.setInt(1,mnum);
                rs = stmt.executeQuery();

                while(rs.next()){
                    MovieVO vo = new MovieVO();
                    vo.setMnum(rs.getInt("mnum"));
                    vo.setMtitle(rs.getString("mtitle"));
                    vo.setActors(rs.getString("actors"));
                    vo.setDirector(rs.getString("director"));
                    vo.setGrade(rs.getString("grade"));
                    vo.setPoster(rs.getString("poster"));
                }
            }
        }.executeAll();

        return vo;
    }

    public List<String> getImgs(int mnum){
        List<String> list = new ArrayList<>();
        //code
        String sql = "select fname from tbl_movie_img where mnum = ? order by fname";

        new QueryExecutor(){

            public void doJob() throws Exception{
                stmt = con.prepareStatement(sql);
                stmt.setInt(1,mnum);// 먼저 선행되어야
                rs = stmt.executeQuery(); //이게 실행된다.

                while(rs.next()){
                    list.add(rs.getString("fname"));
                }
            }
        }.executeAll();

        return list;
    }

}
