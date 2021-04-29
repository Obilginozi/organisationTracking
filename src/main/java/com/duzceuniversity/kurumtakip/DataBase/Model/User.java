package com.duzceuniversity.kurumtakip.DataBase.Model;

import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String name;
    private String surname;
    @Email(message = "Hatalı email")
    private String email;
    private String phone;
    private String roles = "";
    private String permissions = "";
    @Column
    private String iban ;
    @OneToOne
    protected Country country;
    @OneToOne
    protected District district;
    private String address;
    private int active= 1;
    private Date deleteAt;

    public User(String username, String password, String name, String surname, String email, String roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        Country country = new Country();
        country.setId(1);
        this.country = country;
        District district = new District();
        district.setId(1);
        this.district = district;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}

