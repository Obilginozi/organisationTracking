package com.duzceuniversity.kurumtakip.Service;

import com.duzceuniversity.kurumtakip.DTO.StaffDTO;
import com.duzceuniversity.kurumtakip.DTO.TableDTO.StaffObject;
import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;
import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import com.duzceuniversity.kurumtakip.DataBase.Repository.StaffRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.CityRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.DistrictRepository;
import com.duzceuniversity.kurumtakip.JUnit.JUnit;
import com.duzceuniversity.kurumtakip.Response.ResponseItem;
import com.duzceuniversity.kurumtakip.Security.SecurityUtils;
import com.duzceuniversity.kurumtakip.Util.DateConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;

    private final JUnit jUnit = new JUnit();

    public ResponseItem save(StaffDTO staffDto) {
        try {
            ResponseItem responseItem = new ResponseItem();
            Staff staff = new Staff();
            Date birthdate = DateConverter.StringToDatewith(staffDto.getBt());
            staff = modelMapper.map(staffDto, Staff.class);
            staff.setBirthday(birthdate);
            staffRepository.save(staff);

            staffDto.setId(staff.getId());
            responseItem.setMessage("İşleminiz Başarıyla Gerçekleşti!");
            responseItem.setResult(true);
            return responseItem;
        } catch (Exception e) {
            ResponseItem responseItem = new ResponseItem();
            e.printStackTrace();
            responseItem.setResult(false);
            responseItem.setMessage("Kaydetme işlemi başarısız!");
            return responseItem;
        }
    }

    public List<StaffObject> DataResponse() {
        List<Staff> staffList = staffRepository.findAllNotDeleted();
        for (Staff staff : staffList) {
            if (staff.getHesCode() == null){
                staff.setHesCode(" - ");
            }
            District district = districtRepository.findByDistrictMernisKod(staff.getDistrict().getDistrictMernisKod());
//            district.setCity(cityRepository.findById(district.getCity().getId()));
            staff.setDistrict(district);
            staff.setStaffTypeStr(staff.getStaffType().getStaffType());
        }
        List<StaffObject> staffObjects = Arrays.asList(modelMapper.map(staffList, StaffObject[].class));
        return staffObjects;
    }

    public void deleteById(int id) {
        Staff staff = staffRepository.findByIdForDelete(id);
        Date nowDate = new Date();
        staff.setDeleteAt(nowDate);
        staffRepository.save(staff);
    }

    public StaffDTO findById(int id) {
        StaffDTO staffDto = modelMapper.map(staffRepository.findById(id), StaffDTO.class);
        if (staffDto.getBirthday() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            staffDto.setBt(sdf.format(staffDto.getBirthday()));
        }
        return staffDto;
    }

    public void UniqTest(){
        List<Staff> staffList = staffRepository.findAll();
        jUnit.testFindMax(staffList);
    }
}
