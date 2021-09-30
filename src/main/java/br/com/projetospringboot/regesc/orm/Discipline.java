package br.com.projetospringboot.regesc.orm;

import javax.persistence.*;

@Entity
@Table(name = "materias")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ID_do_Professor", referencedColumnName = "id")
    private Professor professor;

    @Deprecated
    public Discipline() {};

    public Discipline(String name, Professor professor) {
        this.name = name;
        this.professor = professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Matéria:" +
                "id=" + id +
                ", Nome='" + name + '\'';
    }
}
