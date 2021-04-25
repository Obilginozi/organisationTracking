package com.duzceuniversity.kurumtakip.DataBase.Repository.address;

import com.duzceuniversity.kurumtakip.DataBase.Model.address.City;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    List<District>findByCity(City city);

    List<District>getDistrictByCityId(int cityId);

    District findByDistrictMernisKod(int districtMernisKod);

    @Query("select district from District district where district.city.id = :cityId and district.district not like %:havalimani% ")
    List<District> findAllDistrictByIlId(@Param("cityId") int cityId, @Param("havalimani") String havalimani);

    District findById(int id);

    District findByDistrict(String district);
}
