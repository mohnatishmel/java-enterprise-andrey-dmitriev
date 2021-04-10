package by.itacademy.entities.task;

import by.itacademy.entities.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private int id;
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "TASK_NAME")
    private String name;
    private String description;
    @Column(name = "DEADLINE")
    private Date deadLine;
    private boolean fixed;
    @Column(name = "IN_BASKET")
    private boolean inBasket;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }
}
