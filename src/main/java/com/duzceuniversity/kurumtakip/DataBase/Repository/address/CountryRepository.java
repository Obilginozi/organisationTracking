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

}
