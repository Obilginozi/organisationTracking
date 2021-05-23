package com.duzceuniversity.kurumtakip.Service;

import com.duzceuniversity.kurumtakip.DataBase.Model.IpInfo;
import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Repository.IpInfoRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.StaffRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
}
