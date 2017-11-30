package util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.rules.ExpectedException;

/**
 * Intended for use with JUnit's {@link ExpectedException}. Provides the
 * opportunity to validate mocks when the test method throws an exception.
 * Example:
 * 
 * <pre>
 * {@literal @}Rule public ExpectedException thrown = ExpectedException.none();
 *
 * ...
 *
 * {@literal @}Test
 * public void testMethod() throws Exception {
 *    final Object mockObject = mock(Object.class);
 *
 *    ...
 *
 *    thrown.expect(MyException.class);
 *    thrown.expect(new VerificationMethod() {
 *       public void verificationMethod(Throwable thrownException) throws Exception {
 *          verify(object, times(3)).someMethod();
 *       }
 *    });
 *
 *    test.method(); // throws MyException
 * }
 * </pre>
 * 
 * @author FransonSR
 *
 */
public abstract class VerificationMethod extends BaseMatcher<Throwable> {

	@SuppressWarnings("squid:S00112") // this is used for testing with unknown
										// exceptions
	public abstract void verificationMethod(Throwable thrownException) throws Exception;

	@Override
	public boolean matches(Object item) {
		try {
			assertThat(item, is(instanceOf(Throwable.class)));
			verificationMethod((Throwable) item);
			return true;
		} catch (Exception e) {
			throw new AssertionError("Verification method exception", e);
		}
	}

	@Override
	public void describeTo(Description description) {
	}
}