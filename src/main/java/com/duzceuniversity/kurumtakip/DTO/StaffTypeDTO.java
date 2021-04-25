package com.duzceuniversity.kurumtakip.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffTypeDTO implements Serializable {
    private int id;
    private  String personelTur;
}
