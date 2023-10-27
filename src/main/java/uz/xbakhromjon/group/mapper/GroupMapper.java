package uz.xbakhromjon.group.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import uz.xbakhromjon.common.BaseMapper;
import uz.xbakhromjon.group.entity.GroupJpaEntity;
import uz.xbakhromjon.group.entity.PersonJpaEntity;
import uz.xbakhromjon.group.repository.GroupRepository;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.response.GroupResponse;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GroupMapper implements BaseMapper<GroupRequest, GroupResponse, GroupJpaEntity> {

    @Autowired
    private GroupRepository groupRepository;


    public abstract GroupResponse toResponse(GroupJpaEntity source);

    @AfterMapping
    public void setPeopleCount(@MappingTarget GroupResponse target, GroupJpaEntity source) {
        target.setPeopleCount(source.getPeople().size());
    }

    @AfterMapping
    public void setTotalMoney(@MappingTarget GroupResponse target, GroupJpaEntity source) {
        target.setTotalMoney(source.getPeople().stream().map(PersonJpaEntity::getGivenMoney).reduce(0F, Float::sum));
    }
}
