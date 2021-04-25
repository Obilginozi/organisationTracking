package com.duzceuniversity.kurumtakip.DataBase.Model.address;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;

    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<District> districts;
//
//    @OneToMany(mappedBy = "cityId")
//    private List<VergiDaireleri> vergiDairelercityist = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }

    public Country getcountry() {
        return country;
    }

    public void setcountry(Country country) {
        this.country = country;
    }

    public List<District> getcityces() {
        return districts;
    }

    public void setcityces(List<District> districts) {
        this.districts = districts;
    }
}


