package com.duzceuniversity.kurumtakip.Controller;
import com.duzceuniversity.kurumtakip.DTO.DistrictDTO;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.City;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.CityRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.CountryRepository;
import com.duzceuniversity.kurumtakip.Service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.List;

@Controller
public class AdressController {

    @Autowired
    DistrictService districtService;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;

    @RequestMapping(value = "/getCitysByContryId/{countryId}", method = RequestMethod.GET)
    @ResponseBody
    private List<City> getilByContryId(@PathVariable int countryId) {
        List<City> list = cityRepository.getCityByCountryId(countryId);
        return list;
    }

    @RequestMapping(value = "/getdistrictByCityId/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    private List<DistrictDTO> getDistrictByCityId(@PathVariable int cityId) {
        List<DistrictDTO> list = districtService.getDistrictByCityMernisKod(cityId);
        return list;
    }

    @GetMapping("/deneme")
    public String brands(Model model , HttpServletRequest request) throws UnknownHostException {

        List<Country>countrys=countryRepository.findAll();
        model.addAttribute("list",countrys);
        System.out.println(request.getRemoteAddr()+"remote");
        System.out.println(request.getHeader("x-forwarded-for"));
        System.out.println(request.getLocalAddr());
        String ip = request.getHeader("X-Forwarded-For");

        if(ip==null)
        {
            ip = request.getRemoteAddr();
        }
        System.out.println(ip);
        return "form-inputs";
    }

}
