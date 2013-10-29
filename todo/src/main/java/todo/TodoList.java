package todo;

public class TodoList {

	public Item createItem(String title) {
		Item item = new Item(title);
		return item;
	}
}
