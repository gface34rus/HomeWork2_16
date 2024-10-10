package pro.sky.HomeWork2_16;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.HomeWork2_16.exception.BadIndexException;
import pro.sky.HomeWork2_16.exception.ElementNotFoundException;
import pro.sky.HomeWork2_16.exception.FullStorageException;
import pro.sky.HomeWork2_16.exception.NullItemException;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private IntegerListImpl integerList;

    @BeforeAll
    static void setUp() {
        IntegerListImpl integerList = new IntegerListImpl(2);
    }


    @Test
    void testAddItem() {
        assertEquals(Integer.valueOf(5), integerList.add(5));
        assertEquals(1, integerList.size());
        assertTrue(integerList.contains(5));
    }

    @Test
    void testAddItemAtIndex() {
        integerList.add(10);
        integerList.add(20);
        assertEquals(Integer.valueOf(15), integerList.add(1, 15));
        assertEquals(Integer.valueOf(10), integerList.get(0));
        assertEquals(Integer.valueOf(15), integerList.get(1));
        assertEquals(Integer.valueOf(20), integerList.get(2));
        assertEquals(3, integerList.size());
    }

    @Test
    void testSetItem() {
        integerList.add(30);
        assertEquals(Integer.valueOf(40), integerList.set(0, 40));
        assertEquals(Integer.valueOf(40), integerList.get(0));
    }

    @Test
    void testRemoveItem() {
        integerList.add(50);
        assertEquals(Integer.valueOf(50), integerList.remove(Integer.valueOf(50)));
        assertFalse(integerList.contains(50));
        assertEquals(0, integerList.size());
    }

    @Test
    void testGetItem() {
        integerList.add(60);
        assertEquals(Integer.valueOf(60), integerList.get(0));
    }

    @Test
    void testClear() {
        integerList.add(70);
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    void testGrowCapacity() {

        integerList.add(1);
        integerList.add(2);


        assertEquals(2, integerList.size());


        integerList.add(3);


        assertEquals(3, integerList.size());


        assertEquals(Integer.valueOf(3), integerList.get(2));


        assertTrue(integerList.toArray().length >= 3);
    }

    // Negative Tests
    @Test
    void testAddNullItem() {
        Exception exception = assertThrows(NullItemException.class, () -> {
            integerList.add(null);
        });
        assertNotNull(exception);
    }

    @Test
    void testAddAtInvalidIndex() {
        Exception exception = assertThrows(BadIndexException.class, () -> {
            integerList.add(-1, 100);
        });
        assertNotNull(exception);

        exception = assertThrows(BadIndexException.class, () -> {
            integerList.add(2, 100);
        });
        assertNotNull(exception);
    }

    @Test
    void testSetAtInvalidIndex() {
        Exception exception = assertThrows(BadIndexException.class, () -> {
            integerList.set(-1, 200);
        });
        assertNotNull(exception);

        exception = assertThrows(BadIndexException.class, () -> {
            integerList.set(0, 200);
        });
        assertNotNull(exception);
    }

    @Test
    void testRemoveNonExistentItem() {
        Exception exception = assertThrows(ElementNotFoundException.class, () -> {
            integerList.remove(Integer.valueOf(-1));
        });
        assertNotNull(exception);
    }

    @Test
    void testGetAtInvalidIndex() {
        Exception exception = assertThrows(BadIndexException.class, () -> {
            integerList.get(-1);
        });
        assertNotNull(exception);

        exception = assertThrows(BadIndexException.class, () -> {
            integerList.get(0);
        });
        assertNotNull(exception);
    }

    @Test
    void testFullStorageException() {
        for (int i = 0; i < 10; i++) {
            integerList.add(i);
        }

        Exception exception = assertThrows(FullStorageException.class, () -> {
            integerList.add(100);
        });

        assertNotNull(exception);
    }

}