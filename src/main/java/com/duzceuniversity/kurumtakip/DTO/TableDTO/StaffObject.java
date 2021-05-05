package com.duzceuniversity.kurumtakip.DTO.TableDTO;

import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.StaffType;
import lombok.Data;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class StaffObject {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String tcPass;
    private String hesCode;
    private String staffTypeStr;
    private String status;
}
