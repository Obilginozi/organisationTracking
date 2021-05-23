package com.duzceuniversity.kurumtakip.DataBase.Repository;

import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer> {

    @Query("SELECT u FROM Staff u WHERE u.status = 1 and u.id=:id")
    Staff findById(@Param("id") int StaffId);

    @Query("SELECT u FROM Staff u WHERE u.id=:id")
    Staff findByIdForDelete(@Param("id") int StaffId);

    @Query("SELECT u FROM Staff u WHERE u.status = 1 and u.tcPass=:tcPass")
    Staff findByTcKimlikPasaportAndStatus(@Param("tcPass") String tcPass);

    Staff findByTcPass(String tc);

    @Query("SELECT u FROM Staff u WHERE u.deleteAt is NULL")
    List<Staff> findAllNotDeleted();

    List<Staff> findAll();

    @Query("select o from Staff o where o.id in :ids")
    List<Staff> findBypIds(@Param("ids") List<Integer> id);

}
