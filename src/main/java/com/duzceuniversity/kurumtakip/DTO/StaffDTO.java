package com.duzceuniversity.kurumtakip.DTO;

import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.StaffType;
import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String bt;
    private Date birthday;
    private String tcPass;
    private String hesCode;
    private StaffType staffType;
    protected Country country;
    protected District district;
    private String address;
    private String postalCode;
    private String sex;
    private User user;
    private byte status;
    private Date deleteAt;

}
