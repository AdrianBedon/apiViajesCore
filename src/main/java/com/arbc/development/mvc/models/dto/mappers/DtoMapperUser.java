package com.arbc.development.mvc.models.dto.mappers;

import com.arbc.development.mvc.models.dto.UserDto;
import com.arbc.development.mvc.models.entities.User;

public class DtoMapperUser {

    private User user;

    private DtoMapperUser(){
    }

    public static DtoMapperUser builder() {
        return new DtoMapperUser();
    }

    public DtoMapperUser setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build() {
        if(user == null) {
            throw new RuntimeException("Debe enviar el entity user!");
        }
        return new UserDto(this.user.getId(), this.user.getUsername(), this.user.getEmail());
        
    }
    
}
