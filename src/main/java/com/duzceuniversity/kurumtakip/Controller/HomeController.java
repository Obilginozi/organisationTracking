package com.duzceuniversity.kurumtakip.Controller;

import com.duzceuniversity.kurumtakip.DataBase.Model.IpInfo;
import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;
import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Repository.StaffRepository;
import com.duzceuniversity.kurumtakip.JUnit.JUnit;
import com.duzceuniversity.kurumtakip.Response.ResponseItem;
import com.duzceuniversity.kurumtakip.Service.DashBoardService;
import com.duzceuniversity.kurumtakip.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController{
    @Autowired
    DashBoardService dashBoardService;

    private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        System.out.println(remoteAddr);
        return remoteAddr;
    }

    @GetMapping({"/", "/login"})
    public String login(HttpServletRequest request) {
        IpInfo ipInfo = new IpInfo();
        ipInfo.setIpAdress(getClientIp(request));
        dashBoardService.ipInfo(ipInfo);
        return "Login";
    }

    @GetMapping({"/home"})
    public String homePage() {
        return "Index";
    }

    @ResponseBody
    @PostMapping(value = "/kontrol")
    public ResponseEntity<ResponseItem> control(@RequestParam String username) {
        ResponseItem responseItem = new ResponseItem();
        User user = dashBoardService.control(username);
        if (user == null) {
            responseItem.setResult(false);
            responseItem.setMessage("Kullan??c?? ad??n??z sistemimizde kay??tl?? de??ildir");
            return ResponseEntity.ok(responseItem);
        } else {
            responseItem.setResult(true);
            responseItem.setMessage("????lem Ba??ar??l??");
            return ResponseEntity.ok(responseItem);
        }
    }
}
