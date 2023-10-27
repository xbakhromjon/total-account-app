package uz.xbakhromjon.group.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import uz.xbakhromjon.group.request.ExpenseRequest;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.request.PersonRequest;
import uz.xbakhromjon.group.response.DebtorPersonResponse;
import uz.xbakhromjon.group.response.GroupResponse;

public interface GroupService {
    Long create(GroupRequest request);

    Long update(Long id, GroupRequest request);

    void delete(Long id);

    GroupResponse getOne(Long id);

    Page<GroupResponse> filter(Specification spec, Pageable pageable);

    void addPerson(Long groupId, PersonRequest request);

    void addExpense(Long groupId, ExpenseRequest request);

    Page<DebtorPersonResponse> getTransactions(Long groupId);
}
