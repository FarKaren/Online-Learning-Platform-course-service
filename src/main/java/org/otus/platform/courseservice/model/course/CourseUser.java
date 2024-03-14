package org.otus.platform.courseservice.model.course;

import jakarta.persistence.*;
import lombok.*;
import org.otus.platform.courseservice.model.user.User;
import org.otus.platform.courseservice.model.user.UserType;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses_users")
public class CourseUser {
    @EmbeddedId
    private final CourseUserId id = new CourseUserId();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", nullable = false)
    @MapsId("courseId")
    private Course course;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @MapsId("userId")
    private User user;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
