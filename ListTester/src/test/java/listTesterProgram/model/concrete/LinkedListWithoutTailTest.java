package listTesterProgram.model.concrete;

import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.exceptions.EmptyList;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedListWithoutTailTest {
    
    private LinkedList<Integer> integerList;
    private LinkedList<String> stringList;
    private LinkedList<Character> charList;
    
    @Before
    public void setUp() {
        integerList = new LinkedLinkedListWithoutTail<>();
        stringList = new LinkedLinkedListWithoutTail<>();
        charList = new LinkedLinkedListWithoutTail<>();
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue("New list should be empty", integerList.isEmpty());
        
        integerList.pushFront(100);
        assertFalse("List with elements should not be empty", integerList.isEmpty());
        
        integerList.popFront();
        assertTrue("List after removing all elements should be empty", integerList.isEmpty());
    }
    
    @Test
    public void testPushFront() {
        // Test with integer
        integerList.pushFront(500);
        integerList.pushFront(250);
        integerList.pushFront(125);
        
        // Verify order by popping
        assertEquals("First element should be 125", Integer.valueOf(125), integerList.popFront());
        assertEquals("Second element should be 250", Integer.valueOf(250), integerList.popFront());
        assertEquals("Third element should be 500", Integer.valueOf(500), integerList.popFront());
    }
    
    @Test
    public void testPushBack() {
        // Test with characters
        charList.pushBack('a');
        charList.pushBack('b');
        charList.pushBack('c');
        
        // Check order by popping from front
        assertEquals("First character should be 'a'", Character.valueOf('a'), charList.popFront());
        assertEquals("Second character should be 'b'", Character.valueOf('b'), charList.popFront());
        assertEquals("Third character should be 'c'", Character.valueOf('c'), charList.popFront());
    }
    
    @Test
    public void testPopFront() {
        // Test with string
        stringList.pushFront("third");
        stringList.pushFront("second");
        stringList.pushFront("first");
        
        assertEquals("First pop should be 'first'", "first", stringList.popFront());
        assertEquals("Second pop should be 'second'", "second", stringList.popFront());
        assertEquals("Third pop should be 'third'", "third", stringList.popFront());
        assertTrue("List should be empty after popping all elements", stringList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopFrontEmptyList() {
        integerList.popFront(); // Should throw EmptyList exception
    }
    
    @Test
    public void testPopBack() {
        // Test with integer
        integerList.pushBack(10);
        integerList.pushBack(20);
        integerList.pushBack(30);
        
        assertEquals("First pop from back should be 30", Integer.valueOf(30), integerList.popBack());
        assertEquals("Second pop from back should be 20", Integer.valueOf(20), integerList.popBack());
        assertEquals("Third pop from back should be 10", Integer.valueOf(10), integerList.popBack());
        assertTrue("List should be empty after popping all elements", integerList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopBackEmptyList() {
        integerList.popBack(); // Should throw EmptyList exception
    }
    
    @Test
    public void testFind() {
        // Test with string
        stringList.pushBack("apple");
        stringList.pushBack("banana");
        stringList.pushBack("cherry");
        
        Node<String> node = stringList.find("banana");
        assertNotNull("Should find existing element", node);
        assertEquals("Found node should have correct value", "banana", node.getValue());
        
        Node<String> notFoundNode = stringList.find("grape");
        assertNull("Should return null for non-existing element", notFoundNode);
    }
    
    @Test(expected = EmptyList.class)
    public void testFindEmptyList() {
        stringList.find("test"); // Should throw EmptyList exception
    }
    
    @Test
    public void testErase() {
        // Test with character
        charList.pushBack('x');
        charList.pushBack('y');
        charList.pushBack('z');
        
        charList.erase('y');
        
        assertNull("Erased element should not be found", charList.find('y'));
        assertNotNull("Other elements should still be there", charList.find('x'));
        assertNotNull("Other elements should still be there", charList.find('z'));
    }
    
    @Test(expected = EmptyList.class)
    public void testEraseEmptyList() {
        charList.erase('a'); // Should throw EmptyList exception
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testEraseNonExistingElement() {
        charList.pushBack('a');
        charList.erase('b'); // Should throw NoSuchElementException
    }
    
    @Test
    public void testAddAfter() {
        // Test with integer
        integerList.pushBack(10);
        integerList.pushBack(30);
        
        Node<Integer> node = integerList.find(10);
        integerList.addAfter(node, 20);
        
        // Check order by popping
        assertEquals("First element should be 10", Integer.valueOf(10), integerList.popFront());
        assertEquals("Second element should be 20", Integer.valueOf(20), integerList.popFront());
        assertEquals("Third element should be 30", Integer.valueOf(30), integerList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddAfterNullNode() {
        integerList.addAfter(null, 10); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testAddBefore() {
        // Test with string
        stringList.pushBack("first");
        stringList.pushBack("third");
        
        Node<String> node = stringList.find("third");
        stringList.addBefore(node, "second");
        
        // Check order by popping
        assertEquals("First element should be 'first'", "first", stringList.popFront());
        assertEquals("Second element should be 'second'", "second", stringList.popFront());
        assertEquals("Third element should be 'third'", "third", stringList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddBeforeNullNode() {
        stringList.addBefore(null, "test"); // Should throw IllegalArgumentException
    }

}
