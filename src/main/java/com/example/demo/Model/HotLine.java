package com.example.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class HotLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotlineId;

    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "description is required")
    private String description;
    @NotEmpty(message = "location is required")
    private String location;
    @NotEmpty(message = "hotLineName is required")
    private String hotLineName;
    @NotEmpty(message = "hotLineType is required")
    private String hotLineType;

    // ------------------------ Relations ----

    @OneToMany
    @JsonIgnore
    private Set<Requests> requestSet;



    // نظام مسول عن الحالات ال urgent
    // solution to urgent cases
    // توفير طياره اخلاء
    // وسيط



//    private boolean facility;
//    private String tools;
//    private String accept;
//    private boolean emergencyRequest;
//    private boolean urgentRequest;
//    private int hoursRequired;
    // sender hospital
    // recevier hospital ;
    // less than one hour ;
    // relations with urgent






}
