package com.PIdevv.PI.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


    @Getter
    @Setter
    @NoArgsConstructor
    @Entity
    @Table(name = "User")

    public class User implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="idUser")
        private Long idUser;
        private String firstName;
         private String lastName;
         private Date birthDate;
         private String placeBirth;
       private String job;
       private String gender;
       private String adress;
        private Integer postalCode;
        private String email;
        private boolean actived;
        private String phoneNumber;
        private String password;
        private String username;
        private Boolean banned;

        private Date bannedPeriode;
        @JsonIgnore
        @OneToOne(mappedBy = "UsertPass")
        private PasswordResetToken UsertPass;
        @JsonIgnore
        @OneToOne(mappedBy = "emailUser")
        private EmailVerificationToken emailUser;


        public User(String username, String email, String encode) {
        }


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "User{" +
                    "idUser=" + idUser +

                    ", Email='" + email + '\'' +

                    ", password='" + password + '\'' +
                    ", username='" + username + '\'' +
                    ", Account=" + Account +
                    ", roles=" + roles +
                    '}';
        }
        @JsonIgnore
        @OneToMany(cascade = CascadeType.ALL)
        private Set<Account> Account;
        @JsonIgnore
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(  name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public Long getIdUser() {
            return idUser;
        }

       /* public String getFirstName() {
            return FirstName;
        }

        public String getLastName() {
            return LastName;
        }

        public Date getBirthDate() {
            return BirthDate;
        }

        public String getPlaceBirth() {
            return PlaceBirth;
        }

        public String getFnc() {
            return fnc;
        }

        public String getGender() {
            return Gender;
        }

        public String getAdress() {
            return Adress;
        }

        public Integer getPostalCode() {
            return PostalCode;
        }*/

        public String getEmail() {
            return email;
        }

      /*  public String getPhoneNumber() {
            return PhoneNumber;
        }*/

        public String getPassword() {
            return password;
        }


        public Set<Account> getAccount() {
            return Account;
        }
       /* public boolean isActived() {
            return actived;
        }*/

        public User(Long idUser, String email, String password, String username, Set<Role> roles) {
            this.idUser = idUser;
            this.email = email;
            this.password = password;
            this.username = username;
            this.roles = roles;
        }
        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }

        public Set<Role> getRoles() {
            return roles;
        }



    }
