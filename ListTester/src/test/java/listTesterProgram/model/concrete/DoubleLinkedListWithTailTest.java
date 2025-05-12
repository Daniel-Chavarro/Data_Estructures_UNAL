package listTesterProgram.model.concrete;

import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.exceptions.EmptyList;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DoubleLinkedListWithTailTest {
    
    private LinkedList<Integer> integerList;
    private LinkedList<Boolean> booleanList;
    private LinkedList<String> stringList;
    
    @Before
    public void setUp() {
        integerList = new DoubleLinkedLinkedListWithTail<>();
        booleanList = new DoubleLinkedLinkedListWithTail<>();
        stringList = new DoubleLinkedLinkedListWithTail<>();
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue("New list should be empty", booleanList.isEmpty());
        
        booleanList.pushFront(true);
        assertFalse("List with elements should not be empty", booleanList.isEmpty());
        
        booleanList.popFront();
        assertTrue("List after removing all elements should be empty", booleanList.isEmpty());
    }
    
    @Test
    public void testPushFront() {
        // Test with boolean
        booleanList.pushFront(false);
        booleanList.pushFront(true);
        
        Node<Boolean> node = booleanList.find(true);
        assertNotNull("Should find pushed element", node);
        assertEquals("Found node should have the correct value", Boolean.TRUE, node.getValue());
    }
    
    @Test
    public void testPushBack() {
        // Test with string
        stringList.pushBack("red");
        stringList.pushBack("green");
        stringList.pushBack("blue");
        
        // Verify order
        Node<String> redNode = stringList.find("red");
        Node<String> greenNode = stringList.find("green");
        Node<String> blueNode = stringList.find("blue");
        
        assertNotNull("Should find red", redNode);
        assertNotNull("Should find green", greenNode);
        assertNotNull("Should find blue", blueNode);
        
        // Pop elements to check order
        assertEquals("First element should be red", "red", stringList.popFront());
        assertEquals("Second element should be green", "green", stringList.popFront());
        assertEquals("Third element should be blue", "blue", stringList.popFront());
    }
    
    @Test
    public void testPopFront() {
        // Test with integer
        integerList.pushFront(30);
        integerList.pushFront(20);
        integerList.pushFront(10);
        
        assertEquals("First pop should be 10", Integer.valueOf(10), integerList.popFront());
        assertEquals("Second pop should be 20", Integer.valueOf(20), integerList.popFront());
        assertEquals("Third pop should be 30", Integer.valueOf(30), integerList.popFront());
        assertTrue("List should be empty after popping all elements", integerList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopFrontEmptyList() {
        integerList.popFront(); // Should throw EmptyList exception
    }
    
    @Test
    public void testPopBack() {
        // Test with boolean
        booleanList.pushBack(true);
        booleanList.pushBack(false);
        booleanList.pushBack(true);
        
        assertEquals("First pop from back should be true", Boolean.TRUE, booleanList.popBack());
        assertEquals("Second pop from back should be false", Boolean.FALSE, booleanList.popBack());
        assertEquals("Third pop from back should be true", Boolean.TRUE, booleanList.popBack());
        assertTrue("List should be empty after popping all elements", booleanList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopBackEmptyList() {
        booleanList.popBack(); // Should throw EmptyList exception
    }
    
    @Test
    public void testFind() {
        // Test with integer
        integerList.pushBack(100);
        integerList.pushBack(200);
        integerList.pushBack(300);
        
        Node<Integer> node = integerList.find(200);
        assertNotNull("Should find existing element", node);
        assertEquals("Found node should have correct value", Integer.valueOf(200), node.getValue());

        try {
            Node<Integer> notFoundNode = integerList.find(999);
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            assertTrue("Should throw NoSuchElementException for non-existing element", true);
        }
    }
    
    @Test(expected = EmptyList.class)
    public void testFindEmptyList() {
        integerList.find(10); // Should throw EmptyList exception
    }
    
    @Test
    public void testErase() {
        // Test with string
        stringList.pushBack("dog");
        stringList.pushBack("cat");
        stringList.pushBack("bird");
        
        stringList.erase("cat");

        assertNotNull("Other elements should still be there", stringList.find("dog"));
        assertNotNull("Other elements should still be there", stringList.find("bird"));


        try {
            stringList.find("cat");
            fail("Should throw NoSuchElementException for erased element");
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }
    
    @Test(expected = EmptyList.class)
    public void testEraseEmptyList() {
        stringList.erase("test"); // Should throw EmptyList exception
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testEraseNonExistingElement() {
        stringList.pushBack("test");
        stringList.erase("nonexistent"); // Should throw NoSuchElementException
    }
    
    @Test
    public void testAddAfter() {
        // Test with integer
        integerList.pushBack(100);
        integerList.pushBack(300);
        
        Node<Integer> node = integerList.find(100);
        integerList.addAfter(node, 200);
        
        // Check order by popping
        assertEquals("First element should be 100", Integer.valueOf(100), integerList.popFront());
        assertEquals("Second element should be 200", Integer.valueOf(200), integerList.popFront());
        assertEquals("Third element should be 300", Integer.valueOf(300), integerList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddAfterNullNode() {
        integerList.addAfter(null, 10); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testAddBefore() {
        // Test with boolean
        booleanList.pushBack(true);
        booleanList.pushBack(false);
        
        Node<Boolean> node = booleanList.find(false);
        booleanList.addBefore(node, true);
        
        // Check order by popping
        assertEquals("First element should be true", Boolean.TRUE, booleanList.popFront());
        assertEquals("Second element should be true", Boolean.TRUE, booleanList.popFront());
        assertEquals("Third element should be false", Boolean.FALSE, booleanList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddBeforeNullNode() {
        booleanList.addBefore(null, true); // Should throw IllegalArgumentException
    }

}
