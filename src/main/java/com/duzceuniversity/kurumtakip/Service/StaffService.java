package com.duzceuniversity.kurumtakip.Service;

import com.duzceuniversity.kurumtakip.DTO.StaffDTO;
import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;
import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Repository.StaffRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.CountryRepository;
import com.duzceuniversity.kurumtakip.Response.ResponseItem;
import com.duzceuniversity.kurumtakip.Security.SecurityUtils;
import com.duzceuniversity.kurumtakip.Util.DateConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CountryRepository countryRepository;

    public ResponseItem save(StaffDTO staffDto) {
        try {
            ResponseItem responseItem = new ResponseItem();
            User user = SecurityUtils.getCurrentUserLogin();
            Staff staff = new Staff();
            //DateConverter.StringToDatewith(staffDto.getDt())
            Date birthdate = DateConverter.StringToDatewith(staffDto.getBirthday());
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

    public List<Staff> DataResponse() {
        User user = SecurityUtils.getCurrentUserLogin();
        List<Staff> staffList = staffRepository.findAll();
        return staffList;
    }

    public void deleteById(int id) {
        staffRepository.deleteById(id);
    }

    public StaffDTO findById(int id) {
        StaffDTO staffDto = modelMapper.map(staffRepository.findById(id), StaffDTO.class);
        if (staffDto.getBirthday() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            staffDto.setBirthday(sdf.format(staffDto.getBirthday()));
        }
        return staffDto;
    }
}
