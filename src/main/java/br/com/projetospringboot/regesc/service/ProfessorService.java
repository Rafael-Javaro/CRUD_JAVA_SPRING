package br.com.projetospringboot.regesc.service;

import br.com.projetospringboot.regesc.orm.Professor;
import br.com.projetospringboot.regesc.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) { // Injeção de Dependência
        this.repository = repository;
    }

    public void menu(Scanner scanner) {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("\n Qual ação vocẽ gostaria de Executar ?");
            System.out.println("0 - Voltar ao Menu anterior");
            System.out.println("1 - Cadastrar Novo Professor");
            System.out.println("2 - Atualizar Professor já cadastrado");
            System.out.println("3 - Visualizar todos os Professores");
            System.out.println("4 - Deletar Professor");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    this.createNewProfessor(scanner);
                    break;
                case 2:
                    this.updateProfessor(scanner);
                    break;
                case 3:
                    this.listAllProfessors();
                    break;
                case 4:
                    this.deleteProfessorById(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }

        }
    }

    public void createNewProfessor (Scanner scanner) {
        System.out.println("Digite o nome do Professor: ");
        String name = scanner.next();
        System.out.println("Digite o email do Professor: ");
        String email = scanner.next();

        Professor professor = new Professor(name, email);
        this.repository.save(professor);
        System.out.println("\nNovo Professor Cadastrado:");
        System.out.println(professor);
    }

    public void updateProfessor (Scanner scanner) {
        System.out.println("Digite o ID do Professor: ");
        long id = scanner.nextLong();
        Optional<Professor> professorById = this.repository.findById(id);

        if (professorById.isPresent()) {
            Professor professorToBeUpdated = professorById.get();
            System.out.println("Digite o novo nome do Professor: ");
            String name = scanner.next();
            System.out.println("Digite o novo email do Professor: ");
            String email = scanner.next();
            professorToBeUpdated.setName(name);
            professorToBeUpdated.setEmail(email);
            this.repository.save(professorToBeUpdated);
            System.out.println("\nNovo Professor Cadastrado: ");
            System.out.println(professorToBeUpdated);
        } else {
            System.out.println("O id informado, id: " + id + ", é inválido");
        }
    }

    public void listAllProfessors () {
        Iterable<Professor> professores = this.repository.findAll();
        for (Professor professor: professores) { System.out.println(professor + "\n"); }
    }

    public void deleteProfessorById (Scanner scanner) {
        System.out.println("Digite o ID do Professor: ");
        long id = scanner.nextLong();
        Optional<Professor> professorById = this.repository.findById(id);
        if (professorById.isPresent()) {
            this.repository.deleteById(id);
            System.out.println("Professor Deletado");
        } else {
            System.out.println("O id informado, id: " + id + ", é inválido");
        }
    }
}
