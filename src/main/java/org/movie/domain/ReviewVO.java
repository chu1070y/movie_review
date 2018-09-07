package org.movie.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewVO {
    private int rno, good,bad, mnum,score;
    private  String cmt;
    private Date reviewdate;
}
