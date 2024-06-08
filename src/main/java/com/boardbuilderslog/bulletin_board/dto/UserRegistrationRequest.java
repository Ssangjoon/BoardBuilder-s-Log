package com.boardbuilderslog.bulletin_board.dto;

import com.boardbuilderslog.bulletin_board.entity.Address;
import com.boardbuilderslog.bulletin_board.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String fullName;
    @NotBlank
    private String address;
    @NotBlank
    private String roadAddress;
    @NotBlank
    private String zip;
    @NotBlank
    private String detailAddress;
    @NotNull
    private Boolean marketingConsent;
    @NotNull
    private Boolean termsAccepted;
    @NotNull
    private Boolean privacyPolicyAccepted;

    public User toEntity(){
        Address addressEntity = Address.builder()
                .address(address)
                .roadAddress(roadAddress)
                .zip(zip)
                .detailAddress(detailAddress)
                .build();

        return User.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .marketingConsent(marketingConsent)
                .termsAccepted(termsAccepted)
                .privacyPolicyAccepted(privacyPolicyAccepted)
                .address(addressEntity)
                .build();
    }
}
