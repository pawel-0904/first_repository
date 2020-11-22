package ru.sbrf.cu.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Реализация MyList должна")
class MyListTest {
    MyList<Integer> list;
    MyQueue<Integer> queue;

    @BeforeEach
    public void beforeAll() {
        list = new MyLinkedList<>();
        list.add( 1 );
        list.add( 2 );
        list.add( 10 );
        //list.add( 20 );
        //list.add( 100 );
    }

    @DisplayName("добавлять элементы и корректно давать размер")
    @Test
    public void testAdd() {
        assertEquals( 3, list.size() );
    }

    @DisplayName("находить элемент по индексу")
    @Test
    public void testGet() {
        assertEquals( Integer.valueOf( 10 ), list.get( 2 ) );
    }

    @DisplayName("возвращать null если выходим за размер")
    @Test
    public void testGetWithNull() {
        assertNull( list.get( 5 ) );
    }

    @DisplayName("находить удалять по элемент по совпадению")
    @Test
    public void testRemoveFirst() {
        list.add( 2 );
        boolean result = list.remove( 10 );
        assertTrue( result );
        assertEquals( Integer.valueOf( 2 ), list.get( 2 ) );
        assertEquals( 3, list.size() );
    }

    @DisplayName("возвращать false если удаление по элементу не удалось")
    @Test
    public void testRemoveFalse() {
        boolean result = list.remove( 3 );
        assertFalse( result );
        assertEquals( 3, list.size() );
    }
    @DisplayName("возвращать первый элемент и удалять его из очереди")
    @Test
    public void testPull() {
        queue = new MyLinkedList<>();
        queue.add( 2 );
        queue.add( 1 );
        queue.add( 10 );
        queue.add( 6 );
        // 1,2,10,6
        assertEquals( Integer.valueOf( 1 ), queue.pull() );
        assertEquals( 3, queue.size() );
        assertEquals( Integer.valueOf( 2 ), queue.pull() );
        assertEquals( 2, queue.size() );
        assertEquals( Integer.valueOf( 10 ), queue.pull() );
        assertEquals( 1, queue.size() );
        assertEquals( Integer.valueOf( 6 ), queue.pull() );
        assertNull( queue.pull() );
    }


    @DisplayName("кооректно сортировать Integer")
    @Test
    public void testSort() {
        list.add( 6 );
        list.add( 4 );
        // 1,2,10,6,4
        // 2,1,10,6,4
        list.sort();

        assertEquals( 5, list.size() );
        assertEquals( Integer.valueOf( 1 ), list.get( 0 ) );
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ) );
        assertEquals( Integer.valueOf( 4 ), list.get( 2 ) );
        assertEquals( Integer.valueOf( 6 ), list.get( 3 ) );
        assertEquals( Integer.valueOf( 10 ), list.get( 4 ) );
    }

    @DisplayName("корректно сортировать Integer (селективная сортировка)")
    @Test
    public void testSortSelection() {
        list.add( 6 );
        list.add( 4 );
        list.add( 20 );
        // 1,2,10,6,4,20
        list.selectionSort();

        assertEquals( 6, list.size() );
        assertEquals( Integer.valueOf( 1 ), list.get( 0 ) );
        assertEquals( Integer.valueOf( 2 ), list.get( 1 ) );
        assertEquals( Integer.valueOf( 4 ), list.get( 2 ) );
        assertEquals( Integer.valueOf( 6 ), list.get( 3 ) );
        assertEquals( Integer.valueOf( 10 ), list.get( 4 ) );
        assertEquals( Integer.valueOf( 20 ), list.get( 5) );
    }
}


