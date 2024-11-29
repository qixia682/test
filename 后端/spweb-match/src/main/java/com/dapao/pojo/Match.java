package com.dapao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private int id;
    private String name;
    private String releasingUnit;
    private String year;
    private String hostUnit;
    private String contactTeacher;
    private String contactInfo;
    private String Location;
    private String category;
    private String isKeyHost;
}
