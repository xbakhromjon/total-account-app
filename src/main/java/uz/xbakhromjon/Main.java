package uz.xbakhromjon;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    // TODO: 10/27/2023 optimize this code
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person(1L, "Jamshid", 11_000F),
                new Person(2L, "Akmal", 14_000F),
                new Person(3L, "Murod", 20_000F),
                new Person(4L, "Nurullo", 15_000F),
                new Person(5L, "Qodirjon", 15_000F)
        );

        float total = people.stream().map(Person::getGivenMoney).reduce(0F, Float::sum);
        System.out.println(total);
        float avr = total / people.size();
        System.out.println(avr);

        Map<Boolean, List<Person>> partitioned = people.stream().filter(item -> !item.getGivenMoney().equals(avr)).collect(Collectors.partitioningBy(item ->
                (item.getGivenMoney() - avr) >= 0));
        List<Person> debtorPeople = partitioned.get(false);
        List<Person> entitledPeople = partitioned.get(true);

        List<DebtorPersonResponse> response = new ArrayList<>();
        int i = 0;
        int j = 0;
        I:
        while (i < debtorPeople.size()) {
            Person debtorPerson = debtorPeople.get(i);
            float debt = debtorPerson.getGivenMoney() - avr;
            DebtorPersonResponse responseItem = new DebtorPersonResponse(debtorPerson.getId(), debtorPerson.getNickname(), debtorPerson.getGivenMoney(), List.of());
            List<EntitledPersonResponse> entitledPeopleForOneDebtor = new ArrayList<>();
            II:
            while (j < entitledPeople.size()) {
                Person entitledPerson = entitledPeople.get(j);
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

        System.out.println(response);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
class Person {
    private Long id;
    private String nickname;
    private Float givenMoney;

    private Float takenMoney = 0F;

    public Person(Long id, String nickname, Float givenMoney) {
        this.id = id;
        this.nickname = nickname;
        this.givenMoney = givenMoney;
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
class DebtorPersonResponse {
    private Long id;
    private String nickname;
    private Float givenMoney;
    private List<EntitledPersonResponse> entitledPeople;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
class EntitledPersonResponse {
    private Long id;
    private String nickname;
    private Float givenMoney;
    private Float takenMoney;
}


