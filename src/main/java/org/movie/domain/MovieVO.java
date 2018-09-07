package org.movie.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MovieVO {
    private int mnum, runtime;
    private String mtitle, director, actors, grade,poster;
    private Date opendate, regdate;
}
