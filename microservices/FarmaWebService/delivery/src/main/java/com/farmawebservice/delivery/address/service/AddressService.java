package com.farmawebservice.delivery.address.service;

import com.farmawebservice.delivery.address.model.Address;
import com.farmawebservice.delivery.address.model.dto.AddressDTO;
import com.farmawebservice.delivery.address.model.dto.DAddressDTO;
import com.farmawebservice.delivery.address.repository.AddressRepository;
import com.farmawebservice.delivery.base.model.MessageClientResponseDTO;
import com.farmawebservice.delivery.base.service.BaseService;
import com.farmawebservice.delivery.user.model.User;
import com.farmawebservice.delivery.user.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AddressService extends BaseService {


    private final UserRepository userRepository;
    private final AddressRepository addressRepository;


    @Autowired
    public AddressService(UserRepository userRepositoryx, AddressRepository addressRepositoryx) {
        this.addressRepository = addressRepositoryx;
        this.userRepository = userRepositoryx;
    }


    public Boolean saveAddressByUser(AddressDTO dto) {
        Address address = new Address();

        address.setCep(dto.getCep());
        address.setLatitude(dto.getLatitude());
        address.setLongitude(dto.getLongitude());
        address.setState(dto.getState());
        address.setCity(dto.getCity());
        address.setNumber(dto.getNumber());
        address.setStreet(dto.getStreet());

        User u = this.userRepository.findByUserAuthId(getClientId(dto.getClient_id_token()));

        if (u != null) {
            Address addressSave = addressRepository.save(address);
            try {
                addressRepository.saveAddressByUserId(u.getId(), addressSave.getId());
                return addressSave.getId() != -1;
            } catch (Exception e) {
                return addressSave.getId() != -1;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteAddressByUser(DAddressDTO dto) {
        try {
            User u = this.userRepository.findByUserAuthId(getClientId(dto.getClient_id_token()));
            Address address = this.addressRepository.findById(dto.getAddress_id()).get();
            this.addressRepository.deleteAddress(u.getId(), address.getId());
            this.addressRepository.delete(address);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteAddressByUser(String token, Long id) {
        try {
            User u = this.userRepository.findByUserAuthId(getClientId(token));
            Address address = this.addressRepository.findById(id).get();
            this.addressRepository.deleteAddress(u.getId(), address.getId());
            this.addressRepository.delete(address);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Address getAddressUserById(DAddressDTO dto) {
        try {
            User u = this.userRepository.findByUserAuthId(getClientId(dto.getClient_id_token()));
            Address address = this.addressRepository.findById(dto.getAddress_id()).get();
            if (u != null) {
                return address;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    public List<Address> getAllAddressUserById(DAddressDTO dto) {
        try {
            User u = this.userRepository.findByUserAuthId(getClientId(dto.getClient_id_token()));
            if (u != null) {
                return u.getUserAddresses();
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    public List<Address> getAllAddressUserById(String token) {
        try {
            User u = this.userRepository.findByUserAuthId(getClientId(token));
            if (u != null) {
                return u.getUserAddresses();
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    public MessageClientResponseDTO selectAddressToPrincipal(Long addressId, Boolean value, String client_id_token) {
        Address address = this.addressRepository.findById(addressId).get();
        User u = this.userRepository.findByUserAuthId(getClientId(client_id_token));
        List<Address> addressListPrincipal = this.addressRepository.getAllAddressPrincipal();
        List<Address> addressList = u.getUserAddresses();
        if (addressList.size() != 0) {
            if (getOccurrencyAddressPrincipal(addressList) == 0) {
                return updateAddressSeletected(address, value);
            } else if (getOccurrencyAddressPrincipal(addressList) == 1 && Objects.equals(addressListPrincipal.get(0).getId(), address.getId())) {
                return updateAddressSeletected(address, value);
            } else {
                return showErrorHasContainsAddressAsPrincipal();
            }

        } else {
            return updateAddressSeletected(address, value);
        }
    }

    private MessageClientResponseDTO showErrorHasContainsAddressAsPrincipal() {
        MessageClientResponseDTO responseDTO = null;
        responseDTO = new MessageClientResponseDTO();
        responseDTO.setTitle("Has other address as Principal");
        responseDTO.setHttpStatusCode(400);
        return responseDTO;
    }

    private MessageClientResponseDTO updateAddressSeletected(
            Address address,
            Boolean value
    ) {
        MessageClientResponseDTO responseDTO = null;
        this.addressRepository.updateAddressToPrincipal(value, address.getId());
        address.setPrincipal(value);
        this.addressRepository.updateAddressToPrincipal(value, address.getId());
        responseDTO = new MessageClientResponseDTO();
        responseDTO.setHttpStatusCode(200);
        responseDTO.setTitle("Address Principal Updated");
        responseDTO.setResponseDTO(new Gson().toJson(address));
        return responseDTO;
    }

    private Integer getOccurrencyAddressPrincipal(List<Address> addressList) {
        List<Address> addressList1 = new ArrayList<>();
        for (Address address1 : addressList) {
            if (address1.isPrincipal()) {
                addressList1.add(address1);
            }
        }
        return addressList1.size();
    }
}
