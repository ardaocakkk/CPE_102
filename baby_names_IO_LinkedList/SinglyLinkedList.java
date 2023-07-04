public class SinglyLinkedList<E>{
    private Node<E> head;
    private int size;

    //Constructor
    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    //Inner Class
    static class Node<E> {
        private E data;
        private Node<E> tail; // Tail means next node

        //Inner Class Constructor
        public Node(E data) {
            this.data = data;
            this.tail = null;
        }

        //Getters and Setters
        public E getData() {
            return this.data;
        } //For example it returns PopularName object

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getTail() {
            return this.tail;
        }

        public void setTail(Node<E> tail) {
            this.tail = tail;
        }
    }

    /*
     * This method is used to insert a node in order.
    */
    public void add(E data) {
        //I have to use this if statement because of
        //If the given data is not a PopularName object, then it will give type casting error.
        if (data instanceof PopularName) {
            insertInOrder();
        }

        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            //This is how we traverse a linked list.
            while (current.getTail() != null) {
                current = current.getTail();
            }
            current.setTail(newNode);
        }
        size++;
    }

    /*
     * This method is used to add a node at a given index.
     * @param index The index of the node to be added.
     * @param data The data of the node to be added.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public void add(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(data);
        if (index == 0) {
            newNode.setTail(head);
            head = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getTail();
            }
            newNode.setTail(current.getTail());
            current.setTail(newNode);
        }
        size++;
    }


    /*
     * This method is used to remove a node at a given index.
     * @param index The index of the node to be removed.
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.getTail();
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getTail();
            }
            current.setTail(current.getTail().getTail());
        }
        size--;
    }

    /*
     * This method is used to get the data of a node at a given index.
     * @param index The index of the node to be returned.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getTail();
        }
        return current.getData();
    }

    /*
     * This method is used to get the index of a given data.
     * @param obj The data to be searched.
     */
    public int indexOf(E obj) {
        Node<E> current = head;
        int index = 1;
        while (current != null) {
            if (current.getData().equals(obj)) {
                return index;
            }
            current = current.getTail();
            index++;
        }
        //If not found in the list, return -1;
        return -1;
    }

    /*
     * This method is used to set the data of a node at a given index.
     * @param index The index of the node to be set.
     * @param data The data to be set.
     */
    public void set(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            //Traverse the list until the given index.
            //For example 0 is head of the linked list.
            //If index is 2, then it will traverse 2 times.
            current = current.getTail();
        }
        E temp = current.getData();
        current.setData(data);
    }

    //Return size of the list
    public int size() {
        return size;
    }


    public String toString() {
        String result = "";
        Node<E> current = head;
        while (current != null) {
            result += current.getData() + "\n";
            current = current.getTail();
        }
        return result;
    }

    /*
     * This method is used to insert the PopularName objects in alphabetical order.
     * It is used in add() method to sort the list each insertion.
     */
    public void insertInOrder() {

        Node<PopularName> current = (Node<PopularName>) head; //Type casting for call the getName() method of current node.
        Node<PopularName> index = null;
        PopularName temp;

        if (head == null) {
        } else {
            while (current != null) {
                //Node index will point to node next to current
                index = current.getTail();

                while (index != null) {
                    /*
                        * If the current node's PopularName object's name comes before the index node's name
                        * then it returns -1
                        * If the current node's PopularName object's name comes after the index node's name
                        * then it returns 1
                        * if they are same then return 0
                     */
                    if (current.getData().getName().compareTo(index.getData().getName()) > 0) {
                        //Swapping the data of current node and index node.
                        temp = current.getData(); // It's a PopularName object.
                        current.setData(index.getData()); //Change the data of current node with index node's data.
                        index.setData(temp); // Change the data of index with current's first data.
                    }
                    //Move to the next node.
                    index = index.getTail();
                }
                //Move to the next node.
                current = current.getTail();
            }
        }
    }

}
