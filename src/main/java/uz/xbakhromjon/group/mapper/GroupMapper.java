package uz.xbakhromjon.group.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import uz.xbakhromjon.common.BaseMapper;
import uz.xbakhromjon.group.entity.GroupJpaEntity;
import uz.xbakhromjon.group.repository.GroupRepository;
import uz.xbakhromjon.group.repository.PersonRepository;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.response.GroupResponse;

@Mapper(componentModel = "spring")
public abstract class GroupMapper implements BaseMapper<GroupRequest, GroupResponse, GroupJpaEntity> {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PersonRepository personRepository;


    public abstract GroupResponse toResponse(GroupJpaEntity source);

    @AfterMapping
    public void setPeopleCount(@MappingTarget GroupResponse target, GroupJpaEntity source) {
        target.setPeopleCount(personRepository.countByGroupId(source.getId()));
    }

    @AfterMapping
    public void setTotalMoney(@MappingTarget GroupResponse target, GroupJpaEntity source) {
        Float totalMoneyOfGroup = personRepository.getTotalMoneyOfGroup(source.getId());
        target.setTotalMoney(totalMoneyOfGroup == null ? 0: totalMoneyOfGroup);
    }
}
