public class User {

    // Atribut untuk menyimpan username, password, dan role
    private String username;
    private String password;
    private String role;

    //konstruktor untuk inisialisasi user
    public User (String usn, String pass, String role){
        this.username = usn;
        this.password = pass;
        this.role = role;
    }

    // Getter methods untuk mengakses atribut      
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        if(role.equalsIgnoreCase("admin")){
            return "Admin";
        }
        if (role.equalsIgnoreCase("member")){
            return "Member";
        }
        return "Role tidak ditemukan.";
    }
}
