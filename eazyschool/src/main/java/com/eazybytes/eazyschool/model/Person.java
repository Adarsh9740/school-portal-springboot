package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import com.eazybytes.eazyschool.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                message = "Email are not matching",
                field = "email",
                matchingField = "confirmEmail"
        ),
        @FieldsValueMatch(
                message = "Passwords are not matching",
                field = "pwd",
                matchingField = "confirmPwd"
        )
})
public class Person extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;
    @NotBlank(message = "The name should not be blank")
    private String name;

    @NotBlank(message = "The Mobile No should not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email should not be blank")
    @Email(message="Email is invalid")
    private String email;

    @NotBlank(message = "Confirm Email should be blank")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password should not be blank")
    @PasswordValidator
    private String pwd;
    @NotBlank(message = "Confirm Password should not be blank")
    @Transient
    private  String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;
}
