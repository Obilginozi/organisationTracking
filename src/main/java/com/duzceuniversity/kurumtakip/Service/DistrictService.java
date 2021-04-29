package com.duzceuniversity.kurumtakip.Service;

import com.duzceuniversity.kurumtakip.DTO.DistrictDTO;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.DistrictRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;

    public DistrictService(DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }

    public List<DistrictDTO> getDistrictByCityMernisKod(int ilId){
       List<District> districtlist=districtRepository.findAllDistrictByIlId(ilId, "Havalimanı");
       return Arrays.asList(modelMapper.map(districtlist, DistrictDTO[].class));
   }

}
