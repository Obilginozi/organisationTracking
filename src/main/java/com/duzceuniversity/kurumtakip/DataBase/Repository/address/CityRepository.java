package com.duzceuniversity.kurumtakip.DataBase.Repository.address;

import com.duzceuniversity.kurumtakip.DataBase.Model.address.City;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
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

    @Query(value = "select city_id from District district where district.district_mernis_kod=:mernis", nativeQuery = true)
    int findByCityMerniskod(int mernis);

    @Query(value = "select * from City city where city.city_id=:id", nativeQuery = true)
    City findById(int id);
}
