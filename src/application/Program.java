package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List <Employee> list = new ArrayList<>();
		
		System.out.println("Enter full file path:");// c:\temp\employee.txt "
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader (new FileReader(path))){
			
			String employeeCsv = br.readLine();
			while(employeeCsv != null) {
				String fields[] = employeeCsv.split(",");
				list.add(new Employee (fields[0], fields[1], Double.parseDouble(fields[2])));
				
				employeeCsv = br.readLine();
			}
			
			// Imprimir email das pessoas, com salario maior que X valor.
			System.out.println("Enter salary: ");
			double salary = sc.nextDouble();
			
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
					
			
			System.out.println("Email of people whose salary is more than "+ String.format("%.2f", salary) + ": ");
			emails.forEach(System.out::println);
			
			//Soma dos nomes que começam com a letra M
			Double sum = list.stream()
					.filter(s -> s.getName().charAt(0) == 'M')
					.map(s -> s.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
			
		}
		catch(IOException e) {
			System.out.println("Error reading file : " + e.getMessage());
		}
	}

}
