package com.example.udemy.userController;

import com.example.udemy.exceptions.UserException;
import com.example.udemy.exceptions.ErrorMessages;
import com.example.udemy.shared.dto.UserDto;
import com.example.udemy.request.UserRequest;
import com.example.udemy.response.UserResponse;
import com.example.udemy.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class user {
    @Autowired
    UserService userService;
    @PostMapping
    public UserResponse postUsers(@RequestBody UserRequest userRequest) throws Exception
    {
        UserResponse res = new UserResponse();
// Custom exception is created here. Here UserException.....Error messages are created using enums
        if(userRequest.getFirstName().equals("")) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest,userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, res);

        return res;
    }
    @GetMapping("/{uid}")
    public UserResponse getUsers(@PathVariable String uid)
    {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = userService.getUserById(uid);
        BeanUtils.copyProperties(userDto,returnValue);
        return returnValue;
    }
    @PutMapping("/{uid}")
    public UserResponse putUsers(@PathVariable String uid, @RequestBody UserRequest req)
    {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(req,userDto);
        UserDto updatedUser = userService.updateUser(userDto,uid);
        BeanUtils.copyProperties(updatedUser,returnValue);
        return returnValue;
    }
    @DeleteMapping("/{uid}")
    public UserResponse deleteUser(@PathVariable String uid)
    {
        UserResponse returnValue = new UserResponse();
        UserDto userDto = userService.deleteUser(uid);
        BeanUtils.copyProperties(userDto,returnValue);
        return returnValue;
    }

    @GetMapping
    public List<UserResponse> getUsers(@RequestParam(value = "page",defaultValue = "0") int page,@RequestParam(value = "limit",defaultValue = "25") int limit)
    {
        List<UserResponse> returnValue = new ArrayList<>();
        List<UserDto> users = userService.getUsers(page,limit);
        for(UserDto user:users)
        {
            UserResponse model = new UserResponse();
            BeanUtils.copyProperties(user,model);
            returnValue.add(model);
        }

        return returnValue;

    }






}
