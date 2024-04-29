import FunctionalsInterfaces.Evaluate;
import FunctionalsInterfaces.Functionable;
import FunctionalsInterfaces.Printable;
import FunctionalsInterfaces.Retrievable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
//*****************************Part 1***********************************************
      //------------------------------consumer----------------------
        consumer();
      //------------------------------supplier----------------------
        supplier();
      //------------------------------predicate---------------------
        predicate();
      //------------------------------check-------------------------
        check();
      //------------------------------function----------------------
        function();
//*******************************Part 2*********************************************
        List<Person> listPeople = getPeople();
      //--------------------------sortAge()-------------------------
        sortAge(listPeople);
      //--------------------------sortName()------------------------
        sortName(listPeople);
      //--------------------------sorHeight()-----------------------
        sortHeight(listPeople);

    }

//*********************************************************** Part 1 ***************************************************************
    //===================================Question 1======================================================
    public static void consumer() {
        // a) Utilisation d'une expression lambda pour implémenter l'interface Printable
        // Utilisation d'une expression lambda
        Printable<String> printableLambda = (String s) -> System.out.println(s);
        printableLambda.print("Printable lambda");

        // Utilisation d'une expression lambda pour implémenter 1a en utilisant un Consumer
        Consumer<String> printableConsumerLambda = s -> System.out.println(s);
        printableConsumerLambda.accept("Printable lambda using Consumer with lambda");

        Consumer<String> printableConsumerMethodRef = System.out::println;
        printableConsumerMethodRef.accept("Printable lambda using Consumer with method reference");
    }
   // ===================================Question 2 ====================================================

    public static void supplier() {
        // a) Utilisation d'une expression lambda pour implémenter l'interface Retrievable
        Retrievable<Integer> retrievableLambda = () -> 77;
        System.out.println(retrievableLambda.retrieve());

        // b) Utilisation d'une expression lambda pour implémenter 2a en utilisant un Supplier
        Supplier<Integer> supplierLambda = () -> 77;
        System.out.println(supplierLambda.get());
    }

    // ==================================Question 3 ===================================================
    public static void predicate() {
        // a) Utilisation d'une expression lambda pour implémenter l'interface Evaluate
        Evaluate<Integer> evaluateLambda = (Integer value) -> value < 0;
        System.out.println("Is -1 < 0 ? ---> " + evaluateLambda.isNegative(-1));
        System.out.println("Is +1 < 0 ? ---> " + evaluateLambda.isNegative(1));

        // b) Utilisation d'une expression lambda pour implémenter 3a en utilisant un Predicate
        Predicate<Integer> predicateLambda = value -> value < 0;
        System.out.println("Is -1 < 0? " + predicateLambda.test(-1));
        System.out.println("Is +1 < 0? " + predicateLambda.test(1));
    }


    public static <T> boolean check(T value, Predicate<T> predicate) {
        return predicate.test(value);
    }
    public static void check() {
        // c) Utilisation de la méthode check() avec différentes expressions lambda de Predicate

        // Vérifier si un nombre est pair
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + check(4, isEven));
        System.out.println("Is 7 even? " + check(7, isEven));

        // Vérifier si une chaîne commence par "Mr."
        Predicate<String> startsWithMr = s -> s.startsWith("Mr.");
        System.out.println("Does \"Mr. Joe Bloggs\" start with \"Mr.\"? " + check("Mr. Joe Bloggs", startsWithMr));
        System.out.println("Does \"Ms. Ann Bloggs\" start with \"Mr.\"? " + check("Ms. Ann Bloggs", startsWithMr));

        // Vérifier si une personne est adulte (âge >= 18)
        Predicate<Person> isAdult = p -> p.getAge() >= 18;
        Person mike = new Person( 33,"Mike", 1.8);
        Person ann = new Person( 13,"Ann", 1.4);
        System.out.println("Is Mike an adult? " + check(mike, isAdult));
        System.out.println("Is Ann an adult? " + check(ann, isAdult));
    }

    //===================================Question 4=================================
    public static void function() {
        // a) Utilisation d'une expression lambda pour implémenter l'interface Functionable
        Functionable<Integer, String> functionableLambda = (Integer number) -> "Number is: " + number;
        System.out.println(functionableLambda.applyThis(25));

        // b) Utilisation d'une expression lambda pour implémenter 4a en utilisant un Function
        Function<Integer, String> functionLambda = number -> "Number is: " + number;
        System.out.println(functionLambda.apply(25));
    }

    //******************************************************** Part 2 ************************************************************
    //==================================Question 5============================
    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person( 33,"Mike", 1.8));
        result.add(new Person( 25,"Mary", 1.4));
        result.add(new Person( 34,"Alan", 1.7));
        result.add(new Person( 30,"Zoe", 1.5));
        return result;
    }

    //==================================Question 6============================
    private static void sortAge(List<Person> people) {
        // a) Utilisation de la méthode sort() pour trier les objets Person par âge croissant
        people.sort(Comparator.comparing(Person::getAge));// ou bien : people.sort(Comparator.comparing((Person p) -> p.getAge()));


        // b) Sortie de la liste triée en utilisant la méthode forEach() et une expression lambda
        System.out.println("Sorted list of people by age:");
        people.forEach(person -> System.out.println(person.getName() + ", Age: " + person.getAge()));
        //=====================question 9=========================
        System.out.println("Sorted list of people by age using method reference:");
        people.forEach(System.out::println);
    }

    private static void sortName(List<Person> people) {
        // a) Utilisation de la méthode sort() pour trier les objets Person par ordre alphabétique croissant du nom
        people.sort(Comparator.comparing(Person::getName)); // ou bien people.sort(Comparator.comparing((Person p) -> p.getName()));

        // b) Sortie de la liste triée en utilisant la méthode forEach() et une expression lambda
        System.out.println("Sorted list of people by name:");
        people.forEach(person -> System.out.println(person.getName() + ", Age: " + person.getAge()));
        //=====================question 9=========================
        System.out.println("Sorted list of people by name using method reference:");
        people.forEach(System.out::println);
    }
    private static void sortHeight(List<Person> people) {
        // a) Utilisation de la méthode sort() pour trier les objets Person par ordre croissant de la hauteur
        people.sort(Comparator.comparing(Person::getHeight));

        // b) Sortie de la liste triée en utilisant la méthode forEach() et une expression lambda
        System.out.println("Sorted list of people by height:");
        people.forEach(person -> System.out.println(person.getName() + ", Height: " + person.getHeight()));
        //=====================question 9=========================
        System.out.println("Sorted list of people by height using method reference:");
        people.forEach(System.out::println);

    }

}

