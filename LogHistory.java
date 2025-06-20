class LogHistory {
    private LogNode head, tail, current;

    // Menambahkan log baru ke dalam daftar
    public void addLog(String action, String detail) {
        // Jika ada log setelah current (misalnya setelah undo), hapus semua log setelah current
        LogNode newNode = new LogNode(action, detail);
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
        }

        // Jika daftar log masih kosong
        if (head == null) {
            head = tail = current = newNode;
        } else {
            // Tambahkan node baru di akhir daftar (tail)
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            current = newNode;
            // Posisikan current ke log terbaru
        }
    }

    // Menampilkan semua log dari awal sampai akhir
    public void displayLogs() {
        System.out.println("Riwayat Log:");
        LogNode temp = head;
        int i = 1;
        while (temp != null) {
            String pointer = (temp == current) ? " <-- Current" : "";
            System.out.println(i+". ["+temp.timestamp+"]"+"- " + temp.action + " - " + temp.detail+ pointer);
            temp = temp.next;
            i++;
        }
    }

    // Fungsi untuk membatalkan aksi terakhir (undo)
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev; // Geser posisi current ke belakang
            
            System.out.println("["+current.timestamp+"]"+"- UNDO:" + current.action + " - " + current.detail);
        } else {
            System.out.println("Tidak bisa undo lagi.");
        }
    }

    // Fungsi untuk mengulang aksi yang dibatalkan (redo)
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next; // Geser posisi current ke depan
            System.out.println("["+current.timestamp+"]"+"- REDO:" + current.action + " - " + current.detail );
        } else {
            System.out.println("Tidak bisa redo lagi.");
        }
    }
}
