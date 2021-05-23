package com.duzceuniversity.kurumtakip.Controller;

import com.duzceuniversity.kurumtakip.DTO.StaffDTO;
import com.duzceuniversity.kurumtakip.DTO.TableDTO.StaffObject;
import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;
import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.StaffType;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.City;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import com.duzceuniversity.kurumtakip.DataBase.Repository.StaffRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.StaffTypeRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.CityRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.CountryRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.DistrictRepository;
import com.duzceuniversity.kurumtakip.LOG.Wombat;
import com.duzceuniversity.kurumtakip.Response.ResponseItem;
import com.duzceuniversity.kurumtakip.Service.StaffService;
import com.google.gson.Gson;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StaffTypeRepository staffTurRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ModelMapper modelMapper;
    final Logger logger = LoggerFactory.getLogger(Wombat.class);
    final Wombat wombat = new Wombat();


    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public String getAllPersonel() {
        wombat.setTemperature(55);
        List<StaffObject> staffObjectList = staffService.DataResponse();
        String jsonData = new Gson().toJson(staffObjectList);
        System.out.println(jsonData);
        return jsonData;
    }

    @RequestMapping(value = "/staffekle", method = RequestMethod.POST)
    public ResponseEntity<ResponseItem> addPerson(@RequestBody StaffDTO staffDto) {
        ResponseItem responseItem = new ResponseItem();
        wombat.setTemperature(52);
        try {
            if (staffDto.getTcPass() != null) {
                if (staffRepository.findByTcPass(staffDto.getTcPass()) == null && staffDto.getId() == 0) {
                    responseItem = staffService.save(staffDto);
                    logger.debug("Sisteme personel kaydedildi...");
                } else if (staffDto.getId() != 0) {
                    Staff staff = staffRepository.findById(staffDto.getId());
                    if (staff != staffRepository.findByTcPass(staffDto.getTcPass())) {
                        if (staffRepository.findByTcKimlikPasaportAndStatus(staffDto.getTcPass()) == null) {
                            responseItem = staffService.save(staffDto);
                            logger.debug("Sistemdeki bir personel güncellendi...");
                        } else {
                            responseItem.setMessage("TC Kimlik Numarası Sistemimizde Kayıtlıdır!");
                            responseItem.setResult(false);
                            return ResponseEntity.ok(responseItem);
                        }

                    } else {
                        if (staffDto.getTcPass().equals(staff.getTcPass())) {
                            responseItem = staffService.save(staffDto);
                        } else {
                            responseItem.setMessage("TC Kimlik Numarası Sistemimizde Kayıtlıdır!");
                            responseItem.setResult(false);
                            return ResponseEntity.ok(responseItem);
                        }
                    }
                } else {
                    responseItem.setMessage("TC Kimlik Numarası Sistemimizde Kayıtlıdır!");
                    responseItem.setResult(false);
                    return ResponseEntity.ok(responseItem);
                }
            } else {
                responseItem.setResult(false);
                responseItem.setMessage("Lütfen Tc/Pass No. Alanını Doldurunuz.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(responseItem);
        }
        return ResponseEntity.ok(responseItem);
    }

    @GetMapping("/add")
    public String PersonAdd(Model model) {
        wombat.setTemperature(47);
        List<City> listCity = cityRepository.findAll();
        List<Country> listCountry = countryRepository.findAll();
        List<StaffType> listStaffType = staffTurRepository.findAll();
        listCountry.get(0).setId(1);
        listCountry.get(0).setCountryCode("TR");
        listCountry.get(0).setCountry("Türkiye");
        model.addAttribute("equalCountry", listCountry);
        model.addAttribute("listCountry", listCountry);
        model.addAttribute("Type", listStaffType);
        model.addAttribute("listCity", listCity);
        return "Staff";
    }

    @GetMapping("/staffs")
    public String PersonAdd(HttpServletRequest request, Model model) {
        wombat.setTemperature(51);
        return "StaffsTable";
    }

    @GetMapping("/update")
    public String showUpdateForm(int id, Model model) {
        StaffDTO staff = staffService.findById(id);
        staff.setBirthday(staff.getBirthday());
        List<Country> listCountry = countryRepository.findAll();
        List<StaffType> listStaffType = staffTurRepository.findAll();
        List<City> cityList = cityRepository.findAll();
        List<District> districtLists = districtRepository.findByCity(staff.getDistrict().getCity());
        model.addAttribute("equalCountry", staff.getCountry().getId());
        model.addAttribute("listCountry", listCountry);
        model.addAttribute("districtLists", districtLists);
        model.addAttribute("Type", listStaffType);
        model.addAttribute("staff", staff);
        model.addAttribute("listCity", cityList);
        return "Staff";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseItem deleteUser(int id) {
        ResponseItem responseItem = new ResponseItem();
        responseItem.setMessage("Bir hata meydana geldi!");
        responseItem.setResult(false);
        try {
            staffService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return responseItem;
        }
        responseItem.setMessage("Personel silindi!");
        responseItem.setResult(true);
        return responseItem;
    }

    @RequestMapping(value = "/importStaffs", method = RequestMethod.GET)
    public String getImportStaffs(Model model) {
        return "ImportStaffs";
    }

}