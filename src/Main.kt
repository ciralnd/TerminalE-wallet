fun main() {
    print("Enter your name: ") // input nama pengguna
    val name = readLine() ?: ""
    print("Enter your phone number: ")  // input nomor telepon customer
    val phoneNumber = readLine() ?: ""

    // membuat objek baru yaitu user dari kelas customer
    // saldo awal adalah Rp. 100,000
    val user = Customer(name,
        phoneNumber, 100000.0)
    // Memulai loop menu utama
    user.runMainMenu()
}

