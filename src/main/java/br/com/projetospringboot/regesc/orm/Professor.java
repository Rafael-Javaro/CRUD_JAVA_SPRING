package br.com.projetospringboot.regesc.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome", nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "professor")
    private List<Discipline> disciplines;

    @Deprecated // Declarado somente para atender as necessidades do hibernate
    public Professor() {};

    public Professor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Professor:" +
                "id=" + id +
                ", Nome='" + name + '\'' +
                ", Email='" + email + '\'';
    }
}
