package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private Integer age;
    private City city;
}
