package com.duzceuniversity.kurumtakip.DataBase.Repository;

import com.duzceuniversity.kurumtakip.DataBase.Model.IpInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpInfoRepository extends PagingAndSortingRepository<IpInfo, Integer> {
}
