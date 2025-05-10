package tester.model.concrete;

    import tester.model.Abstract.LinkedList;

    public class LinkedListWithoutTail<T> implements LinkedList<T> {

        private Node<T> head;
        private int size;

        public LinkedListWithoutTail() {
            head = null;
            size = 0;
        }

        /**
         * Adds a new node with the given value to the front of the list.
         * Time complexity: O(1)
         *
         * @param value the value to add to the front of the list
         */
        @Override
        public void pushFront(T value) {
            Node<T> newNode = new Node<>(value);
            if (size > 0) {
                newNode.next = head;
            }

            head = newNode;
            size++;
        }

        /**
         * Adds a new node with the given value to the back of the list.
         * Time complexity: O(N) because it needs to traverse the list to find the last node.
         *
         * @param value the value to add to the back of the list
         */
        @Override
        public void pushBack(T value) {
            if (size == 0) {
                pushFront(value);
                return;
            }

            Node<T> newNode = new Node<>(value);
            Node<T> current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
            size++;
        }

        /**
         * Removes the node from the front of the list and returns its value.
         * Time complexity: O(1)
         *
         * @return the value of the node removed from the front, or null if the list is empty
         */
        @Override
        public T popFront() {
            if (size == 0) {
                return null;
            }
            T data = head.data;
            head = head.next;

            size--;
            return data;
        }

        /**
         * Removes the node from the back of the list and returns its value.
         * Time complexity: O(N) because it needs to traverse the list to find the second-to-last node.
         *
         * @return the value of the node removed from the back, or null if the list is empty
         */
        @Override
        public T popBack() {
            if (size == 0) {
                return null;
            }
            if (size == 1) {
                return popFront();
            }

            Node<T> prev = head;
            while (prev.next != null && prev.next.next != null) {
                prev = prev.next;
            }

            Node<T> lastNode = prev.next;
            T data = lastNode.data;
            prev.next = null;
            size--;

            return data;
        }

        /**
         * Finds the first node containing the specified value.
         * Time complexity: O(N) in the worst case (value is at the end or not present).
         *
         * @param value the value to search for
         * @return the Node containing the value, or null if the value is not found in the list
         */
        @Override
        public Node<T> find(T value) {
            Node<T> current = head;
            while (current != null) {
                if (current.data.equals(value)) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }

        /**
         * Removes the first occurrence of the node with the specified value from the list.
         * Time complexity: O(N) in the worst case.
         *
         * @param value the value to remove from the list
         */
        @Override
        public void erase(T value) {
            if (size == 0) {
                return;
            }

            if (head.data.equals(value)) {
                popFront();
                return;
            }

            Node<T> current = head;

            while (current.next != null) {
                if (current.next.data.equals(value)) {
                    current.next = current.next.next;
                    size--;
                    return;
                }
                current = current.next;
            }
        }

        /**
         * Adds a new node with `valueToAdd` immediately before the first node containing `valueToFind`.
         * If `valueToFind` is not found, the list remains unchanged.
         * Time complexity: O(N) in the worst case.
         *
         * @param node the value of the node before which the new node should be added
         * @param valueToAdd  the value to add to the list
         */
        @Override
        public void addBefore(Node<T> node, T valueToAdd) {
            if (size == 0) {
                return;
            }

            if (head.equals(node)) {
                pushFront(valueToAdd);
                return;
            }

            Node<T> current = head;
            while (current.next != null && !current.next.equals(node)) {
                current = current.next;
            }

            if (current.next != null) {
                Node<T> newNode = new Node<>(valueToAdd);
                newNode.next = current.next;
                size++;
            }
        }

        /**
         * Adds a new node with `valueToAdd` immediately after the first "node".
         * If `valueToFind` is not found, the list remains unchanged.
         * Time complexity: O(1).
         *
         * @param node the value of the node after which the new node should be added
         * @param valueToAdd  the value to add to the list
         */
        @Override
        public void addAfter(Node<T> node, T valueToAdd) {
            Node<T> newNode = new Node<>(valueToAdd);
            newNode.next = node.next;
            node.next = newNode;
            size++;
        }
    }