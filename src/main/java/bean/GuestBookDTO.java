package bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class GuestBookDTO {
    private String name;
    private String email;
    private String homepage;
    private String title;
    private String content;
    private Date logtime;
}
