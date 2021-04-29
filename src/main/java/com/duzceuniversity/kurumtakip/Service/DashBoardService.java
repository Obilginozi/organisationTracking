package com.duzceuniversity.kurumtakip.Service;

import com.duzceuniversity.kurumtakip.DataBase.Model.IpInfo;
import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;
import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Repository.IpInfoRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.StaffRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.UserRepository;
import com.duzceuniversity.kurumtakip.Security.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DashBoardService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelmapper;
    @Autowired
    private IpInfoRepository ipInfoRepository;
    @Autowired
    private UserRepository userRepository;

    public void ipInfo(IpInfo ipInfo) {
        ipInfoRepository.save(ipInfo);
    }

    public User control(String username) {
        return userRepository.findByUsernameAndDeleteAtIsNull(username);
    }
//    public List<GunlukPersonelDto> gunlukPersonell(Integer firmaId) {
//        try {
//            Date dn = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(dn);
//            dn.setMonth(cal.get(Calendar.MONTH));
//            int year = cal.get(Calendar.YEAR);
//
//            List<YukSeferlerHareket> yukSeferlerHareketList = yukSeferRepository.findAllFirma(firmaId);
//            List list = new ArrayList();
//            int uzunluk = yukSeferlerHareketList.size();
//            int i = 0;
//            for (i = 0; i < uzunluk; i++) {
//                if (yukSeferlerHareketList.get(i).getBaslangicTarihi().getDay() == dn.getDay() && yukSeferlerHareketList.get(i).getBaslangicTarihi().getMonth() == dn.getMonth()
//                        && yukSeferlerHareketList.get(i).getBaslangicTarihi().getYear() == dn.getYear()) {
//                    list.add(yukSeferlerHareketList.get(i).getPersonel().getId());
//                    if (yukSeferlerHareketList.get(i).getPersonel2() != null) {
//                        list.add(yukSeferlerHareketList.get(i).getPersonel2().getId());
//                    }
//                }
//            }
//            return list;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List seferdekiPersonel(Integer firmaId) {
        try {
            User user = SecurityUtils.getCurrentUserLogin();
            Date dn = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dn);
            dn.setMonth(cal.get(Calendar.MONTH));
            List<Staff> staffList = staffRepository.findAll();
            List<String> staff = new ArrayList<>();
            for (int i = 0; i < staffList.size(); i++) {
                staff.add(staffList.get(i).getName() + " " + staffList.get(i).getSurname());
            }
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
