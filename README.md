# TaskBuddy Application
**Tugas Akhir Praktikum Pemrograman Lanjut SI-C Kelompok 5**

## Deskripsi Aplikasi
TaskBuddy adalah aplikasi manajemen tugas berbasis Command Line Interface (CLI) yang dirancang untuk membantu pengguna mengorganisir dan mengelola tugas-tugas mereka secara efisien. Aplikasi ini mendukung sistem hierarki tugas dengan kemampuan menambahkan tugas utama dan subtugas, serta dilengkapi dengan fitur pencarian, pengurutan, dan sistem logging aktivitas.

## Fitur Utama

### Sistem Autentikasi
- **Login & Registrasi**: Pengguna dapat membuat akun dan masuk menggunakan kombinasi username dan password yang aman.
- **Akses Berdasarkan Peran (Role-Based Access)**: Sistem mendukung dua jenis peran: Admin dan Member, masing-masing dengan hak akses berbeda.
- **Manajemen Antrian Pengguna**: Setiap pengguna yang login akan dimasukkan ke dalam antrian untuk pengelolaan akses secara teratur.

### Manajemen Tugas
- **Struktur Hirarki Tugas**: Tugas diorganisasi dalam bentuk pohon, memungkinkan penambahan tugas utama dan subtugas secara bertingkat.
- **Detail Tugas yang Lengkap**: Setiap tugas menyimpan informasi penting seperti nama, deskripsi, prioritas (Tinggi/Sedang/Rendah), deadline, dan status penyelesaian.
- **Operasi CRUD**: Pengguna dapat membuat, melihat, mengubah, dan menghapus tugas yang dimiliki.
- **Pelacakan Status Tugas**: Tugas dapat ditandai sebagai selesai untuk mempermudah manajemen dan progres.

### Pencarian & Pengurutan
- **Pencarian Linear**: Fitur pencarian memungkinkan pengguna mencari tugas berdasarkan nama atau deskripsi.
- **Pengurutan Otomatis**: Tugas dapat diurutkan berdasarkan prioritas dan deadline menggunakan algoritma Bubble Sort.
- **Filter Hasil yang Relevan**: Menampilkan hasil pencarian secara akurat sesuai kata kunci yang dimasukkan pengguna.

### Logging & History
- **Catatan Aktivitas Pengguna**: Setiap aksi yang dilakukan pengguna dicatat dalam log beserta waktu kejadian (timestamp).
- **Undo & Redo**: Pengguna dapat membatalkan atau mengulangi aktivitas terakhir dengan mudah.
- **Struktur Double Linked List**: Riwayat disimpan menggunakan struktur data double linked list untuk navigasi yang efisien ke depan dan ke belakang.

### User Management
- **Antrian Login**: Sistem mencatat urutan login pengguna dengan metode FIFO (First In, First Out).
- **Manajemen Anggota oleh Admin**: Admin memiliki kemampuan untuk menghapus member dari sistem.
- **Pemantauan Pengguna Aktif**: Admin dapat melihat dan memproses siapa saja yang sedang aktif dalam sistem.

## Arsitektur Sistem

### Struktur Data yang Digunakan
- **Tree Structure**: Untuk hierarki tugas (TaskTreeManager)
- **Double Linked List**: Untuk sistem undo/redo (LogHistory)
- **Queue**: Untuk antrian pengguna (UserQueueManager)
- **LinkedList**: Untuk penyimpanan dan operasi data

### Konsep OOP yang Diterapkan
- **Inheritance**: SimpleTask extends Task
- **Polymorphism**: Task sebagai interface untuk berbagai jenis tugas
- **Encapsulation**: Private attributes dengan getter/setter methods
- **Abstraction**: Abstract class Task sebagai blueprint

### Menu Utama
1. **Login ke Akun** - Masuk dengan akun yang sudah ada
2. **Registrasi Akun Baru** - Membuat akun baru (Admin/Member)
3. **Keluar dari Aplikasi** - Menutup aplikasi

### Menu Admin
[1] Tambah Tugas Utama
[2] Tambah Subtugas
[3] Lihat Semua Tugas
[4] Hapus Tugas
[5] Cari Tugas
[6] Urutkan Tugas
[7] Tandai Tugas Selesai
[8] Lihat Log Aktivitas
[9] Undo Aktivitas
[10] Redo Aktivitas
[11] Lihat Antrian User
[12] Proses User Berikutnya
[13] Hapus Member

### Menu Member
[1] Tambah Subtugas
[2] Lihat Semua Tugas
[3] Hapus Tugas
[4] Cari Tugas
[5] Urutkan Tugas
[6] Tandai Tugas Selesai
[7] Undo Aktivitas
[8] Redo Aktivitas
[9] Lihat Antrian User
[10] Proses User Berikutnya

## Struktur File

```
TaskBuddyApp/
├── TaskBuddyApp.java       # Pusat aplikasi tempat login, registrasi, dan menu utama dijalankan
├── Task.java               # Kelas abstrak dasar untuk representasi tugas
├── SimpleTask.java         # Implementasi nyata dari tugas (Task), berisi nama, deskripsi, deadline, dll.
├── TaskNode.java           # Node pohon yang mewakili tugas utama dan subtugas
├── TaskTreeManager.java    # Pengelola struktur pohon tugas dan operasi tambah/hapus/cari
├── TaskOperations.java     # Berisi algoritma pencarian dan pengurutan tugas
├── User.java              # Representasi satu pengguna (username, password, role)
├── UserManager.java       # Modul untuk registrasi, login, dan cek pengguna
├── UserQueueManager.java  # Mengelola antrian user yang sedang login
├── LogHistory.java        # Mencatat semua aktivitas penting selama penggunaan
├── LogNode.java          # Node untuk tiap entri log (undo/redo)
└── README.md             # Dokumentasi proyek
```

## Pembagian Tugas

### **Sabrina**
**Fokus: Struktur Tugas (Tree)**
- **TaskTreeManager.java**: Menangani seluruh operasi pohon tugas, seperti menambahkan tugas utama dan subtugas, mencari letak tugas, menghapus tugas, dan memperlihatkan struktur pohon tugas.
- **TaskNode.java**: Struktur pohon yang mewakili hubungan antar tugas dan subtugas.
- **Task.java**: Kelas abstrak sebagai blueprint untuk pembuatan tugas dan  mendeskripsikan data tugas secara umum.
- **SimpleTask.java**: Implementasi konkret dari Task, memanfaatkan inheritance dan polymorphism.
- **Fitur Utama**: Manajemen tugas secara hierarkis, traversal pohon, dan operasi CRUD pada tugas.

### **Mela** 
**Fokus: Log Perubahan (Double Linked List)**
- **LogHistory.java**: Mengelola riwayat aktivitas menggunakan double linked list yang mendukung fitur undo dan redo.
- **LogNode.java**: Struktur node log yang menyimpan informasi aktivitas beserta timestamp dan pointer navigasi.
- **Fitur Utama**: Pelacakan aktivitas pengguna, sistem undo/redo, dan navigasi histori aktivitas.
- **Algoritma**: Double linked list traversal untuk efficient undo/redo operations

### **Lingga** 
**Fokus: Antrian User (Queue)**
- **UserQueueManager.java**: Menyediakan sistem manajemen antrian pengguna berbasis FIFO.
- **Fitur Utama**: Menambahkan pengguna ke antrian saat berhasil login, menampilkan antrian, memproses pengguna berikutnya, dan menghapus dari antrian.
- **Algoritma**:Menggunakan struktur data LinkedList sebagai dasar implementasi queue.

### **Shabree** 
**Fokus: Sorting dan Searching**
- **TaskOperations.java**: Mengimplementasikan algoritma pencarian dan pengurutan untuk daftar tugas.
- **Linear Search**: Mencari tugas berdasarkan kata kunci pada nama atau deskripsi tugas.
- **Bubble Sort**: Mengurutkan tugas berdasarkan prioritas (dari paling tinggi ke paling rendah, dengan angka 1 sebagai prioritas tertinggi). Jika ada tugas dengan prioritas yang sama, maka akan diurutkan berdasarkan deadline terdekat.
- **Integrasi**: Digunakan oleh TaskBuddyApp melalui method searchTask() dan sortTasks(), serta method getTaskInfo() pada SimpleTask.java.

### **Hani** 
**Fokus: Manajemen Pengguna dan Peran**
- **UserManager.java**: Mengelola proses registrasi, login, penghapusan pengguna, serta validasi akun.
- **User.java**: Kelas yang merepresentasikan satu akun pengguna dalam sistem, berisi informasi username, password, dan role.
- **Fitur**: Sistem autentikasi, kontrol akses berdasarkan peran (Admin / User), dan validasi input pengguna.

## Format Input

### Tanggal
```
Format: YYYY-MM-DD
Contoh: 2024-12-25
```

### Prioritas
```
1 = High (Urgent)
2 = Medium (Normal)  
3 = Low (Tidak Urgent)
```

### Role User
```
admin  = Akses penuh
member = Akses terbatas
```

## Informasi Pengembang
**Kelompok 5 SI-C - Praktikum Pemrograman Lanjut**
- **Sabrina**: Struktur Tugas (Tree Implementation)
- **Mela**: Log Perubahan (Double Linked List System)
- **Lingga**: Antrian User (Queue Management)
- **Shabree**: Searching dan Sorting (Algorithms)
- **Hani**: Manajemen Pengguna dan Peran (User Management)
- **Semua Anggota Tim**: Menu CLI & Logika Utama (Integration)

---
