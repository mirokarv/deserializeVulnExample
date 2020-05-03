package com.mahproject.spring.model;

import org.apache.commons.codec.binary.Hex;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Entity
@Table(name = "users")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username = "";

    @Column(name = "password")
    private String password = "";

    public UserModel(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    public UserModel() { }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String pw) {
        return createMd5Hash(pw).equals(password);
    }

    public void setPassword(String pw) {
        password = createMd5Hash(pw);
    }

    public int getId() {
        return id;
    }

    private String createMd5Hash(String hashable) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(hashable.getBytes());
            byte[] resultByte = md.digest();
            return new String(Hex.encodeHex(resultByte));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
