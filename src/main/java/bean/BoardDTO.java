package bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BoardDTO {
    private int seq;
    private String id;
    private String name;
    private String email;
    private String title;
    private String content;

    private int ref;
    private int lev;
    private int step;
    private int pseq;
    private int reply;

    private int hit;
    private Date logtime;
}
