package com.farmawebservice.delivery.settings.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SettingsLabelDTO {
    private String labelOrder;
    private String labelInfoUser;
    private String labelAsks;
    private String labelMethodPayment;
    private String labelAddress;
    private String labelCloseAccount;
    private String labelButtonLoggout;
    private String labelVersionApp;
    private String nameUser;
    private String labelBiometric;

    public SettingsLabelDTO(String labelOrder, String labelInfoUser, String labelAsks, String labelMethodPayment, String labelAddress, String labelCloseAccount, String labelButtonLoggout, String labelVersionApp, String nameUser,
                            String labelBiometric) {
        this.labelOrder = labelOrder;
        this.labelInfoUser = labelInfoUser;
        this.labelAsks = labelAsks;
        this.labelMethodPayment = labelMethodPayment;
        this.labelAddress = labelAddress;
        this.labelCloseAccount = labelCloseAccount;
        this.labelButtonLoggout = labelButtonLoggout;
        this.labelVersionApp = labelVersionApp;
        this.nameUser = nameUser;
        this.labelBiometric = labelBiometric;
    }
}
