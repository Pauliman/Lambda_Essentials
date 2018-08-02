package lambda_essentials;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

	public static void main(String[] args) {

		// +++++++++ Use of the Predicate interface plus ternary operator +++++++++++

		/*
		 * The Predicate interface defines a couple of verification methods. Three
		 * default ones that don't have to be implemented, a static one and one called
		 * 'test(param)' which can be implemented using Lambda. After the behavior has
		 * been assigned to the object of the type Predicate, it has this new behavior
		 * without the need for an implementing class and instantiation of it.
		 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
		 * Example 1 shows a Lambda Expression assigning behavior to an otherwise 
		 * uninitiated variable of the type Predicate. The compiler can retrieve all 
		 * information required from the context (interface).
		 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		 * Example 2 works in a similar fashion but here the Lambda Expressions make
		 * use of the objects methods. 2a) uses the Predicate.test(param)-method also
		 * incorporating a method provided by the object itself. 2b) Only delivers the
		 * expected result to the Predicate using only the object method. This behavior
		 * could be had more easily but wait till you work with larger numbers of Person. 
		 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		 * Example 3 shows how you can work on Collections like ArrayLists using
		 * stream() and Lambda. '.stream()' allows to use several options on the ArrayList
		 * which make your life easier. Stream<T>.filter(Predicate<? superT> predicate)
		 * is the method signature. The filter method's parameter defines the argument
		 * as a Predicate. A Predicate object or Lambda must provide a 
		 * 'boolean test(T t)'-method so either 't -> truth condition(t)' 
		 * or verbosely (T t) -> {return 'truth condition including or not including t';} 
		 * or T::truth condition where T is the Class type that is contained in the 
		 * ArrayList and truth condition is a method provided by the class T and returning true/false. 
		 * So 'ArrayList.stream().filter(Lambda).count' iterates through all 10 Person objects
		 * and filters them according to a specified truth condition. Here the
		 * 'true'-cases are counted by .count() and returned as the result of this line
		 * of code. What used to be achieved by forEach-loops is now down to a single line.
		 * 
		 */

		// ++++++++++++ Example 1 ++++++++++++++++++++++++++++++++++++++

		Predicate<String> pred = word -> word.length() < 10;
		System.out.println(pred.test("HALLO WORLD") ? "Text contains less than 10 chars" : "Text contains more than 10 chars");

		// +++++++++++ Example 2 +++++++++++++++++++++++++++++++++++++++

		final class Person {
			
			private int age;

			public Person(int a) {this.age = a;}

			public int getAge() {return this.age;}

			public boolean isAdult() {return age >= 18;}

			public String toString() {return "Person is " + this.age + " years old";};
		} // end of class
		
		Person anna = new Person(17);
		Person emma = new Person(19);
		// a)
		Predicate<Person> pred_1 = x -> x.getAge() > 18;
		// b)
		Predicate<Person> pred_2 = Person::isAdult;

		System.out.println(pred_1.test(emma) ? "Person is an adult" : "Person is still an underager");
		System.out.println(pred_2.test(anna) ? "Person is an adult" : "Person is still an underager");

		// ++++++++++++ Example 3 +++++++++++++++++++++++++++++++++++++++

		ArrayList<Person> l = new ArrayList<>();
		for (int a = 0; a < 10; a++) {
			l.add(new Person((int) (Math.random() * 20) + 1));
		}
		System.out.println("Adults = " + l.stream().filter(Person::isAdult).count() + " -- List: " + l.toString());
		System.out.println("Over 10 = " + l.stream().filter(x -> x.getAge() > 10).count() + " -- List: " + l.toString());
	} // end of main()
} // end of class
