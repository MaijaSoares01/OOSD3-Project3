# OOSD3-Project3
OOSD3, Second Year, Semester 2, Assignment 3

Develop a program that models a simple restaurant with one chef and one waiter. Your program should have the following classes:
• A class Restaurant
• A class Chef (extends Thread)
• A class Waiter (extends Thread)
• A class Order
The restaurant class will need a collection such as an ArrayList to store orders. The waiter picks up an order from the collection and passes it to the 
Chef and starts the chef’s thread. Assume the preparation of a meal takes a fixed amount of time and when this elapses the chef’s thread notifies the waiter,
using notify(). While the meal is in preparation, the waiter waits for the meal, suing wait(). Note, wait() and notify() must be called within synchronised 
methods or blocks. Hint: Have one thread for the waiter that continues to run until all orders in the collection have been processed and have one other thread 
for the chef that continues to run until all orders in the collected have been prepared. Both of these threads should use wait() and notify() in synchronised 
blocks to communicate with each other. Create a GUI that will allow users to enter a dish of choice, which will be stored as an order object in the restaurant’s 
collection and then processed. Once the dish is prepared, the GUI will inform the user.
