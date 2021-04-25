package com.duzceuniversity.kurumtakip.DataBase.Repository.address;

import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
    Country findById(int id);
    Country findByCountry(String Country);
    Country findByCountryKod(String CountryKod);

    @Query("select Country from Country Country where Country.CountryKod in ('BY', 'BG', 'RO')") //=Country.CountryKod='BY' or Country.CountryKod='BG' or Country.CountryKod='RO'
    List<Country> findVizeList();


}
