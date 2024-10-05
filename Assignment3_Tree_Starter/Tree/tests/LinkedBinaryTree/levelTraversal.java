import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;
import java.util.Iterator;

@jug.SuiteName("Level Traversal")
public class levelTraversal {

	private LinkedBinaryTree<String> TARGET = init();
	private LinkedBinaryTree<String> T = init();

	public LinkedBinaryTree<String> init() {
		return new LinkedBinaryTree<String>();
	}

	@Before
	public void setup() throws Throwable {
		
	}

	@org.junit.Test(timeout=1000)
	@jug.TestName("Test Level by levels: {A,B,C} -> level order: {A,B,C}")
	public void Test1() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
		Position<String> n2 = TARGET.addLeft(n1, "B");
		Position<String> n3 = TARGET.addRight(n1, "C");

		String[] expected = {"A","B","C"}
		;
		List<String> actural = TARGET.levelOrderElements(TARGET.root());
		org.junit.Assert.assertEquals(expected.length, actural.size());
		int match = 0;
		for (int i=0;i<expected.length;i++) {
			   if (actural.get(i).equals(expected[i])) match++;
			}
		
		org.junit.Assert.assertEquals("Test level order  : {A,B,C} ", (Object)(expected.length), (Object)(match));
	}


	@org.junit.Test(timeout=1000)
	@jug.TestName("Test Level  complete tree by levels: {A,B,C,D,E,F,G} -> level order: {A,B,C,D,E,F,G}")
	public void Test2() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
		Position<String> n2 = TARGET.addLeft(n1, "B");
		Position<String> n3 = TARGET.addRight(n1, "C");
		Position<String> n4 = TARGET.addLeft(n2, "D");
		Position<String> n5 = TARGET.addRight(n2, "E");
		Position<String> n6 = TARGET.addLeft(n3, "F");
		Position<String> n7 = TARGET.addRight(n3, "G");
		String[] expected = {"A","B", "C","D", "E", "F", "G"}
		;
		List<String> actural = TARGET.levelOrderElements(TARGET.root());
		org.junit.Assert.assertEquals(expected.length, actural.size());
		int match = 0;
		for (int i=0;i<expected.length;i++) {
			   if (actural.get(i).equals(expected[i])) match++;
			}
		
		org.junit.Assert.assertEquals("Test inorderTraveral  complete tree by levels: {A,B,C,D,E,F,G} -> inorder: {D,B,E,A,F,C,G}", (Object)(expected.length), (Object)(match));
	}


	
}
