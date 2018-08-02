package lambda_essentials;

import java.util.function.Predicate;

public class LongForm implements Predicate<String>{

	@Override
	public boolean test(String t) {		
		return t.length() > 10;
	} // end of test()

	

} // end of class
