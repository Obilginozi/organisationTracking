package com.duzceuniversity.kurumtakip.DataBase.Repository.address;

import com.duzceuniversity.kurumtakip.DataBase.Model.address.City;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    List<City>findByCountry(Country country);
    List<City>getCityByCountryId(int ulkeid);

    @Query("select city from City city where city.city=:city")
    City findByCityAndMernis(String city);

    City findById(int id);
}
