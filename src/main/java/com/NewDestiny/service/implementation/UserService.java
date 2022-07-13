package com.NewDestiny.service.implementation;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.UserDto;
import com.NewDestiny.model.dto.UserModelDto;
import com.NewDestiny.model.entity.UserEntity;
import com.NewDestiny.model.entity.VerificationToken;
import com.NewDestiny.repository.IUserRepository;
import com.NewDestiny.repository.IVerificationTokenRepository;
import com.NewDestiny.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IVerificationTokenRepository iVerificationTokenRepository;

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntityToSave = objectMapper.convertValue(userDto, UserEntity.class);
        userRepository.save(userEntityToSave);
        return userDto;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> usersDtos = new ArrayList<>();

        for(UserEntity userEntity : userEntities){
            usersDtos.add(objectMapper.convertValue(userEntity, UserDto.class));
        }

        return usersDtos;
    }

    @Override
    public UserDto findById(Long id) {
        UserDto userFound = objectMapper.convertValue(userRepository.findById(id), UserDto.class);
        return userFound;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        UserDto userFound = findById(id);
        if (userFound != null)
            userRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el usuario con el id= "+id+".Ingrese un id correcto por favor" );

    }

    @Override
    public void update(UserDto userDto) {
        UserEntity modifiedUserEntity = objectMapper.convertValue(userDto, UserEntity.class);
        userRepository.save(modifiedUserEntity);
    }

    @Override
    public UserEntity registerUser(UserModelDto userModelDto) {
        UserEntity user = new UserEntity();
        user.setEmail(userModelDto.getEmail());
        user.setName(userModelDto.getName());
        user.setSurname(userModelDto.getSurname());
        user.setRole(userModelDto.getRole());
        user.setCity(userModelDto.getCity());
        user.setPassword(passwordEncoder.encode(userModelDto.getPassword()));
        userRepository.save(user);
        return user;
    }

    public void saveVerificationTokenForUser(String token, UserEntity user) {
        VerificationToken verificationToken = new VerificationToken(user,token);
        iVerificationTokenRepository.save(verificationToken);
    }


    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = iVerificationTokenRepository.findByToken(token);

        if(verificationToken == null){
            return "invalid";
        }
        UserEntity user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            iVerificationTokenRepository.delete(verificationToken);
            return "expire";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = iVerificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        iVerificationTokenRepository.save(verificationToken);
        return verificationToken;
    }
}

