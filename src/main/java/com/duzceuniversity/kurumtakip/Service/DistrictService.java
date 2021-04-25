package com.duzceuniversity.kurumtakip.Service;

import com.duzceuniversity.kurumtakip.DTO.DistrictDTO;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.DistrictRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    ModelMapper modelMapper;

   public List<DistrictDTO> getDistrictByCityMernisKod(int ilId){
       List<District> districtlist=districtRepository.findAllDistrictByIlId(ilId, "Havalimanı");
       List<DistrictDTO>districtDtoList= Arrays.asList(modelMapper.map(districtlist, DistrictDTO[].class));
       return districtDtoList;
   }

}
