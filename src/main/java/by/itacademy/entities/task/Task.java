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
//    @Column(name = "description")
    private String description;
//    @Column(name = "DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date deadLine;
//    @Column(name = "fixed")
    private boolean fixed;
    @Column(name = "IN_BASKET")
    private boolean inBasket;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
