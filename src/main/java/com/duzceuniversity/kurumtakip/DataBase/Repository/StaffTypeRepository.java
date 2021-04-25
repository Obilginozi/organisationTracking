package com.duzceuniversity.kurumtakip.DataBase.Repository;

import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.StaffType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffTypeRepository extends PagingAndSortingRepository<StaffType, Integer> {
    List<StaffType> findAll();
    
    StaffType findByStaffType(String staffType);
}
