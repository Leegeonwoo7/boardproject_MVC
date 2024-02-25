package bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {
    private String name;
    private String id;
    private String password;
    private String gender;
    private String email;
    private String email_addr;
    private String phone;
    private String address_code;
    private String address_address;
    private String address_address_detail;
}
