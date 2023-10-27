package uz.xbakhromjon.group.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.xbakhromjon.group.request.ExpenseRequest;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.request.PersonRequest;
import uz.xbakhromjon.group.response.DebtorPersonResponse;
import uz.xbakhromjon.group.response.GroupResponse;
import uz.xbakhromjon.group.spec.GroupSpecification;

public interface GroupService {
    Long create(GroupRequest request);

    Long update(Long id, GroupRequest request);

    void delete(Long id);

    GroupResponse getOne(Long id);

    Page<GroupResponse> filter(GroupSpecification spec, Pageable pageable);

    void addPerson(PersonRequest request);

    void addExpense(ExpenseRequest request);

    Page<DebtorPersonResponse> getTransactions(Long groupId);
}
