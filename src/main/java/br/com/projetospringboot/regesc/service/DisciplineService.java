package br.com.projetospringboot.regesc.service;

import br.com.projetospringboot.regesc.orm.Discipline;
import br.com.projetospringboot.regesc.orm.Professor;
import br.com.projetospringboot.regesc.repository.DisciplineRepository;
import br.com.projetospringboot.regesc.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class DisciplineService {
    DisciplineRepository disciplineRepository;
    ProfessorRepository professorRepository;

    public DisciplineService(DisciplineRepository disciplineRepository, ProfessorRepository professorRepository) {
        this.disciplineRepository = disciplineRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("\n Qual ação vocẽ gostaria de Executar ?");
            System.out.println("0 - Voltar ao Menu anterior");
            System.out.println("1 - Cadastrar Nova Matéria");
            System.out.println("2 - Atualizar Matéria já cadastrada");
            System.out.println("3 - Visualizar todas as Matérias");
            System.out.println("4 - Deletar Matéria");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    this.createNewDiscipline(scanner);
                    break;
                case 2:
                    this.updateDiscipline(scanner);
                    break;
                case 3:
                   this.listAllDiscipline();
                    break;
                case 4:
                    this.deleteDisciplineById(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }

        }
    }

    public void createNewDiscipline(Scanner scanner) {
        System.out.println("Digite o nome da Matéria: ");
        String name = scanner.next();
        System.out.println("Digite o ID do Professor");
        long professorID = scanner.nextInt();
        Optional<Professor> professor = this.professorRepository.findById(professorID);
        if (professor.isPresent()) {
            System.out.println(professor);
            Professor assignedProfessor = professor.get();
            Discipline discipline = new Discipline(name, assignedProfessor);
            this.disciplineRepository.save(discipline);
        } else {
            System.out.println("ID do Professor informado é Inválido");
        }
    }

    public void updateDiscipline(Scanner scanner) {
        System.out.println("Digite o ID da Matéria: ");
        long id = scanner.nextLong();
        Optional<Discipline> disciplinesById = this.disciplineRepository.findById(id);

        if (disciplinesById.isPresent()) {
            System.out.println("Digite o nome da Matéria: ");
            String name = scanner.next();
            System.out.println("Digite o ID do Professor");
            long professorID = scanner.nextInt();
            Optional<Professor> professor = this.professorRepository.findById(professorID);
            if (professor.isPresent()) {
                Professor assignedProfessor = professor.get();
                Discipline discipline = new Discipline(name, assignedProfessor);
                this.disciplineRepository.save(discipline);
            } else {
                System.out.println("ID do Professor informado é Inválido");
            }
        } else {
            System.out.println("O id informado, id: " + id + ", é inválido");
        }
    }

    public void listAllDiscipline() {
        Iterable<Discipline> disciplines = this.disciplineRepository.findAll();
        disciplines.forEach(discipline -> System.out.println(discipline + "\n"));
    }

    public void deleteDisciplineById(Scanner scanner) {
        System.out.println("Digite o ID da Matéria: ");
        long id = scanner.nextLong();
        Optional<Discipline>  disciplineToBeDeleted = this.disciplineRepository.findById(id);

        if (disciplineToBeDeleted.isPresent()) {
            this.disciplineRepository.deleteById(id);
            System.out.println("Natéria Deletada");
        } else {
            System.out.println("O id informado, id: " + id + ", é inválido");
        }
    }
}
