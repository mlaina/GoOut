package es.fdi.iw.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@NamedQueries({
	@NamedQuery(name="allUsers",
			query="select u from Usuario u"),
			@NamedQuery(name="userByLogin",
			query="select u from Usuario u where u.login = :loginParam"),
			@NamedQuery(name="delUser",
			query="delete from Usuario u where u.id= :idParam")
			/*@NamedQuery(name="modUser",
			query="update Usuario u set u.login= :nick_perfil")*/
})
public class Usuario {	

	private static BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

	private long id;
	private String login;
	private String pass_cifrado;
	private String rol;
	private Date nacimiento;
	private String provincia;
	private String email;
	private String idFoto;
	
	private List<Actividad> historial;
	private List<Actividad> actuales; 
	private List<Usuario> amigos; //Seria mejor tener una lista de ids de usuarios


	public static Usuario createUser(String login, String pass, String rol, 
									Date nacimiento, String prov, String email) {
		Usuario u = new Usuario();
		u.login = login;
		u.pass_cifrado = generateHashedAndSalted(pass);
		u.rol = rol;
		u.nacimiento=nacimiento;
		u.provincia=prov;
		u.email=email;
		return u;
	}
	

	public boolean isPassValid(String pass) {
		return bcryptEncoder.matches(pass, pass_cifrado);		
	}

	/**
	 * Generate a hashed&salted hex-string from a user's pass and salt
	 * @param pass to use; no length-limit!
	 * @param salt to use
	 * @return a string to store in the BD that does not reveal the password even
	 * if the DB is compromised. Note that brute-force is possible, but it will
	 * have to be targeted (ie.: use the same salt)
	 */
	public static String generateHashedAndSalted(String pass) {
		/*
		Código viejo: sólo 1 iteración de SHA-1. bCrypt es mucho más seguro (itera 1024 veces...)

		Además, bcryptEncoder guarda la sal junto a la contraseña
		byte[] saltBytes = hexStringToByteArray(user.salt);
		byte[] passBytes = pass.getBytes();
		byte[] toHash = new byte[saltBytes.length + passBytes.length];
		System.arraycopy(passBytes, 0, toHash, 0, passBytes.length);
		System.arraycopy(saltBytes, 0, toHash, passBytes.length, saltBytes.length);
		return byteArrayToHexString(sha1hash(toHash));
		 */
		return bcryptEncoder.encode(pass);
	}	

	/**
	 * Converts a byte array to a hex string
	 * @param b converts a byte array to a hex string; nice for storing
	 * @return the corresponding hex string
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<b.length; i++) {
			sb.append(Integer.toString((b[i]&0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	/**
	 * Converts a hex string to a byte array
	 * @param hex string to convert
	 * @return equivalent byte array
	 */
	public static byte[] hexStringToByteArray(String hex) {
		byte[] r = new byte[hex.length()/2];
		for (int i=0; i<r.length; i++) {
			String h = hex.substring(i*2, (i+1)*2);
			r[i] = (byte)Integer.parseInt(h, 16);
		}
		return r;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(unique=true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return pass_cifrado;
	}

	public void setPassword(String hashedAndSalted) {
		this.pass_cifrado = hashedAndSalted;
	}
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String toString() {
		return "" + id + " " + login + " " + pass_cifrado;
	}
	@Column(unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@ManyToMany(targetEntity=Actividad.class)
	public List<Actividad> getHistorial() {
		return historial;
	}
	public void setHistorial(List<Actividad> historial) {
		this.historial = historial;
	}
	@ManyToMany(targetEntity=Actividad.class)
	public List<Actividad> getActuales() {
		return actuales;
	}
	public void setActuales(List<Actividad> actuales) {
		this.actuales = actuales;
	}

	@OneToMany(targetEntity=Usuario.class)
	public List<Usuario> getAmigos() {
		return amigos;
	}
	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}

	public String getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(String idFoto) {
		this.idFoto = idFoto;
	}


}