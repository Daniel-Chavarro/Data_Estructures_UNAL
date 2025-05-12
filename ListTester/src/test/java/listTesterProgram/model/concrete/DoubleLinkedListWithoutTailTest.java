package listTesterProgram.model.concrete;

import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.exceptions.EmptyList;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DoubleLinkedListWithoutTailTest {
    
    private LinkedList<Float> floatList;
    private LinkedList<Long> longList;
    private LinkedList<Character> charList;
    
    @Before
    public void setUp() {
        floatList = new DoubleLinkedLinkedListWithoutTail<>();
        longList = new DoubleLinkedLinkedListWithoutTail<>();
        charList = new DoubleLinkedLinkedListWithoutTail<>();
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue("New list should be empty", floatList.isEmpty());
        
        floatList.pushFront(5.5f);
        assertFalse("List with elements should not be empty", floatList.isEmpty());
        
        floatList.popFront();
        assertTrue("List after removing all elements should be empty", floatList.isEmpty());
    }
    
    @Test
    public void testPushFront() {
        // Test with float
        floatList.pushFront(3.3f);
        floatList.pushFront(2.2f);
        floatList.pushFront(1.1f);
        
        // Verify order by popping
        assertEquals("First element should be 1.1", Float.valueOf(1.1f), floatList.popFront());
        assertEquals("Second element should be 2.2", Float.valueOf(2.2f), floatList.popFront());
        assertEquals("Third element should be 3.3", Float.valueOf(3.3f), floatList.popFront());
    }
    
    @Test
    public void testPushBack() {
        // Test with character
        charList.pushBack('x');
        charList.pushBack('y');
        charList.pushBack('z');
        
        // Verify order by popping
        assertEquals("First element should be x", Character.valueOf('x'), charList.popFront());
        assertEquals("Second element should be y", Character.valueOf('y'), charList.popFront());
        assertEquals("Third element should be z", Character.valueOf('z'), charList.popFront());
    }
    
    @Test
    public void testPopFront() {
        // Test with long
        longList.pushFront(300L);
        longList.pushFront(200L);
        longList.pushFront(100L);
        
        assertEquals("First pop should be 100", Long.valueOf(100L), longList.popFront());
        assertEquals("Second pop should be 200", Long.valueOf(200L), longList.popFront());
        assertEquals("Third pop should be 300", Long.valueOf(300L), longList.popFront());
        assertTrue("List should be empty after popping all elements", longList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopFrontEmptyList() {
        floatList.popFront(); // Should throw EmptyList exception
    }
    
    @Test
    public void testPopBack() {
        // Test with float
        floatList.pushBack(1.5f);
        floatList.pushBack(2.5f);
        floatList.pushBack(3.5f);
        
        assertEquals("First pop from back should be 3.5", Float.valueOf(3.5f), floatList.popBack());
        assertEquals("Second pop from back should be 2.5", Float.valueOf(2.5f), floatList.popBack());
        assertEquals("Third pop from back should be 1.5", Float.valueOf(1.5f), floatList.popBack());
        assertTrue("List should be empty after popping all elements", floatList.isEmpty());
    }
    
    @Test(expected = EmptyList.class)
    public void testPopBackEmptyList() {
        charList.popBack(); // Should throw EmptyList exception
    }
    
    @Test
    public void testFind() {
        // Test with long
        longList.pushBack(111L);
        longList.pushBack(222L);
        longList.pushBack(333L);
        
        Node<Long> node = longList.find(222L);
        assertNotNull("Should find existing element", node);
        assertEquals("Found node should have correct value", Long.valueOf(222L), node.getValue());
        
        try {
            longList.find(999L);
            fail("Should throw NoSuchElementException for non-existing element");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
    }
    
    @Test(expected = EmptyList.class)
    public void testFindEmptyList() {
        longList.find(100L); // Should throw EmptyList exception
    }
    
    @Test
    public void testErase() {
        // Test with character
        charList.pushBack('a');
        charList.pushBack('b');
        charList.pushBack('c');
        
        charList.erase('b');
        
        try {
            charList.find('b');
            fail("Should throw NoSuchElementException for erased element");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
        
        assertNotNull("Other elements should still be there", charList.find('a'));
        assertNotNull("Other elements should still be there", charList.find('c'));
    }
    
    @Test(expected = EmptyList.class)
    public void testEraseEmptyList() {
        floatList.erase(1.0f); // Should throw EmptyList exception
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testEraseNonExistingElement() {
        floatList.pushBack(1.0f);
        floatList.erase(2.0f); // Should throw NoSuchElementException
    }
    
    @Test
    public void testAddAfter() {
        // Test with float
        floatList.pushBack(1.1f);
        floatList.pushBack(3.3f);
        
        Node<Float> node = floatList.find(1.1f);
        floatList.addAfter(node, 2.2f);
        
        // Check order by popping
        assertEquals("First element should be 1.1", Float.valueOf(1.1f), floatList.popFront());
        assertEquals("Second element should be 2.2", Float.valueOf(2.2f), floatList.popFront());
        assertEquals("Third element should be 3.3", Float.valueOf(3.3f), floatList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddAfterNullNode() {
        floatList.addAfter(null, 1.0f); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testAddBefore() {
        // Test with long
        longList.pushBack(100L);
        longList.pushBack(300L);
        
        Node<Long> node = longList.find(300L);
        longList.addBefore(node, 200L);
        
        // Check order by popping
        assertEquals("First element should be 100", Long.valueOf(100L), longList.popFront());
        assertEquals("Second element should be 200", Long.valueOf(200L), longList.popFront());
        assertEquals("Third element should be 300", Long.valueOf(300L), longList.popFront());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddBeforeNullNode() {
        longList.addBefore(null, 100L); // Should throw IllegalArgumentException
    }

}
