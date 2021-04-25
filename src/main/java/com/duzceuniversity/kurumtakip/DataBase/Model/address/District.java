package com.duzceuniversity.kurumtakip.DataBase.Model.address;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class District implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private int id;

    private String district;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    private int districtMernisKod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getdistrictMernisKod() {
        return districtMernisKod;
    }

    public void setdistrictMernisKod(int districtMernisKod) {
        this.districtMernisKod = districtMernisKod;
    }
}
