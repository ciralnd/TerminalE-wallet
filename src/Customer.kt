import java.io.File
import kotlin.system.exitProcess

// mewarisi kelas user
// mendeklarasikan kelas customer dengan memiliki 3 parameter yaitu nama, no telepon dan saldo awal
class Customer(name: String, phoneNumber: String,
               initialBalance: Double) :
                User(name, phoneNumber) {

    // Balance diset sebagai private untuk encapsulasi.
    // Mendeklarasikan balance yang akan menyimpan saldo tapcash
    private var balance: Double = initialBalance
    // Daftar untuk menyimpan riwayat transaksi dengan menggunakan mutablelist
    private val transactionHistory =
        mutableListOf<Transaction>()

    // Fungsi untuk menambahkan transaksi ke riwayat.
    private fun addTransaction(type: TransactionType, description: String) {
        transactionHistory.add(Transaction(type, description))

        // Menulis deskripsi transaksi ke file untuk log.
        File("transaction_history.txt").appendText(description + "\n")
    }

    // Fungsi untuk mendapatkan balance.
    fun getBalance(): Double = balance

    // Fungsi untuk menampilkan riwayat transaksi.
    fun showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            println("No transactions found.") // jika transaksi belum dilakukan
            return
        }
        println("Transaction History:") // jika transaksi sudah di lakukan
        transactionHistory.forEach {
            println(it.description)
            println("--------------------------------------------------")
        }
    }

    // Fungsi untuk top up TapCash.
    fun topUpTapCash() {
        try {
            print("Enter TapCash number: ") // memasukkan nomor kartu tapcash
            val tapCashNumber = readLine() ?: throw Exception("TapCash number required.")
            print("Enter amount to top up: ") // memasukkan jumlah yang ingin di isi
            val amount = readLine()?.toDoubleOrNull() ?: throw Exception("Invalid amount.")

            if (amount > balance) { // jika saldo tidak mencukupi maka transaksi gagal
                println("Insufficient funds for top up.")
                return
            }

            balance -= amount // jika saldo mencukupi, saldo awal akan berkurang dan
            // transaksi berhasil dan mencetak struk transaksi
            val receipt = """
                --------------------------------------------------
                Top Up Successful!
                Receipt:
                ${getUserInfo()}
                TapCash Number: $tapCashNumber
                Top Up Amount: IDR $amount
                Remaining Balance: IDR $balance
                --------------------------------------------------
            """.trimIndent()
            println(receipt)
            addTransaction(TransactionType.TOP_UP, receipt)
        } catch (e: Exception){
            println(e.message)
        }
    }

    // Fungsi untuk transfer uang ke pengguna lain.
    fun transfer() {
        try {
            print("Enter destination phone number: ") // input nomor telepon yang di tuju
            val destinationNumber = readLine() ?: throw Exception("Phone number required.")
            print("Enter amount to transfer: ") // input jumlah yang akan di transfer
            val amount = readLine()?.toDoubleOrNull() ?: throw Exception("Invalid amount.")

            if (amount > balance) { // jika saldo tidak mencukupi maka transaksi gagal
                println("Insufficient funds for transfer.")
                return
            }

            balance -= amount // jika saldo mencukupi, saldo awal akan berkurang dan
            // transaksi berhasil dan mencetak struk transaksi
            val receipt = """
                --------------------------------------------------
                Transfer Successful!
                Receipt:
                ${getUserInfo()}
                Destination Number: $destinationNumber
                Transfer Amount: IDR $amount
                Remaining Balance: IDR $balance
                --------------------------------------------------
            """.trimIndent()
            println(receipt)
            addTransaction(TransactionType.TRANSFER, receipt)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    // Fungsi untuk menjalankan menu utama.
    fun runMainMenu() {
        var choice: Int // input pengguna harus bertipe data integer
        do {
            println("\nMenu:")
            println(getUserInfo())
            println("Balance: IDR ${getBalance()}")
            println("1. Top Up TapCash")
            println("2. Transfer")
            println("3. View Transaction History")
            println("4. Exit")
            print("Choose an option: ")
            choice = readLine()?.toIntOrNull() ?: 4

            when (choice) {
                1 -> topUpTapCash()
                2 -> transfer()
                3 -> showTransactionHistory()
                4 -> exitProcess(0)
                else -> println("Invalid option, please try again.")
            }
        } while (choice != 4)
    }
}



