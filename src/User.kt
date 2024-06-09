// "Open class user" yang berarti kelas "User "dapat membuat child class
// yang juga akan mewariskan properti pada kelas ini, terdapat 2 properti yaitu "Name" dan "PhoneNumber"
// dengan tipe data string dan dapat null

open class User(open val name: String, open val phoneNumber: String) {
    // Fungsi untuk mendapatkan informasi pengguna.
    fun getUserInfo(): String = "Name: $name, Phone Number: $phoneNumber"
}

// METHOD getUserInfo() akan mendapatkan serta mengembalikan nilai "Name" dan "Phone number" dengan tipe data string.