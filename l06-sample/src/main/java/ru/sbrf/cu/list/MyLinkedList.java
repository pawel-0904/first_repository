package ru.sbrf.cu.list;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T>, MyQueue<T> {
    private MyListItem<T> head = null;
    private MyListItem<T> tail = null;
    private int size = 0;


    //TODO
    @Override
    public void add( T item ) {
        // Создаем новый элемент
        MyListItem<T> internalItem = new MyListItem<>(); //
         //MyListItem<T> internalItem = null;
        internalItem.value = item;
        //Если голова ещё не задана - то её присваиваем новому элементу
        if ( head == null ) {
            head = internalItem;
        }
        // Хвост заменяется на новый
        MyListItem<T> tempTail = tail;
        //System.out.println(tempTail);
        tail = internalItem;
        //System.out.println(tail);
        // Связываем старый хвост и новый хвост между собой
        if ( tempTail != null ) {
            tempTail.next = tail;
        }
        tail.prev = tempTail;
        // Двигаемся по счетчику
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get( int index ) {
        // TODO Ошибка если индекс больше размера
        if ( index > size() ) throw new MyListException();
        // TODO Если индекс > size/2 - то перебираем от tail
        T result = null;
        int currPosition;
        MyListItem<T> item = null;
        if ( index > size() / 2) {
            currPosition = size() - 1;
            item = tail;
            // Пошли перебирать элементы c хвоста к носу,пока не дойдем до индекса или же не дойдем до первого/нулевого элемента
            while ( result == null && currPosition >= 0 ) {
                if ( index == currPosition ) {
                    result = item.value;
                }
                item = item.prev;
                currPosition--;
            }
        }
        else {
            currPosition = 0;
            item = head;
            // Пошли перебирать элементы пока не дойдем до индекса или же не выйдем за размеры списка
            while (result == null && currPosition < size) {
                if (index == currPosition) {
                    result = item.value;
                }
                item = item.next;
                currPosition++;
            }
        }
        return result;
    }

    @Override
    public boolean remove( T item ) {
        // TODO - реализовать.
        //пройтись по всем элементам, сравнить значения элементов с переданным item
        //если значение элмента равно значению item -> элемент удаляем/меняем связи (связываем следущий и предыдущий элементы между собой)
        //уменьшаем размер
        MyListItem<T> element = head;
        MyListItem<T> prevElement;
        MyListItem<T> nextElement;
        int currPosition = 0;

        while (size > currPosition) {
            if (element.value == item) {
                prevElement = element.prev;
                nextElement = element.next;
                // Если item не находится в конце или в начале, то связываем элемент перед item и после item
                if ( prevElement != null && nextElement != null ){
                    prevElement.next = nextElement;
                    nextElement.prev = prevElement;
                }
                //Если удаляем первый элемент (head) в списке, то обновляем head и у следующего элемента зануляем prev
                if (prevElement == null) {
                    nextElement.prev = null;
                    head = nextElement;
                }
                //Если удаляем последний элемент (tail) в списке, то обновляем tail и у предыдущего элемента зануляем next
                if (nextElement == null) {
                    prevElement.next = null;
                    tail = prevElement;
                }
                size--;
                return true;
            }
            element = element.next;
            currPosition++;
        }
        return false;
    }

    @Override
    public T pull() {
        //TODO реализовать
        MyListItem<T> element = head;
        if (head != null) {
            if (element.next != null) {
                element.next.prev = null;
            }
            head = element.next;
            size--;
            return element.value;
        }
        return null;
    }

    @Override
    public void sort() {
        boolean wasChange = true;
        while ( wasChange ){
            wasChange = false;
            MyListItem<T> first = head;
            MyListItem<T> second = head.next;
            while ( second != null ){
                wasChange = wasChange || compareAndReplaceItem(first, second);
                first = second;
                second = second.next;
            }
        }
    }


    @Override
    public void selectionSort() {
        //проходим все элементы, берем наименьший и отправляем в начало
        //теперь стартуем со следующего элемента, находим наименьший и ... повторяем, что было в строчке выше ...
        //повторяем вторую строчку пока не кончатся элементы
        MyListItem<T> tempMinItem = head;
        MyListItem<T> minItem = head;
        MyListItem<T> second;
        for (int i = 0; i < size; i++ )
        {
            second = tempMinItem.next;
            while ( second != null ) {
                if (second.value.compareTo( tempMinItem.value ) < 0) {
                    minItem = second;
                }
                second = second.next;
            }
            tempMinItem = makeMinItemFirst(minItem, tempMinItem, i);
            minItem = tempMinItem;
        }
    }

    private MyListItem<T> makeMinItemFirst (MyListItem<T> minItem, MyListItem<T> tempMinItem, int i) {
        MyListItem<T> checkMinItem;
        //если tempMinItem != minItem, то ставим minItem перед tempMinItem, обновляем связи
        if (tempMinItem != minItem) {
            //связываем предыдущий и следующий элементы от minItem после его удаления со старого места и переноса в новое
            if (minItem.next != null) {
                minItem.prev.next = minItem.next;
                minItem.next.prev = minItem.prev;
            }
            else {
                minItem.prev.next = null;
            }
            //меняем minItem и tempMinItem местами, обновляем связи
            minItem.prev = tempMinItem.prev;
            if (minItem.prev != null) {
                minItem.prev.next = minItem;
            }
            tempMinItem.prev = minItem;
            minItem.next = tempMinItem;
            checkMinItem = tempMinItem;
        }
        else {
           checkMinItem = minItem.next; //на проверку минимальности среди оставшихся элементов в ряду отправляем следующий справа элемент от minItem
        }
        //так как знаем какую итерацию/шаг делаем, то на первом обновляем head, на последнем tail
        if (i == 0) {
            head = minItem;
        }
        if (i == size - 1) {
            tail = minItem;
        }

        return checkMinItem;
    }

    private boolean compareAndReplaceItem(MyListItem<T> first, MyListItem<T> second ) {
        if (second.value.compareTo( first.value ) < 0){
            second.prev = first.prev;
            if (second.prev == null){
                head = second;
            }
            else {
                first.prev.next = second;
            }
            first.next = second.next;
            if (first.next == null){
                tail = first;
            }
            else {
                second.next.prev = first;
            }
            second.next = first;
            first.prev = second;
            return true;
        }
        return false;
    }

    private class MyListItem<T> {
        T value; // поле класса
        MyListItem<T> prev = null;
        MyListItem<T> next;
    }
}