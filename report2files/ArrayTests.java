import static org.junit.Assert.*;

import org.junit.*;

public class ArrayTests {
  @Test
  public void testReversedInPlace() {
    int[] input = {1,2,3};
    ArrayExamples.reverseInPlace(input);
    assertArrayEquals(new int[] {3,2,1}, input);
  }

  @Test
  public void testReversePalindrome() {
    int[] input = {0,1,0};
    ArrayExamples.reverseInPlace(input);
    assertArrayEquals(new int[] {0,1,0}, input);
  }
}
