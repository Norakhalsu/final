package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @NotEmpty(message = "Type Request is Required")
    @Pattern(regexp = "^(Emergency|Urgent)$", message = "Type Request must be Emergency or Urgent")
    private String typeRequest;

    private String statusRequest="pending";

    @NotEmpty(message = "status Patient is Required ")
    private String patientCase; //الحالة الشقيقه statusmignorosus

    @NotNull(message = "Hours Cases must be not null")
    private int hoursCase;

    @Pattern(regexp = "^(core|pinmpra|unKnown|known)$", message = "Onset must be either core , pinmpra ")
    private String onset; //core =deaid area ,, pinmra = area can be saved --  using bitskan

    @NotEmpty(message = "description of case is required")
    private String description;


    // --------------------------------------------------

  //  @Id
 //  @GeneratedValue(strategy = GenerationType.IDENTITY)
 //   private Integer urgentId;
  //   @NotEmpty(message = "status Patient is Required")
 //   private String statusPatient; // stroke السكتة الدماغية
//    @NotNull(message = "Hours Cases must be not null , must between 2-6 hours ")
//    @Min(2)
//    @Max(6)
//    private int hoursCase;




    // ------------- Relations ----------


//    @OneToOne(cascade = CascadeType.ALL)
//    private Emergency emergency;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Urgent urgent;

    @ManyToOne
    private HotLine hotLine;

    @ManyToOne
    private Hospital hospital; // الطلبات تنرفع من المستشفيات وتوصل للهوت لاين




}
