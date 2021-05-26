package objetos2.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		System.out.println(p.encode("password"));
	}

}
