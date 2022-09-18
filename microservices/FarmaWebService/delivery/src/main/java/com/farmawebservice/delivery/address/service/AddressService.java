package com.farmawebservice.delivery.address.service;

import com.farmawebservice.delivery.address.model.Address;
import com.farmawebservice.delivery.address.model.dto.AddressDTO;
import com.farmawebservice.delivery.address.model.dto.DAddressDTO;
import com.farmawebservice.delivery.address.repository.AddressRepository;
import com.farmawebservice.delivery.base.service.BaseService;
import com.farmawebservice.delivery.user.model.User;
import com.farmawebservice.delivery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
