package uz.xbakhromjon.user.mapper;


import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import uz.xbakhromjon.auth.request.SignupRequest;
import uz.xbakhromjon.common.BaseMapper;
import uz.xbakhromjon.common.constants.ErrorMessages;
import uz.xbakhromjon.common.constants.ResourceNames;
import uz.xbakhromjon.common.exception.ResourceNotFoundException;
import uz.xbakhromjon.user.entity.UserJpaEntity;
import uz.xbakhromjon.user.repository.UserRepository;
import uz.xbakhromjon.user.response.UserResponse;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements BaseMapper<SignupRequest, UserResponse, UserJpaEntity> {
    @Autowired
    private UserRepository userRepository;

    public UserJpaEntity toEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.X_RESOURCE_NOT_FOUND.formatted(ResourceNames.USER)));
    }
}

