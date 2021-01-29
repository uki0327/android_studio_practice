package kr.ukinas.kotlindemo.classesdemo

interface HumanActions {
    fun eat() {
        println("I'm Human and I'm eating")
    }

    fun walk()
}

interface EmployeeActions {
    fun work()
    fun takeVacation()
}

// Abstract class
abstract class Human() {
    abstract var firstName: String
    abstract var lastName: String
    abstract fun printDetails()

    fun fullName(): String {
        return "First Name: $firstName, Last Name: $lastName"
    }
}

class Employee(override var firstName: String, override var lastName: String) :
        Human(), HumanActions, EmployeeActions {

    override fun printDetails() {
        println("Employee Details")
        println("-------------------------")
        println("First Name: $firstName")
        println("Last Name: $lastName")
        println(fullName())
        println("\n\n")
    }

    override fun eat() {
        println("I'm eating but from the Employee class, no HumanActions")
    }

    override fun walk() {
        TODO("Not yet implemented")
    }

    override fun work() {
        TODO("Not yet implemented")
    }

    override fun takeVacation() {
        TODO("Not yet implemented")
    }
}

class Student(override var firstName: String, override var lastName: String) : Human() {

    override fun printDetails() {
        println("Student Details")
        println("-------------------------")
        println("First Name: $firstName")
        println("Last Name: $lastName")
        println(fullName())
        println("\n\n")
    }
}

fun main() {
    val john = Employee("John", "Doe")
    john.printDetails()
    john.eat()
}


//open class Person(open var firstName: String = "First Name", open var lastName: String = "Last Name") {
//    open var fullName: String = "$firstName $lastName"
//
//    open fun printDetails() {
//        println("First name: $firstName")
//        println("Last name: $lastName")
//        println("Full name: $fullName")
//    }
//
//    fun run() {
//        println("$fullName is runnint")
//    }
//
//    fun walk() {
//        println("$fullName is walking")
//    }
//}
//
//class InheritingStudent(firstName: String, lastName: String,
//                        private var listOfCourses: MutableList<String> = mutableListOf<String>() )
//    : Person(firstName = firstName, lastName = lastName) {
//
//    override var firstName = firstName.capitalize()
//    override var lastName = lastName.capitalize()
//    override var fullName: String = "${this.firstName} ${this.lastName}"
//
//    override fun printDetails() {
//        super.printDetails()
//        if (listOfCourses.isNotEmpty()) println("Enrolled in: $listOfCourses")
//        else println("not enrolled in any courses")
//
//        println("--------\n")
//    }
//}
//
//fun main() {
//    val sampleListCourses = mutableListOf<String>("Computer Science", "Statistics", "Psychology")
//
//    //val john = InheritingStudent("john", "doe", sampleListCourses)
//    val john = InheritingStudent("john", "doe")
//    john.printDetails()
//    john.walk()
//    john.run()
//}