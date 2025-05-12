package listTesterProgram.model.concrete;

import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.exceptions.EmptyList;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedListWithTailTest {
    
    private LinkedList<Integer> integerList;
    private LinkedList<String> stringList;
    private LinkedList<Double> doubleList;
    
    @Before
    public void setUp() {
        integerList = new LinkedLinkedListWithTail<>();
        stringList = new LinkedLinkedListWithTail<>();
        doubleList = new LinkedLinkedListWithTail<>();
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue("New list should be empty", integerList.isEmpty());
        
        integerList.pushFront(1);
        assertFalse("List with elements should not be empty", integerList.isEmpty());
        
        integerList.popFront();
        assertTrue("List after removing all elements should be empty", integerList.isEmpty());
    }
    
    @Test
    public void testPushFront() {
        // Test with integer
        integerList.pushFront(42);
        integerList.pushFront(24);
        assertEquals("Should return first element", Integer.valueOf(24), integerList.find(24).getValue());
        
        // Test with string
        stringList.pushFront("hello");
        stringList.pushFront("world");
        assertEquals("Should return first element", "world", stringList.find("world").getValue());
        
        // Test with double
        doubleList.pushFront(3.14);
        doubleList.pushFront(2.71);
        assertEquals("Should return first element", Double.valueOf(2.71), doubleList.find(2.71).getValue());
    }
    
    @Test
    public void testPushBack() {
        // Test with integer
        integerList.pushBack(1);
        integerList.pushBack(2);
        integerList.pushBack(3);
        
        // Pop elements to check order
        assertEquals("First element should be 1", Integer.valueOf(1), integerList.popFront());
        assertEquals("Second element should be 2", Integer.valueOf(2), integerList.popFront());
        assertEquals("Third element should be 3", Integer.valueOf(3), integerList.popFront());
    }
    
    @Test
    public void testPopFront() {
        // Test with string
        stringList.pushFront("c");
        stringList.pushFront("b");
        stringList.pushFront("a");
        
        assertEquals("First pop should return 'a'", "a", stringList.popFront());
        assertEquals("Second pop should return 'b'", "b", stringList.popFront());
        assertEquals("Third pop should return 'c'", "c", stringList.popFront());
        assertTrue("List should be empty after popping all elements", stringList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopFrontEmptyList() {
        integerList.popFront(); // Should throw EmptyList exception
    }
    
    @Test
    public void testPopBack() {
        // Test with double
        doubleList.pushBack(1.1);
        doubleList.pushBack(2.2);
        doubleList.pushBack(3.3);
        
        assertEquals("First pop should return 3.3", Double.valueOf(3.3), doubleList.popBack());
        assertEquals("Second pop should return 2.2", Double.valueOf(2.2), doubleList.popBack());
        assertEquals("Third pop should return 1.1", Double.valueOf(1.1), doubleList.popBack());
        assertTrue("List should be empty after popping all elements", doubleList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopBackEmptyList() {
        integerList.popBack(); // Should throw EmptyList exception
    }
    
    @Test
    public void testFind() {
        // Test with integer
        integerList.pushBack(10);
        integerList.pushBack(20);
        integerList.pushBack(30);
        
        Node<Integer> node = integerList.find(20);
        assertNotNull("Should find existing element", node);
        assertEquals("Found node should have correct value", Integer.valueOf(20), node.getValue());
        
        Node<Integer> notFoundNode = integerList.find(99);
        assertNull("Should return null for non-existing element", notFoundNode);
    }
    
    @Test(expected = EmptyList.class)
    public void testFindEmptyList() {
        integerList.find(10); // Should throw EmptyList exception
    }
    
    @Test
    public void testErase() {
        // Test with string
        stringList.pushBack("apple");
        stringList.pushBack("banana");
        stringList.pushBack("cherry");
        
        stringList.erase("banana");
        
        assertNull("Erased element should not be found", stringList.find("banana"));
        assertNotNull("Other elements should still be there", stringList.find("apple"));
        assertNotNull("Other elements should still be there", stringList.find("cherry"));
    }
    
    @Test(expected = EmptyList.class)
    public void testEraseEmptyList() {
        integerList.erase(10); // Should throw EmptyList exception
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testEraseNonExistingElement() {
        integerList.pushBack(10);
        integerList.erase(20); // Should throw NoSuchElementException
    }
    
    @Test
    public void testAddAfter() {
        // Test with double
        doubleList.pushBack(1.1);
        doubleList.pushBack(3.3);
        
        Node<Double> node = doubleList.find(1.1);
        doubleList.addAfter(node, 2.2);
        
        // Check order by popping
        assertEquals("First element should be 1.1", Double.valueOf(1.1), doubleList.popFront());
        assertEquals("Second element should be 2.2", Double.valueOf(2.2), doubleList.popFront());
        assertEquals("Third element should be 3.3", Double.valueOf(3.3), doubleList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddAfterNullNode() {
        integerList.addAfter(null, 10); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testAddBefore() {
        // Test with integer
        integerList.pushBack(1);
        integerList.pushBack(3);
        
        Node<Integer> node = integerList.find(3);
        integerList.addBefore(node, 2);
        
        // Check order by popping
        assertEquals("First element should be 1", Integer.valueOf(1), integerList.popFront());
        assertEquals("Second element should be 2", Integer.valueOf(2), integerList.popFront());
        assertEquals("Third element should be 3", Integer.valueOf(3), integerList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddBeforeNullNode() {
        integerList.addBefore(null, 10); // Should throw IllegalArgumentException
    }

}
