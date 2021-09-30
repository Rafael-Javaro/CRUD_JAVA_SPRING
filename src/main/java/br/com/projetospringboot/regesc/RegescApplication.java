package br.com.projetospringboot.regesc;

import br.com.projetospringboot.regesc.service.DisciplineService;
import br.com.projetospringboot.regesc.service.ProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {
	private ProfessorService professorService;
    private DisciplineService disciplineService;

	public RegescApplication(ProfessorService professorService, DisciplineService disciplineService) {

		this.professorService = professorService;
		this.disciplineService = disciplineService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
			boolean isTrue = true;
			while (isTrue) {
				System.out.println("\n Qual ação vocẽ gostaria de Executar ?");
				System.out.println("0 - Sair");
				System.out.println("1 - Menu Professores");
                System.out.println("2 - Menu Matérias");

				int option = scanner.nextInt();
				if (option == 1) {
                    professorService.menu(scanner);
                } else if (option == 2) {
                    disciplineService.menu(scanner);
				} else {
					isTrue = false;
			    }
		}
	}
}
