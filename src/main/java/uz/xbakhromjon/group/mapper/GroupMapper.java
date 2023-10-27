package uz.xbakhromjon.group.mapper;

import org.mapstruct.Mapper;
import uz.xbakhromjon.common.BaseMapper;
import uz.xbakhromjon.group.entity.GroupJpaEntity;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.response.GroupResponse;

@Mapper(componentModel = "spring")
public abstract class GroupMapper implements BaseMapper<GroupRequest, GroupResponse, GroupJpaEntity> {

}
