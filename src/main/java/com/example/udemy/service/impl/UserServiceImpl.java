package com.example.udemy.service.impl;

import com.example.udemy.dao.UserRepo;
import com.example.udemy.exceptions.ErrorMessages;
import com.example.udemy.exceptions.UserException;
import com.example.udemy.shared.dto.UserDto;
import com.example.udemy.entity.UserEntity;
import com.example.udemy.service.UserService;
import com.example.udemy.shared.utils.UserIdGeneration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo repo;
    @Autowired
    UserIdGeneration userIdGeneration;

    @Override
    public UserDto createUser(UserDto userDto)
    {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto,userEntity);
        userEntity.setUid(userIdGeneration.createUserId());
        userEntity.setEncryptedPassword("encrypted");
        userEntity.setEmailVerificationStatus(false);

        UserEntity standardDetails = repo.save(userEntity);

        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(standardDetails,returnVal);
        return returnVal;
    }
    @Override
    public UserDto getUserById(String uid)
    {
        UserDto returnValue = new UserDto();
        UserEntity entity = repo.findByUid(uid);
        BeanUtils.copyProperties(entity,returnValue);
        return returnValue;
    }
    @Override
    public UserDto updateUser(UserDto userDto, String uid)
    {
        UserDto returnValue = new UserDto();
       UserEntity entity = repo.findByUid(uid);
       if(entity==null)
           throw new UserException(ErrorMessages.RECORD_DOES_NOT_EXISTS.getErrorMessage());
       entity.setFirstName(userDto.getFirstName());
       entity.setLastName(userDto.getLastName());
       entity.setEmail(userDto.getEmail());
       entity.setPassword(userDto.getPassword());
       entity.setEncryptedPassword("encrypted");
       entity.setEmailVerificationStatus(false);
       UserEntity standardDetails = repo.save(entity);
       BeanUtils.copyProperties(standardDetails,returnValue);
        return returnValue;
    }

    @Override
    public UserDto deleteUser(String uid)
    {
        UserDto returnValue = new UserDto();
        UserEntity entity = repo.findByUid(uid);
        if(entity==null)
            throw new UserException(ErrorMessages.RECORD_DOES_NOT_EXISTS.getErrorMessage());
        else
            repo.delete(entity);
        BeanUtils.copyProperties(entity,returnValue);
        return returnValue;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
       List<UserDto> returnValue = new ArrayList<>();
        Pageable pageableRequest =   PageRequest.of(page, limit);
        Page<UserEntity> userPage = repo.findAll(pageableRequest);
        List<UserEntity> users = userPage.getContent();
        for(UserEntity u : users)
        {
            UserDto user = new UserDto();
            BeanUtils.copyProperties(u , user);
            returnValue.add(user);
        }

       return returnValue;
    }
}
