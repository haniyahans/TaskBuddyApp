# TaskBuddy Application
**Tugas Akhir Praktikum Pemrograman Lanjut SI-C Kelompok 5**

## Deskripsi Aplikasi
TaskBuddy adalah aplikasi manajemen tugas berbasis Command Line Interface (CLI) yang dirancang untuk membantu pengguna mengorganisir dan mengelola tugas-tugas mereka secara efisien. Aplikasi ini mendukung sistem hierarki tugas dengan kemampuan menambahkan tugas utama dan subtugas, serta dilengkapi dengan fitur pencarian, pengurutan, dan sistem logging aktivitas.

## Fitur Utama

### Sistem Autentikasi
- **Login & Registrasi**: Sistem keamanan dengan username dan password
- **Role-based Access**: Dua tingkat akses (Admin dan Member)
- **User Queue Management**: Sistem antrian pengguna untuk pengelolaan akses

### Manajemen Tugas
- **Hierarki Tugas**: Struktur pohon dengan tugas utama dan subtugas
- **Detail Tugas Lengkap**: Nama, deskripsi, prioritas (High/Medium/Low), deadline, dan status
- **CRUD Operations**: Create, Read, Update, Delete untuk semua tugas
- **Status Tracking**: Pelacakan status penyelesaian tugas

### Pencarian & Pengurutan
- **Linear Search**: Pencarian berdasarkan nama atau deskripsi tugas
- **Bubble Sort**: Pengurutan otomatis berdasarkan prioritas dan deadline
- **Filter Results**: Hasil pencarian yang akurat dan relevan

### Logging & History
- **Activity Log**: Pencatatan semua aktivitas pengguna dengan timestamp
- **Undo/Redo System**: Kemampuan membatalkan dan mengulangi aksi
- **Double Linked List**: Navigasi riwayat yang efisien

### User Management
- **Queue System**: Antrian pengguna dengan implementasi FIFO
- **Member Management**: Admin dapat menghapus member
- **Real-time User Tracking**: Pelacakan pengguna aktif

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
- Tambah Subtugas
- Lihat Semua Tugas
- Hapus Tugas
- Cari Tugas
- Urutkan Tugas
- Tandai Tugas Selesai
- Undo Aktivitas
- Redo Aktivitas
- Lihat Antrian User
- Proses User Berikutnya

## Struktur File

```
TaskBuddyApp/
├── TaskBuddyApp.java       # Main application & UI
├── Task.java               # Abstract task class
├── SimpleTask.java         # Concrete task implementation
├── TaskNode.java           # Tree node for task hierarchy
├── TaskTreeManager.java    # Task tree operations
├── TaskOperations.java     # Search & sort algorithms
├── User.java              # User entity
├── UserManager.java       # User management operations
├── UserQueueManager.java  # Queue management system
├── LogHistory.java        # Activity logging system
├── LogNode.java          # Log entry node
└── README.md             # Project documentation
```

## Pembagian Tugas

### **Sabrina**
**Fokus: Struktur Tugas (Tree)**
- **TaskTreeManager.java**: Implementasi complete tree operations (addMainTask, addSubtask, findTaskNode, removeTask)
- **TaskNode.java**: Node structure untuk implementasi tree hierarchy
- **Task.java**: Abstract class design dengan encapsulation
- **SimpleTask.java**: Concrete implementation dengan inheritance dan polymorphism
- **Fitur**: Hierarchical task management, tree traversal, dan task CRUD operations

### **Mela** 
**Fokus: Log Perubahan (Double Linked List)**
- **LogHistory.java**: Implementasi complete double linked list untuk activity logging
- **LogNode.java**: Node structure dengan timestamp dan navigation pointers
- **Fitur**: Activity tracking, undo/redo system, dan history navigation
- **Algoritma**: Double linked list traversal untuk efficient undo/redo operations

### **Lingga** 
**Fokus: Antrian User (Queue)**
- **UserQueueManager.java**: Implementasi complete queue management system
- **Fitur**: FIFO user queue, queue display, user processing, dan queue removal
- **Algoritma**: Queue operations (enqueue, dequeue, display) dengan LinkedList implementation

### **Shabree** 
**Fokus: Sorting dan Searching**
- **TaskOperations.java**: Implementasi complete search dan sort algorithms
- **Linear Search**: Pencarian tugas berdasarkan nama dan deskripsi
- **Bubble Sort**: Pengurutan berdasarkan prioritas dan deadline
- **Integration**: Method `searchTask()` dan `sortTasks()` di TaskBuddyApp, serta method `getTaskInfo()` di SimpleTask.java

### **Hani** 
**Fokus: Manajemen Pengguna dan Peran**
- **UserManager.java**: Complete user management system
- **User.java**: User entity dengan role-based access control
- **Fitur**: User registration, authentication, role management, dan user deletion
- **Security**: Password validation dan role-based menu access

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
