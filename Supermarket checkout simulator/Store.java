/**
 * Shopping cart checkout simulator
 *
 * @version $Id: Store.java,v 1.1 2014/09/14 07:21:46
 *
 * @author Shraddha Atrawalkar
 *
 *         Revisions: Initial revision
 *
 */

// abstract class for class bag and cart
abstract class bag_cart {

    Item[] all_items = new Item[6];// public array for all items

    abstract void add_items(Item item_obj);// abstract method for adding items
}

// abstract class for reciept
abstract class a_reciept {
    public cashier cash = new cashier();
    public Item item = new Item();
    public String list_item;
    public int index;
    public double price;
    public double qty;

    abstract void print();
}

/**
 * This class will print the final receipt of items purchased by the customer
 *
 */
class reciept extends a_reciept {

    public void print() {
        for (int i = 0; i < Item.item_no; i++) {
            list_item = Item.item_desc[i];
            qty = 0;
            for (int j = 0; j < cash.j; j++) {
                if (cash.items[j] == list_item) {
                    index = j;
                    price = price + (Item.item_price[i] * cash.totals[j]);
                    qty = qty + cash.totals[j];
                }
            }
            if (qty != 0) {
                System.out.println(Item.item_desc[i] + ":" + qty + "\t("
                        + Item.item_price[i] + ")");
            }
        }
        System.out.println("-------------------------");
        System.out.println("cost:" + price);
    }
}

// abstract class for cashier
abstract class a_cashier {
    public static String[] items = new String[100];
    public static double[] totals = new double[100];
    public static int j;

    abstract public void compute(customer cust);
}

/**
 * This class computes the total number of items for each customer
 *
 */
class cashier extends a_cashier {

    public void compute(customer cust) {

        cart cart1 = new cart();

        for (int i = 0; i < cust.cart_no; i++) {
            for (j = 0; j < cart1.bag; j++) {
                items[j] = cust.cust[i].cart_bag[j].all_items[0].desc;
                totals[j] = cust.cust[i].cart_bag[j].all_items[0].qty;
            }
            for (int k = 0; k < cart1.item; k++) {
                items[j] = cust.cust[i].all_items[k].desc;
                totals[j] = cust.cust[i].all_items[k].qty;
                j++;
            }

        }
    }
}

// abstract class for the customer
abstract class a_customer {
    public cart[] cust = new cart[2];
    public int cart_no = 0;

    abstract public void add_cart(cart cart_obj);
}

/**
 * This class allows the customer to add a cart to purchase items
 *
 */
class customer extends a_customer {

    public void add_cart(cart cart_obj) {

        if (cart_no < 2) {
            cust[cart_no] = cart_obj;
            cart_no++;
        } else
            System.out.println("only 2 cart allowed per Customer");
    }
}

/**
 * This class allows the customer to add items on their own and items in a bag
 *
 */
class cart extends bag_cart {

    public Bag[] cart_bag = new Bag[6];
    public static int bag = 0;
    public static int item = 0;
    public static int count_additions;

    public void add_bag(Bag cart_bag_obj) {
        cart_bag[bag] = cart_bag_obj;
        bag++;
        count_additions++;
    }

    public void add_items(Item cart_item_obj) {
        all_items[item] = cart_item_obj;
        item++;
        count_additions++;
    }
}

/**
 * This class lets the customer purchase new items (at most 100)
 *
 */
class Bag extends bag_cart {

    // public Item[] all_items = new Item[6];
    public int count = 0;
    public double tot_qty;

    public void add_items(Item item_obj) {
        tot_qty = item_obj.qty;
        if (tot_qty <= 100) {
            all_items[count] = item_obj;
            count++;
        }
    }

}

// abstract class for each item
abstract class a_Item {
    public double qty;
    public String desc;
    public static double[] item_price = new double[6];
    public static String[] item_desc = new String[6];
    public static int item_no = 0;

    abstract void set_price(String desc, double price);

    abstract void create_items(double qty, String item);
}

/**
 * This class creates new items added by the customer and maintains their
 * description,price and quantity
 *
 */
class Item extends a_Item {

    public void set_price(String desc, double price) {
        item_price[item_no] = price;
        item_desc[item_no] = desc;
        item_no++;
    }

    public void create_items(double qty, String item) {
        this.qty = qty;
        desc = item;
    }
}

/**
 * This class will create and add items to the shopping cart,
 * compute the total amount for items purchased and print the receipt.
 *
 */
public class Store {

    public static void main(String[] args) {
        // ToDo auto-generated method stub
        // Set the price for each item
        Item item1 = new Item();
        item1.set_price("apple", 0.50);
        item1.set_price("flour", 0.70);
        item1.set_price("kiwi", 0.70);
        item1.set_price("orange", 0.70);
        item1.set_price("milk", 0.33);

        // Get a Cart
        cart cart1 = new cart();

        // a new customer
        customer cust = new customer();

        // Take 4 apple
        Item apple1 = new Item();
        apple1.create_items(4, "apple");
        // add item to cart
        cart1.add_items(apple1);

        // 4 kiwi in a bag
        Item kiwi1 = new Item();
        kiwi1.create_items(4, "kiwi");
        // add kiwi to bag
        Bag bag1 = new Bag();
        bag1.add_items(kiwi1);
        // add bag to cart
        cart1.add_bag(bag1);

        // add 2 kiwi
        Item kiwi2 = new Item();
        kiwi2.create_items(2, "kiwi");
        // add kiwi to cart
        cart1.add_items(kiwi2);

        // add 5 orange
        Item orange1 = new Item();
        orange1.create_items(5, "orange");
        // add orange to cart
        cart1.add_items(orange1);

        // add 4 orange
        Item orange2 = new Item();
        orange2.create_items(4, "orange");
        Bag bag2 = new Bag();
        // add orange to bag
        bag2.add_items(orange2);
        // add to cart
        cart1.add_bag(bag2);

        // add 3 apple
        Item apple3 = new Item();
        apple3.create_items(2, "apple");
        Bag bag3 = new Bag();
        // add orange to bag
        bag3.add_items(apple3);
        // add to cart
        cart1.add_bag(bag3);

        // add 1 carton of milk
        Item milk = new Item();
        milk.create_items(1, "milk");
        cart1.add_items(milk);
        cust.add_cart(cart1);

        // Compute the totals
        cashier cash = new cashier();
        cash.compute(cust);

        // Get the receipt
        reciept recp = new reciept();
        recp.print();
    }

}
