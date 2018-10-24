package com.excilys.cdb.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.dao.DaoComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

public class ComputerService {

	private static ComputerService computerService = null;
	DaoComputer daoComputer;

	private ComputerService() {
		daoComputer = DaoComputer.getInstance();
	}

	public static ComputerService getInstance() {
		if (computerService == null) {
			computerService = new ComputerService();
		}
		return computerService;
	}

	public Computer showDetails() throws SQLException {
		int idComputer;

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir l'id du produit pour afficher ses détails : ");
		idComputer = sc.nextInt();

		return daoComputer.showDetails(idComputer);
	}

	public <T> List<Computer> findAll(int choix) throws SQLException {
		return Page.getPage(daoComputer.findAll(), choix, 10);
	}

	public void create() {
		String name;
		LocalDate introduced;
		LocalDate discontinued;
		Long idCompany;

		int day, month, year;

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le nom de l'ordinateur à ajouter : ");
		name = sc.nextLine();

		System.out.println("Saisie de la date d'introduction : ");
		System.out.println("Veuillez saisir le jour d'introduction :");
		day = sc.nextInt();

		System.out.println("Veuillez saisir le mois d'introduction :");
		month = sc.nextInt();

		System.out.println("Veuillez saisir l'année d'introduction :");
		year = sc.nextInt();

		introduced = LocalDate.of(year, month, day);

		System.out.println("Saisie de la date d'arret : ");
		System.out.println("Veuillez saisir le jour d'arret : ");
		day = sc.nextInt();

		System.out.println("Veuillez saisir le mois d'arret : ");
		month = sc.nextInt();

		System.out.println("Veuillez saisir l'année d'arret : ");
		year = sc.nextInt();

		discontinued = LocalDate.of(year, month, day);

		System.out.println("Veuillez saisir l'id de la compagnie");
		idCompany = sc.nextLong();

		daoComputer.create(new Computer(name, introduced, discontinued, new Company(idCompany)));

	}

	public void update() {
		int id;
		String name;
		LocalDate introduced;
		LocalDate discontinued;
		Long idCompany;

		int day, month, year;

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir l'id du produit à modifier : ");
		id = sc.nextInt();
		sc.nextLine();

		System.out.println("Veuillez saisir le nouveau nom du produit : ");
		name = sc.nextLine();

		System.out.println("Modification de la date d'introdution : ");
		System.out.println("Veuillez saisir le nouveau jour d'introduction :");
		day = sc.nextInt();

		System.out.println("Veuillez saisir le nouveau mois d'introduction :");
		month = sc.nextInt();

		System.out.println("Veuillez saisir la nouvelle année d'introduction :");
		year = sc.nextInt();

		introduced = LocalDate.of(year, month, day);

		System.out.println("Modification de la date d'arret : ");
		System.out.println("Veuillez saisir le nouveau jour d'arret : ");
		day = sc.nextInt();

		System.out.println("Veuillez saisir le nouveau  mois d'arret : ");
		month = sc.nextInt();

		System.out.println("Veuillez saisir la nouvelle année d'arret : ");
		year = sc.nextInt();

		discontinued = LocalDate.of(year, month, day);

		System.out.println("Veuillez saisir l'id de la compagnie");
		idCompany = sc.nextLong();

		daoComputer.update(id, new Computer(name, introduced, discontinued, new Company(idCompany)));
	}

	public void deleteByName() {
		String nameToDelete;

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le nom de l'ordinateur à supprimer : ");
		nameToDelete = sc.nextLine();

		daoComputer.deleteByName(nameToDelete);
	}

	public void deleteById() {
		int idToDelete;

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir l'id de l'ordinateur à supprimer : ");
		idToDelete = sc.nextInt();

		daoComputer.deleteById(idToDelete);
	}

}