/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mcsadmin
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByBirthday", query = "SELECT u FROM User u WHERE u.birthday = :birthday")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Lob
    @Column(name = "passwd")
    private String passwd;
    @Basic(optional = false)
    @Lob
    @Column(name = "screen_name")
    private String screenName;
    @Lob
    @Column(name = "real_name")
    private String realName;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "birthday")
    private String birthday;
    @Lob
    @Column(name = "interests")
    private String interests;
    @Lob
    @Column(name = "config")
    private String config;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friendId")
    private Collection<Friends> friendsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Friends> friendsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<InviteCode> inviteCodeCollection;
    @JoinColumn(name = "profile_img_id", referencedColumnName = "id")
    @ManyToOne
    private Image profileImgId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posterId")
    private Collection<Wall> wallCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Wall> wallCollection1;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, int userId, String email, String passwd, String screenName) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.passwd = passwd;
        this.screenName = screenName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @XmlTransient
    public Collection<Friends> getFriendsCollection() {
        return friendsCollection;
    }

    public void setFriendsCollection(Collection<Friends> friendsCollection) {
        this.friendsCollection = friendsCollection;
    }

    @XmlTransient
    public Collection<Friends> getFriendsCollection1() {
        return friendsCollection1;
    }

    public void setFriendsCollection1(Collection<Friends> friendsCollection1) {
        this.friendsCollection1 = friendsCollection1;
    }

    @XmlTransient
    public Collection<InviteCode> getInviteCodeCollection() {
        return inviteCodeCollection;
    }

    public void setInviteCodeCollection(Collection<InviteCode> inviteCodeCollection) {
        this.inviteCodeCollection = inviteCodeCollection;
    }

    public Image getProfileImgId() {
        return profileImgId;
    }

    public void setProfileImgId(Image profileImgId) {
        this.profileImgId = profileImgId;
    }

    @XmlTransient
    public Collection<Wall> getWallCollection() {
        return wallCollection;
    }

    public void setWallCollection(Collection<Wall> wallCollection) {
        this.wallCollection = wallCollection;
    }

    @XmlTransient
    public Collection<Wall> getWallCollection1() {
        return wallCollection1;
    }

    public void setWallCollection1(Collection<Wall> wallCollection1) {
        this.wallCollection1 = wallCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "socialnetwork.entities.User[ id=" + id + " ]";
    }
    
}
