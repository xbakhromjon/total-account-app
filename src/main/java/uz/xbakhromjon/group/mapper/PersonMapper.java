package uz.xbakhromjon.group.mapper;

import org.mapstruct.Mapper;
import uz.xbakhromjon.common.BaseMapper;
import uz.xbakhromjon.group.entity.PersonJpaEntity;
import uz.xbakhromjon.group.request.PersonRequest;
import uz.xbakhromjon.group.response.PersonResponse;

@Mapper(componentModel = "spring")
public abstract class PersonMapper implements BaseMapper<PersonRequest, PersonResponse, PersonJpaEntity> {
}
