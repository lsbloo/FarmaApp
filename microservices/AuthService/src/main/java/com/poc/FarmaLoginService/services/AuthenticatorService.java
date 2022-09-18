package com.poc.FarmaLoginService.services;

import com.poc.FarmaLoginService.base.BaseService;
import com.poc.FarmaLoginService.base.model.MessageClientResponseDTO;
import com.poc.FarmaLoginService.connector.client.WebClientBuilder;
import com.poc.FarmaLoginService.dtos.CreateAccountDTO;
import com.poc.FarmaLoginService.dtos.LoginRequestDTO;
import com.poc.FarmaLoginService.dtos.network.AuthSuccessulResponseDTO;
import com.poc.FarmaLoginService.dtos.network.MessageResponseDTO;
import com.poc.FarmaLoginService.model.Role;
import com.poc.FarmaLoginService.model.TypeRoles;
import com.poc.FarmaLoginService.model.UserAuth;
import com.poc.FarmaLoginService.model.UserDTO;
import com.poc.FarmaLoginService.repository.RoleRepository;
import com.poc.FarmaLoginService.repository.UserRepository;
import com.poc.FarmaLoginService.security.JwtUtils;
import com.poc.FarmaLoginService.utils.NetworkHandlerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.poc.FarmaLoginService.base.ConstantsApplication.CLIENT_ID_PARSE;
import static com.poc.FarmaLoginService.base.ConstantsApplication.ResourcesFarmaClient.RESOURCE_CREATE_USER_REFERENCE;

@Service
public class AuthenticatorService extends BaseService {


    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private WebClientBuilder client;


    @Autowired
    public AuthenticatorService(AuthenticationManager
                                        authenticationManager1,
                                UserRepository userRepository1,
                                RoleRepository roleRepository1,
                                PasswordEncoder encoder,
                                JwtUtils jwtUtils1) {

        this.authenticationManager = authenticationManager1;
        this.userRepository = userRepository1;
        this.roleRepository = roleRepository1;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils1;
        client = getFarmClient();
    }


    public void authenticateUser(LoginRequestDTO loginRequestDTO, NetworkHandlerEvent networkHandlerEvent) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        List<Integer> roles = this.roleRepository.findIdsRoleUserByUserId(this.userRepository.findByEmail(loginRequestDTO.getEmail()).get().getId());
        List<String> rolesUser = new ArrayList<>();
        for (Integer id_role : roles) {
            rolesUser.add(
                    this.roleRepository.getRoleById(id_role).getName()
            );
        }
        networkHandlerEvent.onResult(new AuthSuccessulResponseDTO(jwt,"Bearer "));
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void createUser(CreateAccountDTO createAccountDTO, NetworkHandlerEvent networkHandlerEvent) {
        findUserByEmail(createAccountDTO.getEmail()).ifPresentOrElse(
                (value) -> {
                    networkHandlerEvent.onResult(
                            new MessageResponseDTO("Error: Email is already taken! -> " + value.getEmail(), true));
                },
                () -> {
                    MessageClientResponseDTO messageClientResponseDTO = registerUser(createAccountDTO);
                    if (messageClientResponseDTO != null) {
                        networkHandlerEvent.onResult(new MessageResponseDTO("OK: User Registred! ->", false, "", messageClientResponseDTO));
                    } else {
                        networkHandlerEvent.onResult(
                                new MessageResponseDTO("Error: There was an error saving the user", true));
                    }
                });


    }

    public Optional<UserAuth> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private MessageClientResponseDTO registerUser(CreateAccountDTO createAccountDTO) {
        String pass = createAccountDTO.getPassword();
        UserAuth userAuth = new UserAuth(createAccountDTO.getEmail(), passwordEncoder().encode(pass), createAccountDTO.getName(), createAccountDTO.getCpf());
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleRepository.findByName(TypeRoles.ROLE_USER.name()).get());
        userAuth.setRoles(roles);
        userAuth.setActivated(true);
        UserAuth userAuth1 = userRepository.save(userAuth);
        return createUserLinkReferenceFarmaWeb(userAuth1);
    }


    private MessageClientResponseDTO createUserLinkReferenceFarmaWeb(UserAuth userAuth) {
        String dataResponse = client.sendMethodPost(RESOURCE_CREATE_USER_REFERENCE, new UserDTO(userAuth.getId()));
        MessageClientResponseDTO responseDTO = convertClientResponseToDTO(dataResponse);
        String s = CLIENT_ID_PARSE + responseDTO.getResponseDTO();
        String encoded = Base64.getEncoder().
                encodeToString(s.getBytes(StandardCharsets.UTF_8));

        responseDTO.setResponseDTO(encoded);
        return responseDTO;
    }

    public void createUserAdmin(CreateAccountDTO createAccountDTO) {
        String pass = createAccountDTO.getPassword();
        UserAuth userAuth = new UserAuth(createAccountDTO.getEmail(), passwordEncoder().encode(pass), createAccountDTO.getName(), createAccountDTO.getCpf());
        userAuth.setActivated(true);
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleRepository.findByName(TypeRoles.ROLE_ADMIN.name()).get());
        userAuth.setRoles(roles);
        userRepository.save(userAuth);
    }


    public String getNameOfUser() {
        return this.userRepository.findByEmail(getUserNameAuthenticate()).get().getName();
    }
}
