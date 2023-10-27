package uz.xbakhromjon.group.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.xbakhromjon.group.entity.GroupJpaEntity;
import uz.xbakhromjon.group.request.ExpenseRequest;
import uz.xbakhromjon.group.request.GroupRequest;
import uz.xbakhromjon.group.request.PersonRequest;
import uz.xbakhromjon.group.response.DebtorPersonResponse;
import uz.xbakhromjon.group.response.GroupResponse;
import uz.xbakhromjon.group.service.GroupService;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping()
    public ResponseEntity<Long> create(@RequestBody @Valid GroupRequest request) {
        Long response = groupService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody @Valid GroupRequest request) {
        Long response = groupService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteOne(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getOne(@PathVariable Long id) {
        GroupResponse response = groupService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Page<GroupResponse>> filter(@And({
            @Spec(path = "name", params = "name", spec = EqualIgnoreCase.class),
            @Spec(path = "owner.id", constVal = "#{T(uz.xbakhromjon.common.security.SecurityUtils).getCurrentUserId()}", valueInSpEL = true, spec = Equal.class),
    }
    ) Specification<GroupJpaEntity> spec,
                                                      @RequestParam(defaultValue = "1") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer size,
                                                      @RequestParam(defaultValue = "name") String sort,
                                                      @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Page<GroupResponse> response = groupService.filter(spec, PageRequest.of(page - 1, size, direction, sort, "id"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/people")
    public ResponseEntity<GroupResponse> addPerson(@PathVariable Long id, @RequestBody PersonRequest request) {
        groupService.addPerson(id, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/expenses")
    public ResponseEntity<GroupResponse> addExpense(@PathVariable Long id, @RequestBody ExpenseRequest request) {
        groupService.addExpense(id, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<Page<DebtorPersonResponse>> getGroupTransaction(@PathVariable Long id) {
        Page<DebtorPersonResponse> response = groupService.getTransactions(id);
        return ResponseEntity.ok(response);
    }
}
