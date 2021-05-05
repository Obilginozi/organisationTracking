package com.duzceuniversity.kurumtakip.DataBase.Model.Staff;

import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Email
    private String email;
    @Size(min = 10, max = 11)
    private String phone;
    private Date birthday;
    private String tcPass;
    private String hesCode;
    @OneToOne
    private StaffType staffType;
    private String staffTypeStr;
    @OneToOne
    private Country country;
    @OneToOne
    private District district;
    @Size(max = 100)
    private String address;
    private String postalCode;
    private String sex;
    //    @OneToOne
//    private User user;
    private byte status;
    private Date deleteAt;

}
