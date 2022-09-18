package com.farmawebservice.delivery.settings.service;

import com.farmawebservice.delivery.base.service.BaseService;
import com.farmawebservice.delivery.base.model.MessageClientResponseDTO;
import com.farmawebservice.delivery.settings.model.SettingsLabelDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.farmawebservice.delivery.base.connector.Foo.RESOURCE_OBTAIN_NAME_USER;

@Service
public class SettingsService extends BaseService {


    @Value("${settings.labelOrder}")
    private String labelOrder;
    @Value("${settings.labelInfoUser}")
    private String labelInfoUser;
    @Value("${settings.labelAsks}")
    private String labelAsks;
    @Value("${settings.labelMethodPayment}")
    private String labelMethodPayment;
    @Value("${settings.labelAddress}")
    private String labelAddress;
    @Value("${settings.labelCloseAccount}")
    private String labelCloseAccount;
    @Value("${settings.labelButtonLoggout}")
    private String labelButtonLoggout;
    @Value(value = "${settings.labelVersionApp}")
    private String labelVersionApp;

    public SettingsLabelDTO getSettingsDataByUser(String accessToken) {
        String response = getAuthClient().sendMethodPost(RESOURCE_OBTAIN_NAME_USER, null, accessToken);
        SettingsLabelDTO settingsLabelDTO = null;
        if (response != null) {
            MessageClientResponseDTO responseDTO = convertClientResponseToDTO(response);

            settingsLabelDTO = new SettingsLabelDTO(
                    labelOrder,
                    labelInfoUser,
                    labelAsks,
                    labelMethodPayment,
                    labelAddress,
                    labelCloseAccount,
                    labelButtonLoggout,
                    labelVersionApp,
                    responseDTO.getResponseDTO());
        }
        return settingsLabelDTO;
    }
}
