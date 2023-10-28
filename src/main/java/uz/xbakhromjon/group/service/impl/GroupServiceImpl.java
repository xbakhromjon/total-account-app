package uz.xbakhromjon.group.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.xbakhromjon.common.constants.ErrorMessages;
import uz.xbakhromjon.common.constants.ResourceNames;
import uz.xbakhromjon.common.exception.AccessDeniedException;
import uz.xbakhromjon.common.exception.BadRequestException;
import uz.xbakhromjon.common.exception.ResourceNotFoundException;
import uz.xbakhromjon.common.security.SecurityUtils;
import uz.xbakhromjon.group.entity.GroupJpaEntity;
import uz.xbakhromjon.group.entity.PersonJpaEntity;
import uz.xbakhromjon.group.mapper.GroupMapper;
import uz.xbakhromjon.group.repository.GroupRepository;
import uz.xbakhromjon.group.request.ExpenseRequest;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.request.PersonRequest;
import uz.xbakhromjon.group.response.DebtorPersonResponse;
import uz.xbakhromjon.group.response.EntitledPersonResponse;
import uz.xbakhromjon.group.response.GroupResponse;
import uz.xbakhromjon.group.service.GroupService;
import uz.xbakhromjon.group.service.PersonService;
import uz.xbakhromjon.user.entity.UserJpaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;
    private final PersonService personService;

    @Override
    public Long create(GroupRequest request) {
        // map request to entity
        GroupJpaEntity entity = groupMapper.toEntity(request);

        // set current user as owner
        entity.setOwner(UserJpaEntity.builder().id(SecurityUtils.getCurrentUserId()).build());

        // save entity
        entity = groupRepository.save(entity);
        return entity.getId();
    }

    @Override
    public Long update(Long id, GroupRequest request) {
        // find group id and currentUserId
        GroupJpaEntity entity = getOrThrowById(id);
        // partial map request to entity
        groupMapper.partialMap(entity, request);
        // save entity
        groupRepository.save(entity);
        return id;
    }

    @Override
    public void delete(Long id) {
        // check group exists
        throwIfGroupNotExists(id);
        // delete
        groupRepository.deleteById(id);
    }

    // TODO: 10/27/2023 people qo'shilib chiqmayapti.
    @Override
    public GroupResponse getOne(Long id) {
        // check current user is owner of group
        throwIfUserNotOwner(SecurityUtils.getCurrentUserId(), id);
        // find group
        GroupJpaEntity entity = getOrThrowById(id);
        return groupMapper.toResponse(entity);
    }

    @Override
    public Page<GroupResponse> filter(Specification<GroupJpaEntity> spec, Pageable pageable) {
        Page<GroupJpaEntity> groupPage = groupRepository.findAll(spec, pageable);
        List<GroupResponse> content = groupMapper.toResponse(groupPage.getContent());
        return new PageImpl<>(content, pageable, groupPage.getTotalElements());
    }

    @Override
    public void addPerson(Long groupId, PersonRequest request) {
        // check group
        throwIfGroupNotExists(groupId);
        // create person
        personService.create(groupId, request);
    }

    @Override
    public void addMoneyToPerson(Long groupId, Long personId, float amount) {
        // check current user is owner of this group
        throwIfUserNotOwner(SecurityUtils.getCurrentUserId(), groupId);
        // check this group contains this person
        if (!isPersonBelongGroup(personId, groupId)) {
            throw new BadRequestException(ErrorMessages.INVALID_X_ID.formatted(ResourceNames.PERSON));
        }
        personService.addMoney(personId, amount);
    }

    @Override
    public void addExpense(Long groupId, ExpenseRequest request) {

    }


    @Override
    public List<DebtorPersonResponse> getTransactions(Long groupId) {
        // check group exists
        throwIfGroupNotExists(groupId);

        // find all group persons
        List<PersonJpaEntity> groupPeople = personService.getAllByGroupId(groupId);

        float total = groupPeople.stream().map(PersonJpaEntity::getGivenMoney).reduce(0F, Float::sum);
        System.out.println(total);
        float avr = total / groupPeople.size();
        System.out.println(avr);

        Map<Boolean, List<PersonJpaEntity>> partitioned = groupPeople.stream().filter(item -> item.getGivenMoney() != avr).collect(Collectors.partitioningBy(item ->
                (item.getGivenMoney() - avr) >= 0));
        List<PersonJpaEntity> debtorPeople = partitioned.get(false);
        List<PersonJpaEntity> entitledPeople = partitioned.get(true);

        List<DebtorPersonResponse> response = new ArrayList<>();
        int i = 0;
        int j = 0;
        I:
        while (i < debtorPeople.size()) {
            PersonJpaEntity debtorPerson = debtorPeople.get(i);
            float debt = debtorPerson.getGivenMoney() - avr;
            DebtorPersonResponse responseItem = new DebtorPersonResponse(debtorPerson.getId(), debtorPerson.getNickname(), debtorPerson.getGivenMoney(), List.of());
            List<EntitledPersonResponse> entitledPeopleForOneDebtor = new ArrayList<>();
            II:
            while (j < entitledPeople.size()) {
                PersonJpaEntity entitledPerson = entitledPeople.get(j);
                float repayment = entitledPerson.getGivenMoney() - avr - entitledPerson.getTakenMoney();
                debt = debt + repayment;
                // one to one
                if (debt == 0) {
                    entitledPeopleForOneDebtor.add(new EntitledPersonResponse(entitledPerson.getId(), entitledPerson.getNickname(), entitledPerson.getGivenMoney(), repayment));
                    j++;
                    break II;
                }
                // one to many
                else if (debt < 0) {
                    entitledPeopleForOneDebtor.add(new EntitledPersonResponse(entitledPerson.getId(), entitledPerson.getNickname(), entitledPerson.getGivenMoney(), repayment));
                    j++;
                    continue II;
                }
                // many to one
                else if (debt > 0) {
                    entitledPeopleForOneDebtor.add(new EntitledPersonResponse(entitledPerson.getId(), entitledPerson.getNickname(), entitledPerson.getGivenMoney(), repayment - debt));
                    entitledPerson.setTakenMoney(repayment - debt);
                    break II;
                } else {
                    j++;
                }
            }
            responseItem.setEntitledPeople(entitledPeopleForOneDebtor);
            response.add(responseItem);
            i++;
        }
        return response;
    }

    @Override
    public void updateGroupPerson(Long groupId, Long personId, PersonRequest request) {
        // check current user is owner of this group
        throwIfUserNotOwner(SecurityUtils.getCurrentUserId(), groupId);
        // check this group contains this person
        if (!isPersonBelongGroup(personId, groupId)) {
            throw new BadRequestException(ErrorMessages.INVALID_X_ID.formatted(ResourceNames.PERSON));
        }
        // partial map request to entity
        personService.update(personId, request);
    }

    private void throwIfUserNotOwner(Long userId, Long groupId) {
        if (!groupRepository.existsByIdAndOwnerId(groupId, userId)) {
            throw new AccessDeniedException(ErrorMessages.X_RESOURCE_NOT_FOUND.formatted(ResourceNames.GROUP));
        }
    }

    private void throwIfGroupNotExists(Long groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new ResourceNotFoundException(ErrorMessages.X_RESOURCE_NOT_FOUND.formatted(ResourceNames.GROUP));
        }
    }

//    private GroupJpaEntity getWithPeopleOrThrowById(Long groupId) {
//        return groupRepository.findWithPeopleById(groupId).
//                orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.X_RESOURCE_NOT_FOUND.formatted(ResourceNames.GROUP)));
//    }

    private GroupJpaEntity getOrThrowById(Long groupId) {
        return groupRepository.findById(groupId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.X_RESOURCE_NOT_FOUND.formatted(ResourceNames.GROUP)));
    }


    private boolean isPersonBelongGroup(Long personId, Long groupId) {
        return personService.existsByIdAndGroupId(personId, groupId);
    }

}
