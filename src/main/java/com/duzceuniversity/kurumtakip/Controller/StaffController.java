package com.duzceuniversity.kurumtakip.Controller;

import com.duzceuniversity.kurumtakip.DTO.StaffDTO;
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
import com.duzceuniversity.kurumtakip.Response.ResponseItem;
import com.duzceuniversity.kurumtakip.Service.StaffService;
import com.google.gson.Gson;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
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


    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public String getAllPersonel() {

        List<Staff> personObjectList = staffService.DataResponse();
        String jsonData = new Gson().toJson(personObjectList);
        System.out.println(jsonData);
        return jsonData;
    }

    @RequestMapping(value = "/staffekle", method = RequestMethod.POST)
    public ResponseEntity<ResponseItem> addPerson(@RequestBody StaffDTO staffDto) {
        ResponseItem responseItem = new ResponseItem();
        try {
            if (staffDto.getTcPass() != null) {
                if (staffRepository.findByTcPass(staffDto.getTcPass()) == null && staffDto.getId() == 0) {
                    responseItem = staffService.save(staffDto);
                } else if (staffDto.getId() != 0) {
                    Staff staff = staffRepository.findById(staffDto.getId());
                    if (staff != staffRepository.findByTcPass(staffDto.getTcPass())) {
                        //responseItem = staffService.save(staffDto);
                        if (staffRepository.findByTcKimlikPasaportAndStatus(staffDto.getTcPass()) == null) {
                            responseItem = staffService.save(staffDto);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(responseItem);
        }
        return ResponseEntity.ok(responseItem);
    }

    @GetMapping("/add")
    public String PersonAdd(Model model) {
        List<City> listCity = cityRepository.findAll();
        List<Country> listCountry = countryRepository.findAll();
        List<StaffType> listStaffType = staffTurRepository.findAll();
        model.addAttribute("equalCountry", listCountry);
        model.addAttribute("listCountry", listCountry);
        model.addAttribute("Type", listStaffType);
        model.addAttribute("citys", listCity);
        return "Staff";
    }

    @GetMapping("/staffs")
    public String PersonAdd(HttpServletRequest request, Model model) {
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
        model.addAttribute("Tur", listStaffType);
        model.addAttribute("staff", staff);
        model.addAttribute("citys", cityList);
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

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<ResponseItem>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        List<ResponseItem> return_ = new ArrayList<>();
        ResponseItem responseItem = new ResponseItem();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0 && worksheet.getRow(index).getCTRow().sizeOfCArray() > 11) {
                StaffDTO staffDto = new StaffDTO();
                XSSFRow row = worksheet.getRow(index);
                staffDto.setName(row.getCell(0).getStringCellValue());
                staffDto.setSurname(row.getCell(1).getStringCellValue());
//                staffDto.setTcKimlikPasaportNo(row.getCell(2).getStringCellValue());
                if (row.getCell(2) != null) {
                    try {
                        String str = NumberToTextConverter.toText(row.getCell(2).getNumericCellValue());
//                    String s = String.valueOf(row.getCell(2).getRichStringCellValue());
                        staffDto.setTcPass(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("TC Kimlik No Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                } else {
                    responseItem.setResult(false);
                    responseItem.setMessage("TC Kimlik No Alanı Boş Bırakılamaz. Kontrol Ediniz.");
                    return_.add(responseItem);
                }
//                if (row.getCell(3) != null){
//                    try {
//                        String s = String.valueOf(row.getCell(3).getRichStringCellValue());
//                        staffDto.setPasaportNo(s);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        responseItem.setResult(false);
//                        responseItem.setMessage("Pasaport No Hatalı Girilmiş. Kontrol Ediniz.");
//                        return_.add(responseItem);
//                        continue;
//                    }
//                }
                if (row.getCell(4) != null) {
                    try {
                        if (row.getCell(4).getStringCellValue().equals("KADIN")) {
                            staffDto.setSex("0");
                        } else {
                            staffDto.setSex("1");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("Cinsiyet Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                }
                if (row.getCell(5) != null) {
                    try {
                        String country = row.getCell(5).getStringCellValue();
                        Country country1 = countryRepository.findByCountry(country);
                        staffDto.setCountry(country1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("Uyruk Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                }
                if (row.getCell(7) != null) {
                    try {
                        String staffTur = row.getCell(7).getStringCellValue();
                        StaffType staffType = staffTurRepository.findByStaffType(staffTur);
                        staffDto.setStaffType(staffType);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("Personel Tür Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                }
                if (row.getCell(8) != null) {
                    try {
                        staffDto.setEmail(row.getCell(8).getStringCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("E-mail Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                }
                if (row.getCell(9) != null) {
                    try {
                        String s = NumberToTextConverter.toText(row.getCell(9).getNumericCellValue());
                        staffDto.setPhone(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("Telefon Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                }

//                try {
//                    String district = row.getCell(10).getStringCellValue();
//                    City il1 = cityRepository.findById(city);
//                    staffDto.set"(il1);
//                } catch (Exception e) {
//                    message += (index + 1) + ". İl Alanı Hatalı Girilmiş. Kontrol Ediniz.";
//                    e.printStackTrace();
//                }
                if (row.getCell(11) != null) {
                    try {
                        String district = row.getCell(11).getStringCellValue();
                        District district1 = districtRepository.findByDistrict(district);
                        staffDto.setDistrict(district1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("İlçe Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                }
                if (row.getCell(12) != null) {
                    try {
                        staffDto.setAddress(row.getCell(12).getStringCellValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("Adres Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }

                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                if (row.getCell(13) != null) {
                    try {
                        String dateInString = formatter.format(row.getCell(13).getDateCellValue());
                        Date date = formatter.parse(dateInString);
                        staffDto.setBirthday(dateInString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("Doğum Tarihi Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                } else {
                    responseItem.setResult(false);
                    responseItem.setMessage("Doğum tarihi alanı boş bırakılamaz. Kontrol Ediniz.");
                    return_.add(responseItem);
                }
                if (row.getCell(14) != null) {
                    try {
                        staffDto.setPostalCode(NumberToTextConverter.toText(row.getCell(14).getNumericCellValue()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        responseItem.setResult(false);
                        responseItem.setMessage("Posta Kodu Alanı Hatalı Girilmiş. Kontrol Ediniz.");
                        return_.add(responseItem);
                        continue;
                    }
                }
                staffDto.setStatus((byte) 1);
                addPerson(staffDto);
            }
        }
        return ResponseEntity.ok(return_);
    }
}