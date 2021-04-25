package com.duzceuniversity.kurumtakip.DataBase.Model.Staff;

import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
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
    private String postalCode;
    private Date birthday;
    private String sex;
    private byte status;
    private String hesKodu;
    @OneToOne
    protected Country uyrukCountry;

    private String tcPass;
    @Email
    private String email;

    @Size(min = 10,max = 11)
    private String phone;

    @Size(max = 100)
    private String adres;

}
