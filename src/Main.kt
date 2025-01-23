fun main() {
    // 11. Демонстрация работы с классом "Магазин"
    println("11 === Магазин ===")
    val store = Store()
    store.addProduct(Product("Apple", 1.0, 50))
    store.addProduct(Product("Banana", 0.5, 100))
    println("Найденный продукт: ${store.findProductByName("Apple")}")
    store.removeProduct("Banana")
    println("Продукты в магазине после удаления: ${store.findProductByName("Banana")}")

    // 12. Демонстрация работы с платежной системой
    println("\n12 === Платежная система ===")
    val creditCard = CreditCard()
    creditCard.pay(100.0)
    creditCard.refund(50.0)
    val paypal = PayPal()
    paypal.pay(200.0)
    paypal.refund(150.0)

    // 13. Генерация уникальных идентификаторов
    println("\n13 === Уникальные идентификаторы ===")
    println("ID 1: ${UniqueID.generateID()}")
    println("ID 2: ${UniqueID.generateID()}")

    // 14. Работа с прямоугольником
    println("\n14 === Прямоугольник ===")
    val rectangle = Rectangle(Point(0.0, 10.0), Point(5.0, 0.0))
    println("Площадь прямоугольника: ${rectangle.area()}")

    // 15. Комплексные числа
    println("\n15 === Комплексные числа ===")
    val c1 = ComplexNumber(3.0, 4.0)
    val c2 = ComplexNumber(1.0, 2.0)
    println("Сумма: ${c1 + c2}")
    println("Разность: ${c1 - c2}")
    println("Произведение: ${c1 * c2}")
    println("Частное: ${c1 / c2}")

    // 16. Работа с матрицами
    println("\n16 === Матрицы ===")
    val matrix1 = Matrix(2, 2, arrayOf(arrayOf(1, 2), arrayOf(3, 4)))
    val matrix2 = Matrix(2, 2, arrayOf(arrayOf(5, 6), arrayOf(7, 8)))
    println("Сложение матриц: ${matrix1 + matrix2}")
    println("Умножение матриц: ${matrix1 * matrix2}")

    // 17. Текстовая игра
    println("\n17 === Текстовая игра ===")
    val weapon = Weapon("Sword", 10)
    val player = Player("Hero", 100, weapon)
    val enemy = Enemy("Goblin", 50)
    player.attack(enemy)

    // 18. Система заказов
    println("\n18 === Система заказов ===")
    val customer = Customer("Alice")
    val order = Order(1, listOf(Product("Laptop", 1000.0, 1), Product("Mouse", 25.0, 1)))
    customer.placeOrder(order)
    println("Общая стоимость заказа: ${order.totalCost()}")
    println("История заказов: ${customer.getOrderHistory()}")

    // 19. Наследование: Электроника
    println("\n19 === Электроника ===")
    val smartphone = Smartphone("Samsung")
    smartphone.turnOn()
    smartphone.takePhoto()
    smartphone.turnOff()
    val laptop = Laptop("Dell")
    laptop.turnOn()
    laptop.compileCode()
    laptop.turnOff()

    // 20. Игра "Крестики-нолики"
    println("\n20 === Игра 'Крестики-нолики' ===")
    val player1 = TicTacToePlayer("Player1", 'X')
    val player2 = TicTacToePlayer("Player2", 'O')
    val game = TicTacToeGame()
    game.play(player1, 0, 0)
    game.play(player2, 1, 1)
    game.play(player1, 0, 1)
    game.play(player2, 1, 0)
    game.play(player1, 0, 2) // Player1 победил
    println("Победитель: ${game.checkWinner() ?: "Нет победителя"}")
}


// 11. Класс "Магазин"
data class Product(val name: String, val price: Double, var quantity: Int)

class Store {
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(productName: String) {
        products.removeIf { it.name == productName }
    }

    fun findProductByName(name: String): Product? {
        return products.find { it.name == name }
    }
}

// 12. Интерфейс "Платежная система"
interface PaymentSystem {
    fun pay(amount: Double)
    fun refund(amount: Double)
}

class CreditCard : PaymentSystem {
    override fun pay(amount: Double) {
        println("Paid $amount using Credit Card")
    }

    override fun refund(amount: Double) {
        println("Refunded $amount to Credit Card")
    }
}

class PayPal : PaymentSystem {
    override fun pay(amount: Double) {
        println("Paid $amount using PayPal")
    }

    override fun refund(amount: Double) {
        println("Refunded $amount to PayPal")
    }
}

// 13. Генерация уникальных идентификаторов
class UniqueID {
    companion object {
        private var idCounter = 0
        fun generateID(): Int {
            return ++idCounter
        }
    }
}

// 14. Класс "Точка" и "Прямоугольник"
data class Point(val x: Double, val y: Double)

class Rectangle(val topLeft: Point, val bottomRight: Point) {
    fun area(): Double {
        val width = bottomRight.x - topLeft.x
        val height = topLeft.y - bottomRight.y
        return width * height
    }
}

// 15. Комплексные числа
data class ComplexNumber(val real: Double, val imaginary: Double) {
    operator fun plus(other: ComplexNumber) = ComplexNumber(real + other.real, imaginary + other.imaginary)
    operator fun minus(other: ComplexNumber) = ComplexNumber(real - other.real, imaginary - other.imaginary)
    operator fun times(other: ComplexNumber) = ComplexNumber(
        real * other.real - imaginary * other.imaginary,
        real * other.imaginary + imaginary * other.real
    )

    operator fun div(other: ComplexNumber): ComplexNumber {
        val denominator = other.real * other.real + other.imaginary * other.imaginary
        return ComplexNumber(
            (real * other.real + imaginary * other.imaginary) / denominator,
            (imaginary * other.real - real * other.imaginary) / denominator
        )
    }
}

// 16. Перегрузка операторов: Матрица
class Matrix(private val rows: Int, private val cols: Int, private val data: Array<Array<Int>>) {
    operator fun plus(other: Matrix): Matrix {
        val result = Array(rows) { Array(cols) { 0 } }
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                result[i][j] = data[i][j] + other.data[i][j]
            }
        }
        return Matrix(rows, cols, result)
    }

    operator fun times(other: Matrix): Matrix {
        val result = Array(rows) { Array(other.cols) { 0 } }
        for (i in 0 until rows) {
            for (j in 0 until other.cols) {
                for (k in 0 until cols) {
                    result[i][j] += data[i][k] * other.data[k][j]
                }
            }
        }
        return Matrix(rows, other.cols, result)
    }
}

// 17. Создание игры с использованием ООП
class Player(val name: String, var health: Int, val weapon: Weapon) {
    fun attack(enemy: Enemy) {
        println("$name attacks ${enemy.name} with ${weapon.name}")
        enemy.takeDamage(weapon.damage)
    }
}

class Enemy(val name: String, var health: Int) {
    fun takeDamage(damage: Int) {
        health -= damage
        println("$name took $damage damage. Remaining health: $health")
    }
}

class Weapon(val name: String, val damage: Int)

// 18. Автоматизированная система заказов
data class Order(val id: Int, val products: List<Product>) {
    fun totalCost(): Double = products.sumOf { it.price }
}

class Customer(val name: String) {
    private val orderHistory = mutableListOf<Order>()

    fun placeOrder(order: Order) {
        orderHistory.add(order)
    }

    fun getOrderHistory() = orderHistory
}

// 19. Наследование: Электроника
open class Device(val brand: String) {
    open fun turnOn() {
        println("$brand device is now ON")
    }

    open fun turnOff() {
        println("$brand device is now OFF")
    }
}

class Smartphone(brand: String) : Device(brand) {
    fun takePhoto() {
        println("$brand smartphone is taking a photo")
    }
}

class Laptop(brand: String) : Device(brand) {
    fun compileCode() {
        println("$brand laptop is compiling code")
    }
}

// 20. Игра "Крестики-нолики"
class TicTacToePlayer(val name: String, val symbol: Char)

class TicTacToeGame {
    private val board = Array(3) { CharArray(3) { ' ' } }

    fun play(player: TicTacToePlayer, row: Int, col: Int): Boolean {
        if (board[row][col] == ' ') {
            board[row][col] = player.symbol
            return true
        }
        return false
    }

    fun checkWinner(): Char? {
        for (i in 0..2) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') return board[i][0]
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') return board[0][i]
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') return board[0][0]
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') return board[0][2]
        return null
    }
}
