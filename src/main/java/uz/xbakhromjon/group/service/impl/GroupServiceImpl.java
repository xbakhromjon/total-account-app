package uz.xbakhromjon.group.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.xbakhromjon.group.request.ExpenseRequest;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.request.PersonRequest;
import uz.xbakhromjon.group.response.DebtorPersonResponse;
import uz.xbakhromjon.group.response.GroupResponse;
import uz.xbakhromjon.group.service.GroupService;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    @Override
    public Long create(GroupRequest request) {
        return null;
    }

    @Override
    public Long update(Long id, GroupRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GroupResponse getOne(Long id) {
        return null;
    }

    @Override
    public Page<GroupResponse> filter(Specification spec, Pageable pageable) {
        return null;
    }

    @Override
    public void addPerson(Long groupId, PersonRequest request) {

    }

    @Override
    public void addExpense(Long groupId, ExpenseRequest request) {

    }

    @Override
    public Page<DebtorPersonResponse> getTransactions(Long groupId) {
        return null;
    }

    private void throwIfUserNotOwner(Long userId, Long groupId) {

    }

}
